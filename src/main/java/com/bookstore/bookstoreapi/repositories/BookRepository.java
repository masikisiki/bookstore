package com.bookstore.bookstoreapi.repositories;

import com.bookstore.bookstoreapi.model.Author;
import com.bookstore.bookstoreapi.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByTitleContainingOrCategoryContaining(String text1, String text2);

    List<Book> findByAuthorsIn(List<Author> authors);
}
