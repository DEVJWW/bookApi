package com.book.api.dto;

import com.book.api.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

     private String category;

     public CategoryDto(Category categoryData){
         category =categoryData.getCategory();
     }
}
