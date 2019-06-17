import React, { Component } from 'react';
import ReactDOM from 'react-dom';

const Header = () => 
    <header> 
        <h1>Librería de Madrid</h1>
    </header>

const Navigation = () =>
    <nav>
        <table>
            <tr>
                <td><a>Pesquisar el libro</a></td>
                <td><a>Cadastrar nuevo libro</a></td>
                <td><a>Atualizar el libro</a></td>
                <td>/<a>Deletar el libro</a></td>
            </tr>
        </table>
    </nav>

const Footer = () =>
    <footer>
        <p>
            En construcción... No es un sitio oficial, sólo un sitio para practicar el uso de Spring (back-end) y React (front-end). ¡Cualquier feedback or sugerencias son muy bienvenidas! :D
        </p>
    </footer>

class Welcome extends Component {
    render() {
        return (
            <div>
                <Header />
                <Navigation />
                <main>
                    <h1>¡Bienvenido a Librería de Madrid!</h1>
                    <p>
                        Elige las opciones arriba para navegar en nuestro sitio.
                    </p>
                </main>
                <Footer />
            </div>
        );
    }
}

ReactDOM.render(
    <Welcome />,
    document.getElementById("root")
);