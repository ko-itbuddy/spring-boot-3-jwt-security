package org.itbuddy.security.auth.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.itbuddy.security.auth.application.request.AuthenticationRequest;
import org.itbuddy.security.auth.application.response.AuthenticationResponse;
import org.itbuddy.security.auth.application.request.RegisterRequest;
import org.itbuddy.security.auth.application.interfaces.TokenRepository;
import org.itbuddy.security.auth.application.interfaces.UserRepository;
import org.itbuddy.security.auth.mapper.TokenEntityMapper;
import org.itbuddy.security.auth.mapper.UserEntityMapper;
import org.itbuddy.security.common.security.application.JwtService;
import org.itbuddy.security.common.security.repository.entity.TokenEntity;
import org.itbuddy.security.common.security.repository.entity.UserEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    final UserEntity user = UserEntityMapper.of(request,
        passwordEncoder.encode(request.password()));
    final UserEntity savedUser = userRepository.save(user);
    final String jwtToken = jwtService.generateToken(user);
    final String refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);

    return new AuthenticationResponse(jwtToken, refreshToken);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    final UserEntity user = userRepository.findByEmail(request.getEmail())
                                          .orElseThrow();
    final String jwtToken = jwtService.generateToken(user);
    final String refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return new AuthenticationResponse(jwtToken, refreshToken);
  }

  private void saveUserToken(UserEntity user, String jwtToken) {
    TokenEntity token = TokenEntityMapper.of(user, jwtToken);
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(UserEntity user) {
    final List<TokenEntity> validUserTokens = tokenRepository.findAllValidTokenByUserId(
        user.getId());
    if (validUserTokens.isEmpty()) {
      return;
    }
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }

    final String refreshToken = authHeader.substring(7);
    final String userEmail = jwtService.extractUsernameByJwt(refreshToken);
    if (userEmail != null) {
      final UserEntity user = this.userRepository.findByEmail(userEmail)
                                           .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        String accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        AuthenticationResponse authResponse = new AuthenticationResponse(accessToken, refreshToken);
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
