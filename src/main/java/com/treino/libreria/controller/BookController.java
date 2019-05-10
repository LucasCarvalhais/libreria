package com.treino.libreria.controller;

import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/new_book")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.save(book);
    }
}
