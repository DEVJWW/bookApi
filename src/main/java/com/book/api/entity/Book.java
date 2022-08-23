package com.book.api.entity;

import com.book.api.dto.BookDto;
import com.book.api.dto.CategoryDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@Entity(name = "BOOKLIST")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@ToString
public class Book {
    @Id
    @Column(name = "BOOK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String writer;
    private String name;
    private String status;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categoryData = new ArrayList<>();


    public Book(BookDto bookDto) {
        id = bookDto.getId();
        writer = bookDto.getWriter();
        name = bookDto.getName();
        status = bookDto.getStatus();
        categoryData = bookDto.getCategoryData().stream().map(Category::new).collect(Collectors.toList());


    }

    public void addCategory(Category category) {
        this.categoryData.add(category);
        if (category.getBook() != this) {
            category.setBook(this);
        }

    }
}
