package com.book.api.controller;

import com.book.api.dto.StatusModifyDto;
import com.book.api.entity.Book;
import com.book.api.entity.Category;
import com.book.api.repository.BookRepository;
import com.book.api.repository.CategoryRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ModifyControllerTest {


    //BEFOREACH
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @DisplayName("도서상태 업데이트 테스트")
    @Rollback(false)
    @Test
    void sModify() {


        Book book = new Book();
        book.setId(311);
        book.setName("TEST_NAME");
        book.setWriter("TEST_WRITER");
        book.setStatus("Y");
        final Book bookBefore = bookRepository.save(book);
        String beforeStatus = bookBefore.getStatus();

        Book books = new Book();
        books.setId(bookBefore.getId());
        books.setName(bookBefore.getName());
        books.setWriter(bookBefore.getWriter());
        books.setStatus("N");
        final Book bookAfter = bookRepository.save(books);
        String afterStatus = bookBefore.getStatus();

        assertEquals("Y", beforeStatus);
        assertEquals("N", afterStatus);

    }

    @DisplayName("카테고리업데이트")
    @Test
    @Rollback(false)
    void cModify() {

        Set<Category> categoryTestBefore = new HashSet<>();
        Category categoryBefore = new Category();
        categoryBefore.setCategory("TEST_BEFORE");
        categoryTestBefore.add(categoryBefore);

        Book book = new Book();
        book.setName("TEST_NAME");
        book.setWriter("TEST_WRITER");
        book.setStatus("Y");
        book.setCategoryData(categoryTestBefore);
        categoryBefore.setBook(book);

        Book bookBefore = bookRepository.save(book);
        int beforSize = bookBefore.getCategoryData().size();
        bookBefore.getCategoryData().clear();
        bookRepository.save(bookBefore);
        Optional<Book> bookFindBefore = bookRepository.findById(bookBefore.getId());
        bookBefore = bookFindBefore.get();

        Set<Category> categoryTestAfter = new HashSet<>();
        for(int i=0;i<3;i++){
            Category categoryAfter = new Category();
            categoryAfter.setBook(bookBefore);
            categoryAfter.setCategory("TEST_AFTER_"+i);
            categoryTestAfter.add(categoryAfter);
        }


        Book newBook = new Book();
        newBook.setId(bookBefore.getId());
        newBook.setWriter(bookBefore.getWriter());
        newBook.setStatus(bookBefore.getStatus());
        newBook.setName(bookBefore.getName());
        newBook.setCategoryData(categoryTestAfter);

        bookRepository.save(newBook);

        Optional<Book> bookFindAfter = bookRepository.findById(bookBefore.getId());
        Book bookAfter = bookFindAfter.get();
        int afterSize = bookAfter.getCategoryData().size();


        assertNotEquals(beforSize,afterSize);
    }
}