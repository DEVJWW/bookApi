package com.book.api.entity;

import com.book.api.dto.CategoryDto;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;


@Entity
@Table(name = "CATEGORYDATA")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Integer id;

    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public Category(CategoryDto categoryDto) {
        category = categoryDto.getCategory();


    }

    public void setBook(Book book) {
        if (this.book != null) {
            book.getCategoryData().clear();
        }
        this.book = book;
        book.getCategoryData().add(this);


    }


}
