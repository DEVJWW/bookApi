package com.book.api.dto;

import com.book.api.entity.Book;
import com.book.api.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CategoryDto {

    private String category;

    public CategoryDto(Category categoryData) {

        category = categoryData.getCategory();
    }


}
