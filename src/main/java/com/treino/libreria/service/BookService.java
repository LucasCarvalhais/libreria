package com.treino.libreria.service;

import com.treino.libreria.model.Book;
import com.treino.libreria.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class BookService {

    @Autowired
    EntityManager entityManager;

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }
}
