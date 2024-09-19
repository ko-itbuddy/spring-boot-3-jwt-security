package org.itbuddy.security.book.mapper;

import org.itbuddy.security.book.application.BookRequest;
import org.itbuddy.security.book.repository.entity.BookEntity;

public class BookEntityMapper {

    public static BookEntity toBookEntity(BookRequest request){
        return BookEntity.builder()
                         .id(request.getId())
                         .author(request.getAuthor())
                         .isbn(request.getIsbn())
                         .build();
    }
}
