import React from 'react';

export const Navigation = ({ setPageName }) => 
    <nav>
        <table>
            <tbody>
                <tr>
                    <td onClick={() => setPageName('home')}>
                        Pagina de inicio
                    </td>
                    <td onClick={() => setPageName('list')}>
                        Listar los libros
                    </td>
                    <td onClick={() => setPageName('create')}>
                        Cadastrar nuevo libro
                    </td>
                    <td onClick={() => setPageName('update')}>
                        Atualizar el libro
                    </td>
                    <td onClick={() => setPageName('delete')}>
                        Deletar el libro
                    </td>
                </tr>
            </tbody>
        </table>
    </nav>