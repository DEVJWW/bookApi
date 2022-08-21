package com.book.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "대여가능상태 변경 요청형식")
public class StatusModifyDto {

    @ApiModelProperty(value="book_id", example = "도서ID")
    private Integer id;
    @ApiModelProperty(value="status", example = "도서 이름")
    private String status;

}
