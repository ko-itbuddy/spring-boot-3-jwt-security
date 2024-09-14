package org.itbuddy.security.auth.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.itbuddy.security.auth.application.interfaces.TokenRepository;
import org.itbuddy.security.common.security.repository.entity.TokenEntity;
import org.itbuddy.security.common.security.repository.jpa.TokenJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

    private final TokenJpaRepository tokenJpaRepository;

    @Override
    public TokenEntity save(TokenEntity token) {
        return tokenJpaRepository.save(token);
    }

    @Override
    public List<TokenEntity> saveAll(List<TokenEntity> tokens) {
        return tokenJpaRepository.saveAll(tokens);
    }

    @Override
    public List<TokenEntity> findAllValidTokenByUserId(Integer userId) {
        return null;
    }
}
