package com.treino.libreria.model;

import com.treino.libreria.exceptions.InvalidResourceException;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name = "book_generator",
            sequenceName = "book_sequence",
            initialValue = 1,
            allocationSize = 1)
    private Integer bookId;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String author;

    private int edition;

    public Book() {
    }

    public Book(String title,
                String description,
                String author,
                int edition) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.edition = edition;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public int getEdition() {
        return edition;
    }

    public String toUrlEncoded() {
        StringBuilder urlEncoded = new StringBuilder();

        urlEncoded.append("title=" + getTitle());
        if (description != null) {
            urlEncoded.append("&description=" + getDescription());
        }
        urlEncoded.append("&author=" + getAuthor());
        if (edition > 0) {
            urlEncoded.append("&edition=" + getEdition());
        }

        return urlEncoded.toString();
    }

    public void updateValues(Book newBook) {
        if (newBook == null) {
            throw new InvalidResourceException("¡Estás intentando alterar con libro nulo! 😱");
        }
        if (newBook.title == null || newBook.author == null) {
            throw new InvalidResourceException("¡Hay que poner el título y/o el autor del libro! :(");
        }

        this.title = newBook.title;
        this.author = newBook.author;
        this.description = newBook.description;
        this.edition = newBook.edition;
    }

    @Override
    public String toString() {
        return "bookId: " + bookId + "\n" +
                "title: " + title + "\n" +
                "description: " + description + "\n" +
                "author: " + author + "\n" +
                "edition: " + edition + "\n";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Book book = (Book) object;
        return (this.bookId == book.bookId
            && this.title == book.title
            && this.description == book.description
            && this.author == book.author
            && this.edition == book.edition);
    }
}
