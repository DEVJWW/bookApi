package com.book.api.controller;


import com.book.api.entity.Book;
import com.book.api.service.BookService;
import io.swagger.annotations.*;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@Api(tags = {"도서 Search Controller"})
@ApiResponses({
        @ApiResponse(code = 200, message = "성공 200코드 리턴"),
        @ApiResponse(code = 400, message = "실패 400코드 리턴 + 리턴 DATA")
})
@RestController
public class SearchController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BookService bookService;

    @ApiOperation(value = "카테고리 검색 메소드")
    @ApiImplicitParam(name = "category", value = "카테고리 검색 ", dataType = "String")
    @GetMapping("/c-search/{category}")
    public ResponseEntity searchByCategory(@PathVariable String category) {

        List<Book> books = bookService.findAllByCategory(category);
        return bookService.getResponseEntity(books);
    }

    @ApiOperation(value = "카테고리/도서명 검색 메소드")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", value = "카테고리 검색 ", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "카테고리 검색 ", dataType = "String")}
    )
    @GetMapping("/cn-search/{category}/{name}")
    public ResponseEntity searchByCategoryName(@PathVariable String category, @PathVariable String name) {

        List<Book> books = bookService.findAllByCategoryAndName(category, name);
        return bookService.getResponseEntity(books);

    }

    @ApiOperation(value = "카테고리/작가명 검색 메소드")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", value = "카테고리 검색 ", dataType = "String"),
            @ApiImplicitParam(name = "writer", value = "작가명", dataType = "String")}
    )
    @GetMapping("/cw-search/{category}/{writer}")
    public ResponseEntity searchByCategoryWriter(@PathVariable String category, @PathVariable String writer) {

        List<Book> books = bookService.findAllByCategoryAndWriter(category, writer);
        return bookService.getResponseEntity(books);

    }

    @ApiOperation(value = "카테고리/도서명/작가명 검색 메소드")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", value = "카테고리", dataType = "String"),
            @ApiImplicitParam(name = "writer", value = "작가명", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "도서명", dataType = "String")
    })
    @GetMapping("/a-search/{category}/{writer}/{name}")
    public ResponseEntity searchByAll(@PathVariable String category, @PathVariable String writer, @PathVariable String name) {

        List<Book> books = bookService.findAllByCategoryAndWriterAndName(category, writer, name);
        return bookService.getResponseEntity(books);

    }

    @ApiOperation(value = "작가명/도서명 OR 메소드")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "writer", value = "작가명", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "도서명", dataType = "String")
    })
    @GetMapping("/won-search/{writer}/{name}")
    public ResponseEntity searchByWriterOrName(@PathVariable String writer, @PathVariable String name) {

        List<Book> books = bookService.findAllByWriterOrName(writer, name);
        return bookService.getResponseEntity(books);

    }

    @ApiOperation(value = "작가명/도서명 AND 메소드")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "writer", value = "작가명", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "도서명", dataType = "String")
    })
    @GetMapping("/wan-search/{writer}/{name}")
    public ResponseEntity searchByWriterAndName(@PathVariable String writer, @PathVariable String name) {

        List<Book> books = bookService.findAllByWriterAndName(writer, name);
        return bookService.getResponseEntity(books);

    }



}

