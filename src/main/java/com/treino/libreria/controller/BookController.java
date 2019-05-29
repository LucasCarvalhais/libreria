package com.treino.libreria.controller;

import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private RestTemplate restTemplate;

    public BookController(BookService bookService, RestTemplate restTemplate) {
        this.bookService = bookService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/new_book")
    public Book saveBook(@ModelAttribute Book book) {
        return bookService.save(book);
    }

    @GetMapping("/register_book_form")
    public ModelAndView getFormToRegisterBook() {
        return new ModelAndView("insertBook");
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books2")
    public ModelAndView getAllBooks2() {
        ModelAndView modelAndView = new ModelAndView("allBooks");
        List<Book> books = bookService.findAll();
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/search")
    public Book getBookByForm(@RequestParam Integer id) {
        return bookService.findByBookId(id);
    }

    @GetMapping("/{id}")
    public Book getBookByURL(@PathVariable("id") Integer id) {
        return bookService.findByBookId(id);
    }

    @GetMapping("/getById")
    public ModelAndView getBookFormToSearchById() {
        return new ModelAndView("searchBookById");
    }

    @PutMapping("/update_book/{id}")
    public Book updateBook(@PathVariable("id") Integer id, @RequestBody Book newBook) {
        return bookService.updateBook(id, newBook);
    }

    @PostMapping("/update_book")
    public RedirectView updateBookFromForm(@RequestParam Integer  id, @ModelAttribute Book newBook) {
        try {
            restTemplate.put("http://localhost:8080/book/update_book/" + id, newBook, id);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
        }
        return new RedirectView("/");
    }

    @GetMapping("/update_book_form")
    public ModelAndView getUpdateBookForm() {
        return new ModelAndView("updateBook");
    }
}
