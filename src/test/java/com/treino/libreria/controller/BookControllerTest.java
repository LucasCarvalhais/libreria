package com.treino.libreria.controller;

import com.treino.libreria.exceptions.ResourceNotFoundException;
import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
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
        this.bookController = new BookController(bookService);
    }

    @Test
    public void shouldReturnBookWhenSuccessfullySaveIt() {
        Book book = new Book("Teste", "Testando", "Lucas", 1);

        when(bookService.save(book)).thenReturn(book);
        Book bookOutput = bookController.saveBook(book);

        assertThat(bookOutput).isEqualTo(book);
        verify(bookService).save(book);
    }

    @Test
    public void souldReturnNullIfBookIsNull() {
        when(bookService.save(null)).thenReturn(null);
        Book bookOutput = bookController.saveBook(null);

        assertThat(bookOutput).isNull();
        verify(bookService).save(null);
    }

    @Test
    public void shouldReturnAllBooks() {
        Book book1 = new Book("Teste", "Testando", "Lucas", 1);
        Book book2 = new Book("Libro", "Este es un libro", "Jimmy", 3);

        List<Book> books = Arrays.asList(book1, book2);
        when(bookService.findAll()).thenReturn(books);
        List<Book> booksOutput = bookController.getBooks();

        assertThat(booksOutput).isEqualTo(books);
        verify(bookService).findAll();
    }

    @Test
    public void shouldReturnEmptyListIfThereAreNoBooks() {
        when(bookService.findAll()).thenReturn(Collections.emptyList());
        List<Book> booksOutput = bookController.getBooks();

        assertThat(booksOutput).isEmpty();
        verify(bookService).findAll();
    }

    @Test
    public void shouldReturnBookById() {
        Book expectedBook = new Book("Teste", "Teste", "Teste", 1);

        when(bookService.findByBookId(1)).thenReturn(expectedBook);
        Book bookOutput = bookController.getBook(1);

        assertThat(bookOutput).isEqualTo(expectedBook);
        verify(bookService).findByBookId(1);
    }

    @Test
    public void shouldReturnBookByTitle() {
        Book expectedBook = new Book("Teste", "Teste", "Teste", 1);

        when(bookService.findByTitle("Teste")).thenReturn(Arrays.asList(expectedBook));
        List<Book> bookOutput = bookController.getBooks("Teste");

        assertThat(bookOutput.get(0)).isEqualTo(expectedBook);
        verify(bookService).findByTitle("Teste");
    }

    @Test
    public void shouldThrowExceptionWhenBookIsNotFound() {
        exception.expect(ResourceNotFoundException.class);

        when(bookService.findByBookId(2)).thenThrow(ResourceNotFoundException.class);

        bookController.getBook(2);
    }

    @Test
    public void shouldThrowExceptionWhenTitleIsNotFound() {
        exception.expect(ResourceNotFoundException.class);

        when(bookService.findByTitle("hola")).thenThrow(ResourceNotFoundException.class);

        bookController.getBooks("hola");
    }

    @Test
    public void shouldUpdateBook() {
        Book expectedBook = new Book("Segundo", "Libro Nuevo", "Lucas", 1);

        when(bookService.updateBook(1, expectedBook)).thenReturn(expectedBook);
        Book bookOutput = bookController.updateBook(1, expectedBook);

        assertThat(bookOutput).isEqualTo(expectedBook);
        verify(bookService).updateBook(1, expectedBook);
    }

    @Test
    public void shouldThrowExceptionWhenTryToUpdateUnexistingBook() {
        exception.expect(ResourceNotFoundException.class);

        Book book = new Book("foo", "foo", "foo", 2);
        when(bookService.updateBook(2, book)).thenThrow(ResourceNotFoundException.class);

        bookController.updateBook(2, book);
    }

    @Test
    public void shouldDeleteBook() {
        doNothing().when(bookService).deleteById(1);
        bookController.deleteBook(1);
        verify(bookService).deleteById(1);
    }

    @Test
    public void shouldThrowExceptionWhenTryToDeleteUnexistingBook() {
        exception.expect(ResourceNotFoundException.class);
        doThrow(ResourceNotFoundException.class).when(bookService).deleteById(2);
        bookController.deleteBook(2);
    }

}
