package com.bookstore.bookstoreapi.services;

import com.bookstore.bookstoreapi.model.Author;
import com.bookstore.bookstoreapi.model.AuthorInput;
import com.bookstore.bookstoreapi.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AuthorServiceImplTest {

    private AuthorRepository authorRepository;
    private AuthorService sut;

    @BeforeEach
    public void beforeAll() {
        authorRepository = mock(AuthorRepository.class);
        when(authorRepository.save(any())).thenAnswer(createAuthorMockAnswer());

        sut = new AuthorServiceImpl(authorRepository);
    }

    @Test
    void addAuthor() {
        //arrange
        AuthorInput input = new AuthorInput();
        input.setName("Josh Long");

        //act
        Author actual = sut.addAuthor(input);

        //assert
        assertThat(actual).isNotNull();
        assertThat(actual.getFullName()).isEqualTo(input.getName());

        verify(authorRepository, times(1)).findByFullName(input.getName());
        verify(authorRepository, times(1))
                .save(argThat(a -> a.getFullName().equals(input.getName())));
    }

    @Test
    void findAuthorByName_given_noMatch() {
        //arrange
        String searchName = "Rob";
        when(authorRepository.findByFullName(searchName)).thenReturn(Optional.empty());

        //act
        Optional<Author> actual = sut.findAuthorByName(searchName);

        //assert
        assertThat(actual.isPresent()).isFalse();
    }

    @Test
    void findAuthorByName_givenAMatch() {
        //arrange
        String searchName = "Bob";
        when(authorRepository.findByFullName(searchName)).thenReturn(Optional.of(new Author(searchName)));

        //act
        Optional<Author> actual = sut.findAuthorByName(searchName);

        //assert
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().getFullName()).isEqualTo(searchName);
    }

    @Test
    void findAllAuthors() {
    }

    @Test
    void deleteAuthor() {
    }

    @Test
    void findAuthorById() {
    }

    private Answer<Author> createAuthorMockAnswer() {
        return a -> {
            Author argument = a.getArgument(0);
            argument.setId(new Random().nextInt());
            return argument;
        };
    }
}
