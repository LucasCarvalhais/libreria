package com.treino.libreria.service;

import com.treino.libreria.model.Book;
import com.treino.libreria.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;

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
        Book book = new Book("Teste",
                "Testando",
                "Lucas",
                1);

        when(bookRepository.save(book)).thenReturn(book);
        Book saved = bookService.save(book);

        assertThat(saved, is(book));
        verify(bookRepository).save(book);
    }

}
