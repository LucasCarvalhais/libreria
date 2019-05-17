package com.treino.libreria.service;

import com.treino.libreria.model.Book;
import com.treino.libreria.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class    BookServiceTest {

    BookRepository bookRepository;

    BookService bookService;

    @Before
    public void setUp() {
        this.bookRepository = mock(BookRepository.class);
        this.bookService = new BookService(bookRepository);
    }

    @Test
    public void shouldInsertNewBook() {
        Book book = new Book("Teste", "Testando", "Lucas", 1);

        when(bookRepository.save(book)).thenReturn(book);
        Book saved = bookService.save(book);

        assertThat(saved, is(book));
        verify(bookRepository).save(book);
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

}
