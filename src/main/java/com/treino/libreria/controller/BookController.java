package com.treino.libreria.controller;

import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RedirectAttributesMethodArgumentResolver;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
public class BookController {

    RestTemplate restTemplate = new RestTemplate();

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

    @PutMapping("/new_book/{id}")
    public Book updateBook(@PathVariable("id") Integer id, @RequestBody Book newBook) {
        return bookService.updateBook(id, newBook);
    }

    @PostMapping("/update_book")
    public RedirectView updateBookByPost(@RequestParam Integer  id, @ModelAttribute Book newBook) throws IOException {
        restTemplate.put("http://localhost:8080/new_book/" + id, newBook, id);
        return new RedirectView("/bienvenido");
    }

    @GetMapping("/update_book_form")
    public ModelAndView getUpdateBookForm() {
        return new ModelAndView("updateBook");
    }
}
