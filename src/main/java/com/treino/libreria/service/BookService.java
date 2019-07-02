package com.treino.libreria.service;

import com.treino.libreria.exceptions.ResourceNotFoundException;
import com.treino.libreria.model.Book;
import com.treino.libreria.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void clearDatabase() {
        bookRepository.deleteAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findByBookId(int id) {
        Optional<Book> bookOptional = bookRepository.findByBookId(id);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        } else {
            throw new ResourceNotFoundException("Libro no encontrado :(");
        }
    }

    public Book updateBook(Integer id, Book newBook) {
        Book book = findByBookId(id);
        book.updateValues(newBook);
        return bookRepository.save(book);
    }

    public void deleteById(Integer id) {
        Book book = findByBookId(id);
        bookRepository.delete(book);
    }

    public List<Book> findByTitle(String title) {
        List<Book> books = bookRepository.findAll();
        List<Book> booksWithTitle = new ArrayList();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                booksWithTitle.add(book);
            }
        }

        if (booksWithTitle.isEmpty()) {
            throw new ResourceNotFoundException("Libro no encontrado :(");
        }

        return booksWithTitle;
    }
}
