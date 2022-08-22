package com.book.api.dto;

import com.book.api.entity.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@ApiModel(value = "도서관련 정보")
public class InsertDto {

    @ApiModelProperty(value="writer", example = "도서 저자",position = 2)
    private String writer;
    @ApiModelProperty(value="name", example = "도서 이름",position = 3)
    private String name;
    @ApiModelProperty(value="message", example = "리턴 응답값(오류)",position = 5)
    private String message;
    @ApiModelProperty()
    private Set<Category> categoryData;





}
