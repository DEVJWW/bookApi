package com.book.api.controller;

import com.book.api.dto.BookDto;
import com.book.api.service.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"도서 Insert Controller"})
@RestController
public class InsertController {
    @Autowired
    BookService bookService;


    @ApiOperation(value = "도서이름 / 도서저자 / 카테고리 INSERT 메소드")
    @ApiImplicitParam(name = "bookList", value = "ID , MESSAGE 제외 사용", dataType = "list")
    @ApiResponses({@ApiResponse(code = 200, message = "성공시 200코드 리턴"), @ApiResponse(code = 400, message = "실패시 400리턴 + INSERT 실패 DATA ")})
    @PostMapping("/insert")
    public ResponseEntity insert(@RequestBody List<BookDto> bookList) {

        List<BookDto> returnList = bookService.save(bookList);
        if (returnList.size() == 0) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(returnList, HttpStatus.BAD_REQUEST);
        }


    }

}
