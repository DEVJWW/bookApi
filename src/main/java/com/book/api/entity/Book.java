package com.book.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import springfox.documentation.annotations.ApiIgnore;
import javax.persistence.*;
import java.util.*;


// TODO GETTER SETTER 제거 ,builder생성
@Entity(name = "BOOKLIST")
@Getter
@Setter
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


    @OneToMany(mappedBy = "book" ,cascade = CascadeType.ALL ,orphanRemoval = true)
    private Set<Category> categoryData = new HashSet<>();


}
