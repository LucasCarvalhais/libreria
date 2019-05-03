package com.treino.libreria.model;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    private Long bookCode;

    private String title;
    private String description;
    private String author;
    private int edition;

    public Book() {
    }

    public Book(Long bookCode,
                String title,
                String description,
                String author,
                int edition) {
        this.bookCode = bookCode;
        this.title = title;
        this.description = description;
        this.author = author;
        this.edition = edition;
    }

    public Long getBookCode() {
        return bookCode;
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

}
