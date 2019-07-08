import React from 'react';

export const FormSearch = ({ bookId, handleChange, handleSubmit }) => 
    <form className="formulario" onSubmit={handleSubmit}>
        <label>
            <span className="legend">Pesquisar libro: </span>
            <input className="inputForm" type="number" name="bookId" value={bookId} onChange={handleChange} />
        </label>
        <input className="submitButton" type="submit" value="Pesquisar" />
    </form>;
