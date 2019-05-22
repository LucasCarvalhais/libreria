package com.treino.libreria.service;

import com.treino.libreria.model.Book;
import com.treino.libreria.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book updateBook(Integer id, Book newBook) {
        Optional<Book> bookOptional = bookRepository.findByBookId(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.updateValues(newBook);
            return bookRepository.save(book);
        } else {
            // Throw book not found
            return null;
        }
    }
}
