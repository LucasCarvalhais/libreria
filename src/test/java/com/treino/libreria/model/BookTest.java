package com.treino.libreria.model;

import com.treino.libreria.exceptions.InvalidResourceException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

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

    @Test
    public void shouldThrowInvalidResourceWhenTryToInsertBookWithNullFields() {
        Book currentBook = new Book("title", "description", "author", 2);
        Book invalidBook = new Book(null, null, "AUTHOR", 1);

        exceptionRule.expect(InvalidResourceException.class);
        exceptionRule.expectMessage("¡Hay que poner el título y/o el autor del libro! :(");

        currentBook.updateValues(invalidBook);
    }

    @Test
    public void shouldThrowExceptionIfNewBookIsNull() {
        Book book = new Book("foo", "foo", "foo", 2);

        exceptionRule.expect(InvalidResourceException.class);
        exceptionRule.expectMessage("¡Estás intentando alterar con libro nulo! 😱");

        book.updateValues(null);
    }

}
