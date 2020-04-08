package com.bookstore.bookstoreapi.repositories;


import com.bookstore.bookstoreapi.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Rollback
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void shouldAdd() {
        //arrange
        Author author = new Author("Testing Author 01");

        //act
        authorRepository.save(author);

        //assert
        assertThat(author.getId()).isGreaterThan(0);
    }

    @Test
    public void shouldFindById() {
        //arrange
        Author author = new Author("Testing Author 02");
        authorRepository.save(author);

        //act
        Optional<Author> result = authorRepository.findById(author.getId());

        //assert
        assertThat(result.isPresent())
                .isTrue();
    }

    @Test
    public void shouldFindAll() {
        //arrange
        Author author1 = new Author("Testing Author 03");
        Author author2 = new Author("Testing Author 04");
        authorRepository.saveAll(Arrays.asList(author1, author2));

        //act
        Iterable<Author> all = authorRepository.findAll();

        //assert
        assertThat(all).isNotEmpty();
        assertThat(all).extracting(Author::getFullName)
                .containsAll(Arrays.asList("Testing Author 03", "Testing Author 04"));
    }
}
