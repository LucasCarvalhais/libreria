import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Header, Footer } from './pageComponents';
import { Welcome } from './welcome';
import ListBooks from './listBooks';

class App extends Component {
    constructor(props) {
        super(props);

        this.state = {
            page: 'home',
        };

        this.setPageName = this.setPageName.bind(this);
    }

    setPageName(name) {
        this.setState({ page: name })
    }

    navigation() {
        return <nav>
            <table>
                <tr>
                    <td><a onClick={() => this.setPageName('home')}>
                        Pagina de inicio
                    </a></td>
                    <td><a onClick={() => this.setPageName('list')}>
                        Listar los libros
                    </a></td>
                    <td><a onClick={() => this.setPageName('create')}>
                        Cadastrar nuevo libro
                    </a></td>
                    <td><a onClick={() => this.setPageName('update')}>
                        Atualizar el libro
                    </a></td>
                    <td><a onClick={() => this.setPageName('delete')}>
                        Deletar el libro
                    </a></td>
                </tr>
            </table>
        </nav>;
    }

    printMainPage(page) {
        switch(page) {
            case 'home':
                return <Welcome />
            case 'list':
                return <ListBooks />
            default:
                return <p>En construcción...</p>
        }
    }

    render() {
        const { page } = this.state;

        return (
            <div>
                <Header />
                
                {this.navigation()}
                {this.printMainPage(page)}

                <Footer />
            </div>
        );
    }
}

ReactDOM.render(
    <App />,
    document.getElementById("root")
);