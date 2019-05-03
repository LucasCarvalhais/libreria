package com.treino.libreria.controller;

import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookControllerTest {

    private BookController bookController;
    private BookService bookService;

    @Before
    public void setUp() {
        this.bookService = mock(BookService.class);

        this.bookController = new BookController(bookService);
    }

    @Test
    public void shpuldReturnBookWhenSaveIt() {
        Book book = new Book(1L, "Teste", "Testando", "Lucas", 1);

        when(bookService.save(book)).thenReturn(book);
        Book bookResponse = this.bookController.createBook(book);

        assertThat(bookResponse).isEqualTo(book);
    }

    @Test
    public void ShouldReturnNullIfBookIsNull() {
        Book book = null;

        when(bookService.save(null)).thenReturn(null);
        Book bookResponse = this.bookController.createBook(book);

        assertThat(bookResponse).isNull();
    }

}
