package com.treino.libreria.controller;

import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
        Book book = new Book("Teste", "Testando", "Lucas", 1);

        when(bookService.save(book)).thenReturn(book);
        Book bookResponse = this.bookController.createBook(book);

        assertThat(bookResponse).isEqualTo(book);
        verify(bookService).save(book);
    }

    @Test
    public void ShouldReturnNullIfBookIsNull() {
        Book book = null;

        when(bookService.save(null)).thenReturn(null);
        Book bookResponse = this.bookController.createBook(book);

        assertThat(bookResponse).isNull();
        verify(bookService, never()).save(book);
    }

    @Test
    public void shouldReturnThePageWithFrom() {
        ModelAndView expectedModelAndView = new ModelAndView("insertBook");
        ModelAndView modelAndView = bookController.getNewBookForm();
        assertThat(modelAndView.getViewName()).isEqualTo(expectedModelAndView.getViewName());
    }

}
