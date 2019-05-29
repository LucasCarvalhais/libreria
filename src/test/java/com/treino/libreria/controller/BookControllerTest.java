package com.treino.libreria.controller;

import com.treino.libreria.exceptions.ResourceNotFoundException;
import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private RestTemplate restTemplate;
    private BookController bookController;
    private BookService bookService;

    @Before
    public void setUp() {
        this.bookService = mock(BookService.class);
        this.restTemplate = mock(RestTemplate.class);
        this.bookController = new BookController(bookService, restTemplate);
    }

    @Test
    public void shpuldReturnBookWhenSaveIt() {
        Book book = new Book("Teste", "Testando", "Lucas", 1);

        when(bookService.save(book)).thenReturn(book);
        Book bookResponse = this.bookController.saveBook(book);

        assertThat(bookResponse).isEqualTo(book);
        verify(bookService).save(book);
    }

    @Test
    public void shouldReturnThePageWithFrom() {
        ModelAndView expectedModelAndView = new ModelAndView("insertBook");

        ModelAndView modelAndView = bookController.getFormToRegisterBook();

        assertThat(modelAndView.getViewName()).isEqualTo(expectedModelAndView.getViewName());
    }

    @Test
    public void shouldReturnThePageWithListOfBooks() {
        Book book1 = new Book("Teste", "Testando", "Lucas", 1);
        Book book2 = new Book("Libro", "Este es un libro", "Jimmy", 3);
        bookController.saveBook(book1);
        bookController.saveBook(book2);

        List<Book> expectedBooks = Arrays.asList(book1, book2);
        when(bookService.findAll()).thenReturn(expectedBooks);

        List<Book> books = bookController.getAllBooks();

        assertThat(books).isEqualTo(expectedBooks);
        verify(bookService).findAll();
    }

    @Test
    public void shouldUpdateBook() {
        Book oldBook = new Book("Primero", "Libro Viejo", "Lucas", 1);
        Book newBook = new Book("Segundo", "Libro Nuevo", "Lucas", 1);

        when(bookService.updateBook(1, newBook)).thenReturn(newBook);
        bookController.saveBook(oldBook);
        Book bookResponse = bookController.updateBook(1, newBook);

        assertThat(bookResponse).isEqualTo(newBook);
    }

    @Test
    public void shouldReturnThePageWithFromToUpdate() {
        ModelAndView expectedModelAndView = new ModelAndView("updateBook");

        ModelAndView modelAndView = bookController.getUpdateBookForm();

        assertThat(modelAndView.getViewName()).isEqualTo(expectedModelAndView.getViewName());
    }

    @Test
    public void shouldReturnBookById() {
        Book expectedBook = new Book("Teste", "Teste", "Teste", 1);

        when(bookService.findByBookId(1)).thenReturn(expectedBook);

        Book bookResponse = bookController.getBookByForm(1);

        assertThat(bookResponse).isEqualTo(expectedBook);
        verify(bookService).findByBookId(1);
    }

    @Test
    public void shouldThrowExceptionWhenBookIsNotFound() {
        exception.expect(ResourceNotFoundException.class);

        when(bookService.findByBookId(2)).thenThrow(ResourceNotFoundException.class);

        bookController.getBookByForm(2);
    }

    @Test
    public void shouldHandlePUTMethodFromFormWithPOST() {
        Book book = new Book("Teste", "Teste", "Teste", 1);

        doNothing().when(restTemplate).put("http://localhost:8080/book/update_book/1", book, 1);

        RedirectView expectedView = new RedirectView("/");
        RedirectView redirectView = bookController.updateBookFromForm(1, book);

        assertThat(redirectView.getUrl()).isEqualTo(expectedView.getUrl());
    }

}
