package com.book.api.repository;

import com.book.api.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    boolean existsByWriterAndName(String writer, String name);

    List<Book> findALLByWriterAndName(String writer, String name);

    List<Book> findALLByWriterContainingOrNameContaining(String writer, String name);

    @Query("SELECT DISTINCT b FROM BOOKLIST b JOIN FETCH b.categoryData c WHERE c.category =:category")
    List<Book> findAllByCategoryData(String category);

    @Query("SELECT DISTINCT b FROM BOOKLIST b JOIN FETCH b.categoryData c WHERE c.category =:category AND b.name LIKE %:name%")
    List<Book> findAllByCategoryDataAndNameContaining(String category, String name);

    @Query("SELECT DISTINCT b FROM BOOKLIST b JOIN FETCH b.categoryData c WHERE c.category =:category AND b.writer LIKE %:writer%")
    List<Book> findAllByCategoryDataAndWriterContaining(String category, String writer);

    @Query(
            value = "SELECT * FROM (" +
                    "SELECT distinct b.* FROM BOOKLIST b inner join categorydata c on b.BOOK_ID =c.BOOK_ID WHERE c.category = ?1" +
                    ") d " +
                    "WHERE d.writer like ?2% or d.name like ?3%",
            nativeQuery = true)
    List<Book> findAllByCategoryDataAndWriterContainingAndNameContaining(String category, String writer, String name);


}
