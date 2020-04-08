package com.bookstore.bookstoreapi.repositories;

import com.bookstore.bookstoreapi.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Optional<Author> findByFullName(String name);

    List<Author> findByFullNameContaining(String text);
}
