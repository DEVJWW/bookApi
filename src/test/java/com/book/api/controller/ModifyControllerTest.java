package com.book.api.controller;

import com.book.api.dto.StatusModifyDto;
import com.book.api.entity.Book;
import com.book.api.entity.Category;
import com.book.api.repository.BookRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ModifyControllerTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void sModify() {


         Book book = new Book();
         book.setName("TEST_NAME");
         book.setWriter("TEST_WRITER");
         book.setStatus("Y");
         book=bookRepository.save(book);

         Book books = new Book();
         books.setId(book.getId());
         books.setStatus("N");
         books= bookRepository.save(books);

        assertEquals("Y",books.getStatus());

    }

    @Test
    void cModify() {
    }
}