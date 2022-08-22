package com.book.api.controller;

import com.book.api.dto.BookDto;
import com.book.api.entity.Book;
import com.book.api.entity.Category;
import com.book.api.repository.BookRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchControllerTest {

    @Autowired
    BookRepository bookRepository;

    @BeforeAll
    public void setUp() {
        List<Book> bookList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        String[] testCategory = {"TEST", "TEST2", "SAMPLE", "BOOK"};


        for (int i = 0; i < testCategory.length; i++) {
            Book book = new Book();
            book.setWriter("TEST_WRITER_" + i);
            book.setName("TEST_NAME_" + i);
            Category category = new Category();
            category.setBook(book);
            category.setCategory(testCategory[i]);
            categoryList.add(category);
            book.setCategoryData(categoryList);
            bookList.add(book);

        }


        List<Book> bookListTest = bookRepository.saveAll(bookList);
    }

    @Test
    @DisplayName("카테고리 검색테스트")
    void searchByCategory() {

        List<Book> bookList = bookRepository.findAllByCategoryData("TEST");
        List<BookDto> bookDtoList = bookList.stream().map(BookDto::new).collect(Collectors.toList());

        for (int i = 0; i < bookDtoList.size(); i++) {

            for (int j = 0; j < bookDtoList.get(j).getCategoryData().size(); j++) {
                assertEquals("TEST", bookDtoList.get(i).getCategoryData().get(j).getCategory());

            }

        }
    }
}