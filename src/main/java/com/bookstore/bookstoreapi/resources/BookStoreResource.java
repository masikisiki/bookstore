package com.bookstore.bookstoreapi.resources;

import com.bookstore.bookstoreapi.model.Author;
import com.bookstore.bookstoreapi.model.AuthorInput;
import com.bookstore.bookstoreapi.model.Book;
import com.bookstore.bookstoreapi.model.BookInput;
import com.bookstore.bookstoreapi.model.SearchInput;
import com.bookstore.bookstoreapi.services.AuthorService;
import com.bookstore.bookstoreapi.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/")
public class BookStoreResource {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookStoreResource(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostMapping("authors/add")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorInput input) {
        if (authorService.findAuthorByName(input.getName()).isPresent())
            return ResponseEntity.badRequest().build();

        Author author = authorService.addAuthor(input);
        return ResponseEntity.ok().body(author);
    }

    @GetMapping("authors/all")
    public ResponseEntity<List<Author>> findAllAuthors() {
        return ResponseEntity.ok().body(authorService.findAllAuthors());
    }

    @DeleteMapping("authors/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        if (authorService.findAuthorById(id).isPresent()) {
            authorService.deleteAuthor(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("books/add")
    public ResponseEntity<Book> addBook(@RequestBody BookInput input) {
        return ResponseEntity.ok().body(bookService.addBook(input));
    }

    @GetMapping("books/all")
    public ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok().body(bookService.findAllBooks());
    }

    @DeleteMapping("books/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        if (bookService.findBookById(id).isPresent()) {
            bookService.deleteBook(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("search")
    public ResponseEntity<List<String>> addBook(@RequestBody SearchInput input) {
        return ResponseEntity.ok().body(bookService.search(input.getTerm()));
    }
}
