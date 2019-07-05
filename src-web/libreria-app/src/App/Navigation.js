import React from 'react';
import './Navigation.css';

export const Navigation = ({ setPageName }) => 
    <nav>
        <table>
            <tbody>
                <tr>
                    <td><p onClick={() => setPageName('home')}>
                        Pagina de inicio
                    </p></td>
                    <td><p onClick={() => setPageName('list')}>
                        Listar los libros
                    </p></td>
                    <td><p onClick={() => setPageName('create')}>
                        Cadastrar nuevo libro
                    </p></td>
                    <td><p onClick={() => setPageName('update')}>
                        Atualizar el libro
                    </p></td>
                    <td><p onClick={() => setPageName('delete')}>
                        Deletar el libro
                    </p></td>
                </tr>
            </tbody>
        </table>
    </nav>