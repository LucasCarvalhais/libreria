package com.treino.libreria.service;

import com.treino.libreria.model.Book;
import com.treino.libreria.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    BookService bookService = new BookService();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldInsertNewBook() {
        Book book = new Book();
        book.setTitle("Teste");
        book.setAuthor("Lucas");
        book.setDescription("Testando");
        book.setEdition(1);

        bookService.save(book);

        verify(bookRepository, times(1)).save(book);
    }

}
