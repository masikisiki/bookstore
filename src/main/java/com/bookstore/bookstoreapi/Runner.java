package com.bookstore.bookstoreapi;

import com.bookstore.bookstoreapi.model.BookInput;
import com.bookstore.bookstoreapi.model.BookStore;
import com.bookstore.bookstoreapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    @Value("classpath:xml/bookstore.xml")
    Resource bookStore;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookStoreMapper bookStoreMapper;

    @Override
    public void run(String... args) throws Exception {

        JAXBContext context = JAXBContext.newInstance(BookStore.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        BookStore books = (BookStore) unmarshaller.unmarshal(bookStore.getFile());
        System.out.println("test");
        List<BookInput> bookInputs = bookStoreMapper.toBookInputs(books.getBooks());
        
        bookInputs.forEach(bookService::addBook);
    }
}
