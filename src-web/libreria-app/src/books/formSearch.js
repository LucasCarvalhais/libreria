import React from 'react';
export const FormSearch = ({ bookId, handleChange, handleSearch }) => <form className="formulario">
    <label>
        <span className="legend">Pesquisar libro: </span>
        <input className="inputForm" type="number" name="bookId" value={bookId} onChange={handleChange} />
    </label>
    <input className="submitButton" type="submit" value="Pesquisar" onClick={handleSearch} />
</form>;
