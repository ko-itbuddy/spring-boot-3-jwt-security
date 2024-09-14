package org.itbuddy.security.auth.application.interfaces;

import java.util.Optional;
import org.itbuddy.security.common.security.repository.entity.UserEntity;

public interface UserRepository {

    UserEntity save(UserEntity user);
    Optional<UserEntity> findByEmail(String email);
}
