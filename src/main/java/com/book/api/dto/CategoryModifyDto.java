package com.book.api.dto;

import com.book.api.entity.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@ApiModel(value = "카테고리 변경 요청형식")
public class CategoryModifyDto {
    
    @ApiModelProperty(value="id", example = "도서ID")
    private Integer id;
    private Set<Category> category;


}
