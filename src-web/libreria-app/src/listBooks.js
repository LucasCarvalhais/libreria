import React, { Component } from 'react';
import axios from 'axios';
import './listBooks.css';
import { PATH_BASE, PATH_BOOKS } from './constants';

class ListBooks extends Component {
    constructor(props) {
        super(props);
        
        this.state = {
            books: [],
            error: null,
            isLoading: false,
        };

        this.fetchBooks = this.fetchBooks.bind(this);
    }
    
    fetchBooks() {
        this.setState({ isLoading: true });

        axios.get(`${PATH_BASE}${PATH_BOOKS}`)
            .then(result => this.setState({ books: result.data, isLoading: false }))
            .catch(error => this.setState({ error }));
    }

    componentDidMount() {
        this.fetchBooks();
    }

    render() {
        const { books, error, isLoading } = this.state;

        return (
            <div>
            { error
                ? <ErrorMessage error={error} />
                : <Table 
                    books={books}
                    isLoading={isLoading} 
                />
            }
            </div>
        );
    }
}

const ErrorMessage = ({ error }) =>
    <div className="errorMessage">
        <p>Ocurrió un problema :(</p>
        <p>{error.toString()}</p>
        {console.log('ERRO: ' + error)}
    </div>

const Table = ({ books, isLoading }) =>
    <div>
        {isLoading
            ? <div><p className="loading">Cargando...</p></div> 
            : <div>
                <h1>Lista de los libros</h1>
                <table className="bookTable">
                    <tr>
                        <th>Código</th>
                        <th>Título</th>
                        <th>Descripción</th>
                        <th>Persona autora</th>
                        <th>Edición</th>
                    </tr>
                    {books.map(book => 
                        <tr>
                            <td>{book.bookId}</td>
                            <td>{book.title}</td>
                            <td>{book.description}</td>
                            <td>{book.author}</td>
                            <td>{book.edition}</td>
                        </tr>
                    )}
                </table>
            </div>
        }
    </div>

export default ListBooks;