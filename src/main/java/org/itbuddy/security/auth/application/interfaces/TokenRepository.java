package org.itbuddy.security.auth.application.interfaces;

import java.util.List;
import org.itbuddy.security.common.security.repository.entity.TokenEntity;

public interface TokenRepository {

    TokenEntity save(TokenEntity token);
    List<TokenEntity> saveAll(List<TokenEntity> tokens);
    List<TokenEntity> findAllValidTokenByUserId(Integer userId);
}
