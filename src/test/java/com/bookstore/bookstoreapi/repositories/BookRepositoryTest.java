package com.bookstore.bookstoreapi.repositories;

import com.bookstore.bookstoreapi.model.Author;
import com.bookstore.bookstoreapi.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Rollback
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void shouldAdd() {
        //arrange
        Book book = createTestBook("Test book 1", new String[]{"Author 1", "Author 3"});

        //act
        bookRepository.save(book);

        //assert
        assertThat(book.getId()).isGreaterThan(0);
        assertThat(book.getTitle()).isEqualTo(book.getTitle());
        assertThat(book.getCategory()).isEqualTo(book.getCategory());
        assertThat(book.getPrice()).isEqualTo(book.getPrice());
        assertThat(book.getYear()).isEqualTo(book.getYear());

        assertThat(book.getAuthors().size()).isEqualTo(2);
        assertThat(book.getAuthors()).extracting(Author::getId).allMatch(id -> id > 0);
    }

    @Test
    public void shouldFindById() {
        //arrange
        Book book = createTestBook("Test book 1", new String[]{"Author 5", "Author 6"});
        bookRepository.save(book);

        //act
        Optional<Book> result = bookRepository.findById(book.getId());

        //assert
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getId()).isEqualTo(book.getId());
    }


    @Test
    public void shouldFindAll() {
        //arrange
        bookRepository.deleteAll();
        Book book1 = createTestBook("Test book 1", new String[]{"Author 7", "Author 8"});
        Book book2 = createTestBook("Test book 2", new String[]{"Author 9", "Author 10"});
        bookRepository.saveAll(Arrays.asList(book1, book2));

        //act
        Iterable<Book> actual = bookRepository.findAll();

        //assert
        assertThat(actual).size().isEqualTo(2);
    }


    private Book createTestBook(String title, String[] authors) {
        Book book = new Book(title, "Cooking", 33.33, 2017);
        Set<Author> authorSet = Arrays.stream(authors).map(Author::new).collect(Collectors.toSet());
         authorRepository.saveAll(authorSet);
        book.setAuthors(authorSet);
        return book;
    }
}
