package com.bookstore.bookstoreapi.services;

import com.bookstore.bookstoreapi.model.Author;
import com.bookstore.bookstoreapi.model.AuthorInput;
import com.bookstore.bookstoreapi.model.Book;
import com.bookstore.bookstoreapi.model.BookInput;
import com.bookstore.bookstoreapi.repositories.BookRepository;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public Book addBook(BookInput input) {
        Assert.notNull(input, "Book input cannot be empty");
        Assert.hasText(input.getTitle(), "Please provide a valid book title");
        Assert.notEmpty(input.getAuthorNames(), "Please provide at least one book author");


        Book book = new Book(input.getTitle(), input.getCategory(), input.getPrice(), input.getYear());
        if (input.getId() != 0) {
            Book existingBook = bookRepository.findById(input.getId()).orElseThrow(() -> new RuntimeException("No Book found for " + input.getId()));
            book.setId(existingBook.getId());
            book.setCreatedBy(existingBook.getCreatedBy());
            book.setCreatedOn(existingBook.getCreatedOn());
        }
        book.setAuthors(resolveAuthors(input.getAuthorNames()));
        return bookRepository.save(book);
    }

    private Set<Author> resolveAuthors(List<String> authorNames) {
        return authorNames.stream()
                .map(name -> authorService.findAuthorByName(name).orElse(authorService.addAuthor(new AuthorInput(name))))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Book> findBookById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAllBooks() {
        return Lists.newArrayList(bookRepository.findAll());
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<String> search(String searchTerm) {
        List<Author> authors = authorService.search(searchTerm);
        List<String> lines = authors.stream().map(o -> String.format("Author : %s", o.getFullName())).collect(Collectors.toList());
        List<Book> books = bookRepository.findByTitleContainingOrCategoryContaining(searchTerm, searchTerm);
        List<Book> moreBooks = bookRepository.findByAuthorsIn(authors);
        books.addAll(moreBooks);
        lines.addAll(books.stream()
                .map(b -> String.format("Book : %s ,Category %s, Authors: %s"
                        , b.getTitle()
                        , b.getCategory()
                        , b.getAuthors().stream().map(Author::getFullName).collect(Collectors.joining())
                ))
                .collect(Collectors.toList()));
        return lines;
    }
}
