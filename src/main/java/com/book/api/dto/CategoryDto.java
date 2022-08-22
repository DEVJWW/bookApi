package com.book.api.dto;

import com.book.api.entity.Category;
import lombok.Data;

@Data
public class CategoryDto {

     private String category;

    public CategoryDto() {

    }
     public CategoryDto(Category categoryData){
         category =categoryData.getCategory();
     }
}
