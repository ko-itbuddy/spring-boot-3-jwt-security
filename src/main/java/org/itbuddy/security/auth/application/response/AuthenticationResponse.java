package org.itbuddy.security.auth.application.response;

public record AuthenticationResponse(
    String accessToken,
    String refreshToken
) {


}
