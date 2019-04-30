package com.treino.libreria.controller;

import com.treino.libreria.model.Book;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @PostMapping("/new_book")
    public Book createBook(@RequestBody Book book) {
        return book;
    }
}
