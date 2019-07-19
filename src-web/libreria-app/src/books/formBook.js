import React from 'react';

const FormBook = ({ book, handleChange, handleSubmit }) => 
    <form className="formulario" onSubmit={handleSubmit}>
        <label>
            <span className="legend">Título: </span>
            <input className="inputForm" type="text" name="title" value={book.title} onChange={handleChange} />
        </label><br />
        <label>
            <span className="legend">Descripción: </span>
            <input className="inputForm" type="text" name="description" value={book.description} onChange={handleChange} />
        </label><br />
        <label>
            <span className="legend">Autor: </span>
            <input className="inputForm" type="text" name="author" value={book.author} onChange={handleChange} />
        </label><br />
        <label>
            <span className="legend">Edición: </span>
            <input className="inputForm" type="number" name="edition" value={book.edition} onChange={handleChange} />
        </label><br />
        <input className="submitButton" type="submit" value="Cadastrar" />
    </form>;

export default FormBook;