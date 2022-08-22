package com.book.api.controller;

import com.book.api.dto.BookDto;
import com.book.api.service.BookService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"도서 Modify Controller"})
@ApiResponses({
        @ApiResponse(code = 200, message = "성공 200코드만 리턴")
})
@RestController
public class ModifyController {

    @Autowired
    BookService bookService;
    @Autowired
    ModelMapper modelMapper;

    @ApiOperation(value = "상태 변경 메소드")
    @ApiImplicitParam(name = "bookList", value = "도서ID값과 STATUS만사용", dataType = "list")
    @PutMapping("/s-modify")
    public ResponseEntity sModify(@RequestBody List<BookDto> bookList) {

        bookService.updateStatus(bookList);

        return new ResponseEntity(HttpStatus.OK);

    }

    @ApiOperation(value = "카테고리 변경 메소드")
    @ApiImplicitParam(name = "bookList", value = "도서 ID값과 categoryData만 사용", dataType = "list")
    @PutMapping("/c-modify")
    public ResponseEntity cModify(@RequestBody List<BookDto> bookList) {

        bookService.updateCategory(bookList);

        return new ResponseEntity(HttpStatus.OK);
    }
}
