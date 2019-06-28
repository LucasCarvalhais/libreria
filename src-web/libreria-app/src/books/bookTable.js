import React from 'react';

export const BookTable = ({ books, isLoading }) => <div>
    {isLoading
        ? <div><p className="loading">Cargando...</p></div>
        : <div>
            <table className="bookTable">
                <tbody>
                    <tr>
                        <th>Código</th>
                        <th>Título</th>
                        <th>Descripción</th>
                        <th>Persona autora</th>
                        <th>Edición</th>
                    </tr>
                    {books.map(book => <tr key={book.bookId}>
                        <td>{book.bookId}</td>
                        <td>{book.title}</td>
                        <td>{book.description}</td>
                        <td>{book.author}</td>
                        <td>{book.edition}</td>
                    </tr>)}
                </tbody>
            </table>
        </div>}
</div>;
