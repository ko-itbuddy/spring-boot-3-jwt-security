package org.itbuddy.security.book.application;

import lombok.RequiredArgsConstructor;
import org.itbuddy.security.book.mapper.BookEntityMapper;
import org.itbuddy.security.book.repository.entity.BookEntity;
import org.itbuddy.security.book.repository.jpa.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public void save(BookRequest request) {
        final BookEntity book = BookEntityMapper.toBookEntity(request);
        repository.save(book);
    }

    public List<BookEntity> findAll() {
        return repository.findAll();
    }
}
