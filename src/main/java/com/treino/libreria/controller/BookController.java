package com.treino.libreria.controller;

import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@CrossOrigin
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") Integer id) {
        return bookService.findByBookId(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") Integer id, @RequestBody Book newBook) {
        return bookService.updateBook(id, newBook);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteById(id);
    }

    @GetMapping("title/{title}")
    public List<Book> getBooks(@PathVariable("title") String title) {
        return bookService.findByTitle(title);
    }
}
