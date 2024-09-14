package org.itbuddy.security.auth.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.itbuddy.security.auth.application.interfaces.UserRepository;
import org.itbuddy.security.common.security.repository.entity.UserEntity;
import org.itbuddy.security.common.security.repository.jpa.UserJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserEntity save(UserEntity user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }
}
