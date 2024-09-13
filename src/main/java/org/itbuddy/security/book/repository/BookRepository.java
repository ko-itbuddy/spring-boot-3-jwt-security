package org.itbuddy.security.book.repository;

import org.itbuddy.security.book.repository.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
