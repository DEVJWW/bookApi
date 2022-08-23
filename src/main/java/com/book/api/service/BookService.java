package com.book.api.service;

import com.book.api.dto.*;
import com.book.api.entity.Book;
import com.book.api.entity.Category;
import com.book.api.repository.BookRepository;
import com.book.api.util.DeduplicationUtils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class BookService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BookRepository bookRepository;


    public List<BookDto> save(List<BookDto> bookList) {


        List<BookDto> returnList = new ArrayList<>();
        List<BookDto> newList = new ArrayList<>();

        for (int k = 0; k < bookList.size(); k++) {

            String writer = bookList.get(k).getWriter();
            String name = bookList.get(k).getName();

            if (bookRepository.existsByWriterAndName(writer, name)) {
                bookList.get(k).setMessage("Exist Book");
                returnList.add(bookList.get(k));

            } else if (bookList.get(k).getCategoryData().size() == 0) {
                bookList.get(k).setMessage("No Category");
                returnList.add(bookList.get(k));
            } else {
                newList.add(bookList.get(k));
            }
        }

        List<Book> books = newList.stream().map(BookDto -> modelMapper.map(BookDto, Book.class)).collect(Collectors.toList());

        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            int categorySize = books.get(i).getCategoryData().size();
            for (int j = 0; j < categorySize; j++) {

                Category category = new Category();
                category.setBook(books.get(i));
                category.setCategory(books.get(i).getCategoryData().get(j).getCategory());
                categories.add(category);

            }
            books.get(i).setCategoryData(categories);
        }
        bookRepository.saveAll(books);

        return returnList;
    }
    public void updateStatus(List<BookDto> bookList) {


        List<Book> books = new ArrayList<>();

        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookRepository.findById(bookList.get(i).getId())
                    .orElseThrow(() -> new NoSuchElementException());
            book.setStatus(bookList.get(i).getStatus());
            books.add(book);
        }
        bookRepository.saveAll(books);

    }

    public void updateCategory(List<BookDto> bookList) {

        List<Book> books = bookList.stream().map(BookDto -> modelMapper.map(BookDto, Book.class)).collect(Collectors.toList());

        List<Integer> ids = new ArrayList<>();
        for (Book book : books) {
            ids.add(book.getId());
        }
        List<Book> booksListById = bookRepository.findAllById(ids);

        for (int i = 0; i < booksListById.size(); i++) {
            booksListById.get(i).getCategoryData().clear();
        }
        bookRepository.saveAll(booksListById);

        for (int j = 0; j < booksListById.size(); j++) {
            List<Category> data = new ArrayList<>(books.get(j).getCategoryData());
            data = DeduplicationUtils.deduplication(data, Category::getCategory);
            for (int k = 0; k < data.size(); k++) {
                Category category = new Category();
                category.setBook(booksListById.get(j));
                category.setCategory(data.get(k).getCategory());
                booksListById.get(j).getCategoryData().add(category);
            }
        }
        bookRepository.saveAll(booksListById);

    }

    public List<Book> findAllByCategory(String category) {

        List<Book> bookList = bookRepository.findAllByCategoryData(category);

        return bookList;
    }


    public List<Book> findAllByCategoryAndName(String category, String name) {

        List<Book> bookList = bookRepository.findAllByCategoryDataAndNameContaining(category, name);

        return bookList;
    }

    public List<Book> findAllByCategoryAndWriter(String category, String writer) {
        List<Book> bookList = bookRepository.findAllByCategoryDataAndWriterContaining(category, writer);
        return bookList;
    }

    public List<Book> findAllByCategoryAndWriterAndName(String category, String writer, String name) {
        List<Book> bookList = bookRepository.findAllByCategoryDataAndWriterContainingAndNameContaining(category, writer, name);
        return bookList;
    }

    public List<Book> findAllByWriterOrName(String writer, String name) {
        List<Book> bookList = bookRepository.findALLByWriterContainingOrNameContaining(writer, name);
        return bookList;
    }

    public List<Book> findAllByWriterAndName(String writer, String name) {
        List<Book> bookList = bookRepository.findALLByWriterAndName(writer, name);
        return bookList;
    }


    public ResponseEntity getResponseEntity(List<Book> books) {
        List<BookDto> returnList = books.stream().map(BookDto::new).collect(Collectors.toList());

        if (returnList.size() == 0) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage("NO Book");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(returnList, HttpStatus.OK);
        }
    }
}




