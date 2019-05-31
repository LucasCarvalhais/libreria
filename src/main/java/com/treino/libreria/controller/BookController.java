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

    @GetMapping("/books")
    public ModelAndView getAllBooks() {
        ModelAndView modelAndView = new ModelAndView("allBooks");
        modelAndView.addObject("books", bookService.findAllSorted());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") Integer id) {
        return bookService.findByBookId(id);
    }

    @PutMapping("/update_book/{id}")
    public Book updateBook(@PathVariable("id") Integer id, @RequestBody Book newBook) {
        return bookService.updateBook(id, newBook);
    }

    @PostMapping("/update_book")
    public ModelAndView updateBookHandler(@RequestParam Integer  id, @ModelAttribute Book newBook) {
        try {
            restTemplate.put("http://localhost:8080/book/update_book/" + id, newBook, id);
        } catch (HttpClientErrorException exception) {
            throw new InvalidResourceException(exception.getMessage());
        }

        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("message", "Libro atualizado con éxito");
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteById(id);
    }

    @GetMapping("/delete")
    public ModelAndView deleteBookHandler(@RequestParam Integer id) {
        try {
            restTemplate.delete("http://localhost:8080/book/delete/" + id);
        } catch (HttpClientErrorException exception) {
            throw new InvalidResourceException(exception.getMessage());
        }

        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("message", "Libro removido con éxito");
        return modelAndView;
    }


}
