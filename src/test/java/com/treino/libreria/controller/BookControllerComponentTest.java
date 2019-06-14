package com.treino.libreria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.*;
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

    @Autowired
    BookService bookService;

    @After
    public void clearDB() {
        bookService.clearDatabase();
    }

    @Test
    public void shouldSaveBook() throws Exception {
        Book book = new Book("Teste", "Testando... Hola, que tal", "Lucas", 2);

        mockMvc.perform(MockMvcRequestBuilders.post("/books/")
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

    @Test
    public void shouldListAllAvailableBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void shouldFindBookById() throws Exception {
        Book book = new Book("Teste", "Testando... Hola, que tal", "Lucas", 2);

        mockMvc.perform(MockMvcRequestBuilders.post("/books/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(book))
                .accept(MediaType.APPLICATION_JSON));

        book = bookService.findAll().get(0);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + book.getBookId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(equalTo(objectMapper.writeValueAsString(book))));
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        Book oldBook = new Book("Primero", "Libro Viejo", "Lucas", 1);
        Book newBook = new Book("Segundo", "Libro Nuevo", "Lucas", 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/books/")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(objectMapper.writeValueAsString(oldBook))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        Book book = bookService.findAll().get(0);
        mockMvc.perform(MockMvcRequestBuilders.put("/books/" + book.getBookId())
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(objectMapper.writeValueAsString(newBook))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("Segundo")))
                .andExpect(content().string(not(containsString("Primero"))))
                .andExpect(content().string(containsString("Libro Nuevo")))
                .andExpect(content().string(not(containsString("Libro Viejo"))))
                .andExpect(content().string(containsString("Lucas")))
                .andExpect(content().string(containsString("1")));
    }

    @Test
    public void shouldDeleteBookById() throws Exception {
        Book book = new Book("Teste", "Testando... Hola, que tal", "Lucas", 2);

        mockMvc.perform(MockMvcRequestBuilders.post("/books/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(book))
                .accept(MediaType.APPLICATION_JSON));

        book = bookService.findAll().get(0);
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/" + book.getBookId()))
                .andExpect(status().is(204));
    }

    // Teste para PUT /books e DELETE /books ?

}
