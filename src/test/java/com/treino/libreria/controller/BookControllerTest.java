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
    public void shpuldReturnSuccessPageWhenSaveBook() {
        Book book = new Book("Teste", "Testando", "Lucas", 1);

        when(bookService.save(book)).thenReturn(book);
        ModelAndView expectedModelAndView = new ModelAndView("success");
        expectedModelAndView.addObject("message", "¡Libro adicionado con éxito!");

        ModelAndView modelAndView = this.bookController.saveBook(book);

        assertThat(modelAndView.getViewName()).isEqualTo(expectedModelAndView.getViewName());
        assertThat(modelAndView.getModel()).isEqualTo(expectedModelAndView.getModel());
        verify(bookService).save(book);
    }

    @Test
    public void shouldReturnThePageWithListOfBooks() {
        Book book1 = new Book("Teste", "Testando", "Lucas", 1);
        Book book2 = new Book("Libro", "Este es un libro", "Jimmy", 3);

        List<Book> books = Arrays.asList(book1, book2);
        when(bookService.findAllSorted()).thenReturn(books);

        ModelAndView expectedModelAndView = new ModelAndView("allBooks");
        expectedModelAndView.addObject("books", books);

        ModelAndView modelAndView = bookController.getAllBooks();

        assertThat(modelAndView.getView()).isEqualTo(expectedModelAndView.getView());
        assertThat(modelAndView.getModel()).isEqualTo(expectedModelAndView.getModel());
        verify(bookService).findAllSorted();
    }

    @Test
    public void shouldReturnBookById() {
        Book expectedBook = new Book("Teste", "Teste", "Teste", 1);

        when(bookService.findByBookId(1)).thenReturn(expectedBook);

        Book bookResponseForm = bookController.getBook(1);

        assertThat(bookResponseForm).isEqualTo(expectedBook);
        verify(bookService).findByBookId(1);
    }

    @Test
    public void shouldThrowExceptionWhenBookIsNotFound() {
        exception.expect(ResourceNotFoundException.class);

        when(bookService.findByBookId(2)).thenThrow(ResourceNotFoundException.class);

        bookController.getBook(2);
    }

    @Test
    public void shouldUpdateBook() {
        Book newBook = new Book("Segundo", "Libro Nuevo", "Lucas", 1);

        when(bookService.updateBook(1, newBook)).thenReturn(newBook);
        Book bookResponse = bookController.updateBook(1, newBook);

        assertThat(bookResponse).isEqualTo(newBook);
    }

    @Test
    public void shouldHandlePUTMethod() {
        Book book = new Book("Teste", "Teste", "Teste", 1);

        doNothing().when(restTemplate).put("http://localhost:8080/book/update_book/1", book, 1);

        ModelAndView expectedModelAndView = new ModelAndView("success");
        expectedModelAndView.addObject("message", "Libro atualizado con éxito");

        ModelAndView modelAndView = bookController.updateBookHandler(1, book);

        assertThat(modelAndView.getView()).isEqualTo(expectedModelAndView.getView());
        assertThat(modelAndView.getModel()).isEqualTo(expectedModelAndView.getModel());
        verify(restTemplate).put("http://localhost:8080/book/update_book/1", book, 1);
    }

    @Test
    public void shouldDeleteBook() {
        doNothing().when(bookService).deleteById(1);

        bookController.deleteBook(1);
        verify(bookService).deleteById(1);
    }

    @Test
    public void shouldHandleDELETEMethod() {
        doNothing().when(restTemplate).delete("http://localhost:8080/book/delete/1");

        ModelAndView expectedModelAndView = new ModelAndView("success");
        expectedModelAndView.addObject("message", "Libro removido con éxito");

        ModelAndView modelAndView = bookController.deleteBookHandler(1);

        assertThat(modelAndView.getView()).isEqualTo(expectedModelAndView.getView());
        assertThat(modelAndView.getModel()).isEqualTo(expectedModelAndView.getModel());
        verify(restTemplate).delete("http://localhost:8080/book/delete/1");
    }

}
