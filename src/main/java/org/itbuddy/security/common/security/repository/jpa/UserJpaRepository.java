package org.itbuddy.security.common.security.repository.jpa;

import java.util.Optional;
import org.itbuddy.security.common.security.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {

  Optional<UserEntity> findByEmail(String email);

}
