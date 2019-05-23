package com.treino.libreria.service;

import com.treino.libreria.exceptions.DuplicatedResouceException;
import com.treino.libreria.exceptions.ResourceNotFoundException;
import com.treino.libreria.model.Book;
import com.treino.libreria.repository.BookRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class    BookServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    BookRepository bookRepository;
    BookService bookService;

    @Before
    public void setUp() {
        this.bookRepository = mock(BookRepository.class);
        this.bookService = new BookService(bookRepository);
    }

    @Test
    public void shouldSaveNewBook() {
        Book bookInput = new Book("Teste", "Testando", "Lucas", 1);

        when(bookRepository.save(bookInput)).thenReturn(bookInput);
        Book bookOutput = bookService.save(bookInput);

        assertThat(bookOutput, is(bookInput));
        verify(bookRepository).save(bookInput);
    }

    @Test
    public void shouldFindAllBooks() {
        Book book1 = new Book("Teste", "Testando", "Lucas", 1);
        Book book2 = new Book("Teste2", "Testando2", "Lucas2", 2);
        Book book3 = new Book("Teste3", "Testando3", "Lucas3", 3);
        List<Book> expectedBooks = Arrays.asList(book1, book2, book3);

        when(bookRepository.findAll()).thenReturn(expectedBooks);
        List<Book> books = bookService.findAll();

        assertThat(books, is(expectedBooks));
        verify(bookRepository).findAll();
    }

    @Test
    public void shouldUpdateBook() {
        Book oldBook = new Book("Old", "Libro viejo", "Lucas", 1);
        Book newBook = new Book("new", "Libro nuevo", "Lucas", 1);

        when(bookRepository.findByBookId(1)).thenReturn(java.util.Optional.of(oldBook));
        oldBook.updateValues(newBook);
        when(bookRepository.save(oldBook)).thenReturn(newBook);

        Book output = bookService.updateBook(1, newBook);
        assertEquals(newBook, output);
        assertEquals(oldBook, output);
        verify(bookRepository).findByBookId(1);
        verify(bookRepository).save(oldBook);
    }

    @Test
    public void shouldThrowExceptionIfBookAlreadyExists() {
        Book bookExists = new Book("Teste", "Teste", "Teste", 1);

        when(bookRepository.findByTitle("Teste")).thenReturn(Arrays.asList(bookExists));

        exception.expect(DuplicatedResouceException.class);
        exception.expectMessage("Libro ya existe :(");

        bookService.save(bookExists);
    }

    @Test
    public void shouldReturnBookWhenFindIt() {
        Book expectedBook = new Book("Teste", "Teste", "Teste", 1);

        when(bookRepository.findByBookId(1)).thenReturn(java.util.Optional.of(expectedBook));

        Book book = bookService.findByBookId(1);
        assertEquals(expectedBook, book);
        verify(bookRepository).findByBookId(1);
    }

    @Test
    public void shouldThrowExceptionWhenBookIsNotFound() {
        exception.expect(ResourceNotFoundException.class);
        exception.expectMessage("Libro no encontrado :(");

        when(bookRepository.findByBookId(4)).thenReturn(Optional.empty());

        bookService.findByBookId(4);
        verify(bookRepository).findByBookId(4);
    }

}
