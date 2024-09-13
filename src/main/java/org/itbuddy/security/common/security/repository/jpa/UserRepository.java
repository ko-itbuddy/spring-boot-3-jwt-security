package org.itbuddy.security.common.security.repository.jpa;

import java.util.Optional;
import org.itbuddy.security.common.security.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
