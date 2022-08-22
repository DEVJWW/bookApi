package com.book.api.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SearchControllerTest {

    @BeforeAll
    @DisplayName("초기 데이터 SAVE")
    void setUp(){

    }
    @Test
    @DisplayName("카테고리 검색테스트 Like")
    void searchByCategory() {


    }

    @Test
    void searchByAll() {
    }
}