package org.itbuddy.security.book.repository.jpa;

import org.itbuddy.security.book.repository.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
