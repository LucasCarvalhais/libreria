package com.treino.libreria.model;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long bookCode;

    @Column(columnDefinition = "text")
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String autor;

    @Column(columnDefinition = "number")
    private int edition;

    public Long getBookCode() {
        return bookCode;
    }

    public void setBookCode(Long bookCode) {
        this.bookCode = bookCode;
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

    public String getAutor() {
        return autor;
    }

    public void setAuthor(String autor) {
        this.autor = autor;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "{ \"bookCode\": \"" + getBookCode() + "\"," +
                " \"title\": \"" + getTitle() + "\"," +
                " \"description\": \"" + getDescription() + "\"," +
                " \"autor\": \"" + getAutor() + "\"," +
                " \"edition\": \"" + getEdition() + "\"," +
                "}";
    }
}
