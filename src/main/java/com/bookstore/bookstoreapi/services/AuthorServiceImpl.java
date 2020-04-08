package com.bookstore.bookstoreapi.services;

import com.bookstore.bookstoreapi.model.Author;
import com.bookstore.bookstoreapi.model.AuthorInput;
import com.bookstore.bookstoreapi.repositories.AuthorRepository;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author addAuthor(AuthorInput input) {
        Assert.notNull(input, "Author input cannot be empty");
        Assert.hasText(input.getName(), "Please provide a valid author name");

        Optional<Author> optionalAuthor = authorRepository.findByFullName(input.getName());
        return optionalAuthor.orElseGet(() -> authorRepository.save(new Author(input.getName())));
    }

    @Override
    public Optional<Author> findAuthorByName(String name) {
        return authorRepository.findByFullName(name);
    }

    @Override
    public List<Author> findAllAuthors() {
        return Lists.newArrayList(authorRepository.findAll());
    }

    @Override
    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Optional<Author> findAuthorById(int id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> search(String term) {
        return authorRepository.findByFullNameContaining(term);
    }
}
