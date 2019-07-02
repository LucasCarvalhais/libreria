package com.treino.libreria.service;

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
    public void shouldFindBookByTitle() {
        Book book = new Book("Teste", "testando", "Lucas", 1);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
        List<Book> bookOutput = bookService.findByTitle("Teste");

        assertThat(bookOutput.get(0), is(book));
        assertThat(bookOutput.size(), is(1));
        verify(bookRepository).findAll();
    }

    @Test
    public void shouldFindBookByPartOfTitle() {
        Book book1 = new Book("Mario Kart Stadium", "Teste 1", "Lucas", 1);
        Book book2 = new Book("Sweet Sweet Canyon", "Teste 2", "Lucas", 2);
        Book book3 = new Book("Mario Circuit", "Teste 3", "Lucas", 3);
        Book book4 = new Book("Toad Circuit", "Teste 4", "Lucas", 4);
        Book book5 = new Book("Rainbow Road", "Teste 5", "Lucas", 5);
        Book book6 = new Book("Mario Raceway", "Teste 6", "Lucas", 6);
        Book book7 = new Book("Wario Stadium", "Teste 7", "Lucas", 7);
        List<Book> booksDB = Arrays.asList(book1, book2, book3, book4, book5, book6, book7);
        List<Book> expectedOutput = Arrays.asList(book1, book3, book6);

        when(bookRepository.findAll()).thenReturn(booksDB);

        List<Book> output = bookService.findByTitle("Mario");

        assertThat(output, is(expectedOutput));
    }

    @Test
    public void shouldFindBookByPartOfTitleIgnoreCaseSensitive() {
        Book book1 = new Book("Mario Kart Stadium", "Teste 1", "Lucas", 1);
        Book book2 = new Book("Sweet Sweet Canyon", "Teste 2", "Lucas", 2);
        Book book3 = new Book("Mario Circuit", "Teste 3", "Lucas", 3);
        Book book4 = new Book("Toad Circuit", "Teste 4", "Lucas", 4);
        Book book5 = new Book("Rainbow Road", "Teste 5", "Lucas", 5);
        Book book6 = new Book("Mario Raceway", "Teste 6", "Lucas", 6);
        Book book7 = new Book("Wario Stadium", "Teste 7", "Lucas", 7);
        List<Book> booksDB = Arrays.asList(book1, book2, book3, book4, book5, book6, book7);
        List<Book> expectedOutput = Arrays.asList(book1, book3, book6);

        when(bookRepository.findAll()).thenReturn(booksDB);

        List<Book> output = bookService.findByTitle("mARIO");

        assertThat(output, is(expectedOutput));
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
    public void shouldReturnBookWhenFindIt() {
        Book expectedBook = new Book("Teste", "Teste", "Teste", 1);

        when(bookRepository.findByBookId(1)).thenReturn(java.util.Optional.of(expectedBook));

        Book book = bookService.findByBookId(1);
        assertEquals(expectedBook, book);
        verify(bookRepository).findByBookId(1);
    }

    @Test
    public void shouldThrowExceptionWhenBookIdIsNotFound() {
        exception.expect(ResourceNotFoundException.class);
        exception.expectMessage("Libro no encontrado :(");

        bookService.findByBookId(4);
    }

    @Test
    public void shouldThrowExceptionWhenBookTitleIsNotFound() {
        exception.expect(ResourceNotFoundException.class);
        exception.expectMessage("Libro no encontrado :(");

        bookService.findByTitle("test");
    }

}
