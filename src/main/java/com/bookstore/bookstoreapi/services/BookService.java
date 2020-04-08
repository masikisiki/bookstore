package com.bookstore.bookstoreapi.services;

import com.bookstore.bookstoreapi.model.Book;
import com.bookstore.bookstoreapi.model.BookInput;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book addBook(BookInput input);

    Optional<Book> findBookById(int id);

    List<Book> findAllBooks();

    void deleteBook(int id);

    List<String> search(String searchTerm);
}
