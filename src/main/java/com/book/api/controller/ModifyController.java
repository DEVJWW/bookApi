package com.book.api.controller;

import com.book.api.dto.CategoryModifyDto;
import com.book.api.dto.StatusModifyDto;
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
    //TODO CATEGORY UPDATE 추가 or 제거
    @Autowired
    BookService bookService;
    @Autowired
    ModelMapper modelMapper;

    @ApiOperation(value = "상태 변경 메소드")
    @ApiImplicitParam(name = "bookList", value = "상태값 변경 ", dataType = "list")
     @PutMapping("/s-modify")
    public ResponseEntity sModify(@RequestBody List<StatusModifyDto> bookList) {

        bookService.updateStatus(bookList);
        return new ResponseEntity(HttpStatus.OK);

    }

    @ApiOperation(value = "카테고리 변경 메소드")
    @ApiImplicitParam(name = "bookList", value = "카테고리 변경 ", dataType = "list")
    @PutMapping("/c-modify")
    public ResponseEntity cModify(@RequestBody List<CategoryModifyDto> bookList) {

        bookService.updateCategory(bookList);
        return new ResponseEntity(HttpStatus.OK);
    }
}
