package com.treino.libreria.controller;

import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/new_book")
    public Book saveBook(@ModelAttribute Book book) {
        if (book == null) return null;
        return bookService.save(book);
    }

    @GetMapping("/new_book_form")
    public ModelAndView getNewBookForm() {
        return new ModelAndView("insertBook");
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }
}
