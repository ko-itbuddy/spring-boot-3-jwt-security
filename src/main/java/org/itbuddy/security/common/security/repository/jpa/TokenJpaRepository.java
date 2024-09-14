package org.itbuddy.security.common.security.repository.jpa;

import java.util.List;
import java.util.Optional;
import org.itbuddy.security.common.security.repository.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, Integer> {

  @Query(value = """
      select t from TokenEntity t inner join UserEntity u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<TokenEntity> findAllValidTokenByUser(Integer id);

  Optional<TokenEntity> findByToken(String token);
}
