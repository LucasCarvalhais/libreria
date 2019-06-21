import React from 'react';
import './pageComponents.css';

const Header = () => 
    <header>
        <h1>Librería de Madrid</h1>
    </header>;

const Navigation = ({ setPageName }) => 
    <nav>
        <table>
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
        </table>
    </nav>

const Footer = () => 
    <footer>
        <p>
            En construcción... No es un sitio oficial, sólo un sitio para practicar el uso de Spring (back-end) y React (front-end). ¡Cualquier feedback or sugerencias son muy bienvenidas! :D
            </p>
    </footer>;

export {
    Header,
    Navigation,
    Footer
}