package com.treino.libreria.service;

import com.treino.libreria.exceptions.DuplicatedResouceException;
import com.treino.libreria.exceptions.InvalidResourceException;
import com.treino.libreria.exceptions.ResourceNotFoundException;
import com.treino.libreria.model.Book;
import com.treino.libreria.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book) {
        if (!bookRepository.findByTitle(book.getTitle()).isEmpty()) {
            throw new DuplicatedResouceException("Libro ya existe :(");
        }
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book updateBook(Integer id, Book newBook) throws InvalidResourceException, ResourceNotFoundException {
        Optional<Book> bookOptional = bookRepository.findByBookId(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.updateValues(newBook);
            return bookRepository.save(book);
        } else {
            throw new ResourceNotFoundException("Libro no encontrado!");
        }
    }

    public void clearDB() {
        bookRepository.deleteAll();
    }

    public Book findByBookId(int id) {
        Optional<Book> bookOptional = bookRepository.findByBookId(id);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        } else {
            throw new ResourceNotFoundException("Libro no encontrado :(");
        }
    }
}
