package com.treino.libreria.controller;

import com.treino.libreria.exceptions.ResourceNotFoundException;
import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private BookController bookController;
    private BookService bookService;

    @Before
    public void setUp() {
        this.bookService = mock(BookService.class);
        this.bookController = new BookController(bookService);
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
    public void shouldThrowExceptionIfBookIsNull() {
        exception.expect(ResourceNotFoundException.class);
        exception.expectMessage("El libro no existe (es nulo) :(");

        this.bookController.saveBook(null);
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

}
