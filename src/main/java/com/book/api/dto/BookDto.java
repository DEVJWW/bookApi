package com.book.api.dto;

import com.book.api.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
