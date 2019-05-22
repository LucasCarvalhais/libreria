package com.treino.libreria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.treino.libreria.model.Book;
import com.treino.libreria.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
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

    @Test
    public void shouldReturnBookWhenItIsInserted() throws Exception {
        Book book = new Book("Teste", "Testando... Hola, que tal", "Lucas", 2);

        mockMvc.perform(MockMvcRequestBuilders.post("/book/new_book")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .content(book.toUrlEncoded())
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("Teste")))
                .andExpect(content().string(containsString("Testando... Hola, que tal")))
                .andExpect(content().string(containsString("Lucas")))
                .andExpect(content().string(containsString("2")));

    }

    @Test
    public void shouldGetTheForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/register_book_form"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(content().string(containsString("<title>Cadastro del libro</title>")));
    }

    @Test
    public void shouldGetTheFormToUpdate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/update_book_form"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(content().string(containsString("<title>Atualización del libro</title>")));
    }

    @Test
    public void shouldListAllAvailableBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        Book oldBook = new Book("Primero", "Libro Viejo", "Lucas", 1);
        Book newBook = new Book("Segundo", "Libro Nuevo", "Lucas", 1);

        mockMvc.perform(MockMvcRequestBuilders.post("/book/new_book")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .content(oldBook.toUrlEncoded())
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("Primero")))
                .andExpect(content().string(containsString("Libro Viejo")))
                .andExpect(content().string(containsString("Lucas")))
                .andExpect(content().string(containsString("1")));

        mockMvc.perform(MockMvcRequestBuilders.put("/book/update_book/1")
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
                .andExpect(content().string(containsString("1")));;
    }

}
