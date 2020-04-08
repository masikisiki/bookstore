package com.bookstore.bookstoreapi.services;

import com.bookstore.bookstoreapi.model.Author;
import com.bookstore.bookstoreapi.model.AuthorInput;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author addAuthor(AuthorInput input);

    Optional<Author> findAuthorByName(String name);

    List<Author> findAllAuthors();

    void deleteAuthor(int id);

    Optional<Author> findAuthorById(int id);

    List<Author> search(String term);
}
