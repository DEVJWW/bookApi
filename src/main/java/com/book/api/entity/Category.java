package com.book.api.entity;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
// TODO GETTER SETTER 제거 ,builder생성
@Entity
@Table(name = "CATEGORYDATA")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "CATEGORY_ID")
    private Integer id;
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;



}
