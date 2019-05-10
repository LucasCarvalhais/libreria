package com.treino.libreria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class  BookControllerComponentTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    BookService bookService;

    @Before
    public void setUp() {
        bookService = mock(BookService.class);
    }

    @Test
    public void shouldReturnBookWhenCreated() throws Exception {
        Book book = new Book("Teste",
                "Testando... Hola, que tal",
                "Lucas",
                2);

        when(bookService.save(book)).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.post("/new_book")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(book))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("Teste")))
                .andExpect(content().string(containsString("Testando... Hola, que tal")))
                .andExpect(content().string(containsString("Lucas")))
                .andExpect(content().string(containsString("2")));

    }

}
