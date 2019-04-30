package com.treino.libreria.service;

import com.treino.libreria.model.Book;
import com.treino.libreria.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
