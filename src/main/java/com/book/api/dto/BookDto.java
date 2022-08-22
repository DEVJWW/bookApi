package com.book.api.dto;

import com.book.api.entity.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
public class BookDto {

    private Integer id;
    @ApiModelProperty(value = "writer",example = "작가명")
    private String writer;
    @ApiModelProperty(value = "name",example = "도서이름")
    private String name;
    @ApiModelProperty(value = "status",example = "대여상태 Y or N")
    private String status;
    @ApiModelProperty(value = "massage",example = "RETURN 메세지")
    private String message;
    private List<CategoryDto> categoryData;
    public BookDto() {

    }
    public BookDto(Book book){

        id= book.getId();
        writer= book.getName();
        status=book.getStatus();
        categoryData=book.getCategoryData().stream().map(CategoryDto::new).collect(Collectors.toList());

    }

}
