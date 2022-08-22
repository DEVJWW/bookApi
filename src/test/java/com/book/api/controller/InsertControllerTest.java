package com.book.api.controller;


import com.book.api.entity.Book;
import com.book.api.entity.Category;
import com.book.api.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InsertControllerTest {

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("도서저장테스트")
    @Test
    void insert() {


        Category category = new Category();
        category.setCategory("TEST_CATE");
        List<Category> categoryTest = new ArrayList<>();
        categoryTest.add(category);

        Book book = new Book();
        book.setName("TEST_NAME");
        book.setWriter("TEST_WRITER");
        book.setStatus("Y");
        book.setCategoryData(categoryTest);
        category.setBook(book);

        Book bookList = bookRepository.save(book);

        assertEquals("TEST_NAME", bookList.getName());
        assertEquals(categoryTest, bookList.getCategoryData());
        assertEquals(categoryTest.size(), book.getCategoryData().size());

    }

    @DisplayName("다중저장")
    @Test
    void insertAll() {

        List<Book> bookList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            Book book = new Book();
            book.setWriter("TEST_WRITER_" + i);
            book.setName("TEST_NAME_" + i);
            for (int j = 0; j < 6; j++) {

                Category category = new Category();
                category.setCategory("TEST_CATE_" + j);
                category.setBook(book);
                categoryList.add(category);
            }
            book.setCategoryData(categoryList);
            bookList.add(book);

        }

        List<Book> bookListTest = bookRepository.saveAll(bookList);
        assertEquals(bookList, bookListTest);
    }
}