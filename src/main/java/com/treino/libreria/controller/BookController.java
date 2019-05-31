package com.treino.libreria.controller;

import com.treino.libreria.exceptions.InvalidResourceException;
import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
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
    public ModelAndView saveBook(@ModelAttribute Book book) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("message", "¡Libro adicionado con éxito!");
        return modelAndView;
    }

    @GetMapping("/register_book_form")
    public ModelAndView getFormToRegisterBook() {
        return new ModelAndView("insertBook");
    }

    @GetMapping("/books")
    public ModelAndView getAllBooks() {
        ModelAndView modelAndView = new ModelAndView("allBooks");
        List<Book> books = bookService.findAllSorted();
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

    @PutMapping("/update_book/{id}")
    public Book updateBook(@PathVariable("id") Integer id, @RequestBody Book newBook) {
        return bookService.updateBook(id, newBook);
    }

    @PostMapping("/update_book")
    public RedirectView updateBookFromForm(@RequestParam Integer  id, @ModelAttribute Book newBook) {
        try {
            restTemplate.put("http://localhost:8080/book/update_book/" + id, newBook, id);
        } catch (HttpClientErrorException exception) {
            throw exception;
        }
        return new RedirectView("/");
    }

    @GetMapping("/update_book_form")
    public ModelAndView getUpdateBookForm() {
        return new ModelAndView("updateBook");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public RedirectView deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteById(id);
        RedirectView redirectView = new RedirectView("success");
        redirectView.addStaticAttribute("message", "Libro removido con éxito");
        return redirectView;
    }
}
