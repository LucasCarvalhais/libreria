package com.treino.libreria.model;

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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
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
        this.title = newBook.title;
        this.author = newBook.author;
        this.description = newBook.description;
        this.edition = newBook.edition;
    }

    @Override
    public String toString() {
        return title + " | " + author + " | " + description + " | " + edition;
    }
}
