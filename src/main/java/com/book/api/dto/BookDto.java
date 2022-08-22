package com.book.api.dto;

import com.book.api.entity.Category;
import lombok.*;

import java.util.Set;


@Getter
@Setter
public class BookDto {

    private Integer id;
    private String writer;
    private String name;
    private String status;
    private String message;
    private Set<Category> categoryData;


}
