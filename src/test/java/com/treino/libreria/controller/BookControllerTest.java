package com.treino.libreria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.treino.libreria.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldReturnBookWhenCreated() throws Exception {
        Book book = new Book();
        book.setTitle("Test");
        book.setAuthor("Lucas");
        book.setDescription("Testando... hola, que tal");
        book.setEdition(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/new_book")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(book))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Test")))
        .andExpect(content().string(containsString("Lucas")))
        .andExpect(content().string(containsString("Testando... hola, que tal")))
        .andExpect(content().string(containsString("1")));
    }

}
