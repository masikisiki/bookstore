package com.bookstore.bookstoreapi;


import com.bookstore.bookstoreapi.model.Author;
import com.bookstore.bookstoreapi.model.Book;
import com.bookstore.bookstoreapi.model.BookInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookStoreMapper {

    List<BookInput> toBookInputs(List<Book> source);

    @Mapping(target = "authorNames", expression = "java(toAuthorNames(source))")
    BookInput toBookInput(Book source);

    default List<String> toAuthorNames(Book source) {
        //hello
        return source.getAuthors().stream().map(Author::getFullName).collect(Collectors.toList());
    }
}
