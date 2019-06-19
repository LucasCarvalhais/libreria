import React from 'react';

const Header = () => 
    <header>
        <h1>Librería de Madrid</h1>
    </header>;

const Navigation = ({ setPageName }) => 
    <nav>
        <table>
            <tr>
                <td><a onClick={() => setPageName('home')}>
                    Pagina de inicio
                </a></td>
                <td><a onClick={() => setPageName('list')}>
                    Listar los libros
                </a></td>
                <td><a onClick={() => setPageName('create')}>
                    Cadastrar nuevo libro
                </a></td>
                <td><a onClick={() => setPageName('update')}>
                    Atualizar el libro
                </a></td>
                <td><a onClick={() => setPageName('delete')}>
                    Deletar el libro
                </a></td>
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