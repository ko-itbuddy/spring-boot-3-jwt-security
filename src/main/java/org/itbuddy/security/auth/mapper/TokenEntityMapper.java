package org.itbuddy.security.auth.mapper;

import org.itbuddy.security.common.security.domain.TokenType;
import org.itbuddy.security.common.security.repository.entity.TokenEntity;
import org.itbuddy.security.common.security.repository.entity.UserEntity;

public class TokenEntityMapper {

    public static TokenEntity of(UserEntity user, String jwtToken) {
        return TokenEntity.builder()
                          .user(user)
                          .token(jwtToken)
                          .tokenType(TokenType.BEARER)
                          .expired(false)
                          .revoked(false)
                          .build();
    }
}
