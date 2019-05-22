package com.treino.libreria.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookTest {

    @Test
    public void shouldReturnTheUrlEncodedString() {
        Book book = new Book("Primero", "Primero Libro", "Lucas", 1);
        String expectedUrlEncoded = "title=Primero&description=Primero Libro&author=Lucas&edition=1";

        String output = book.toUrlEncoded();
        assertThat(output, is(expectedUrlEncoded));
    }

    @Test
    public void shouldReturnUrlEncodedWithNoDescriptionNorEdition() {
        Book book = new Book("Primero", null, "Lucas", 0);
        String expectedUrlEncoded = "title=Primero&author=Lucas";
        String output = book.toUrlEncoded();
        assertThat(output, is(expectedUrlEncoded));
    }

    @Test
    public void shouldUpdateAllValues() {
        Book oldBook = new Book("old", "libro viejo", "Lucas", 1);
        Book newBook = new Book("new", "libro nuevo", "Lucas", 1);

        oldBook.updateValues(newBook);

        assertThat(oldBook.getTitle(), is(newBook.getTitle()));
        assertThat(oldBook.getAuthor(), is(newBook.getAuthor()));
        assertThat(oldBook.getDescription(), is(oldBook.getDescription()));
        assertThat(oldBook.getEdition(), is(newBook.getEdition()));
    }

}
