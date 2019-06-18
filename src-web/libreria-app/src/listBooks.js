import React, { Component } from 'react';
import axios from 'axios';
import './listBooks.css';

const PATH_BASE = 'http://localhost:8080';
const PATH_BOOKS = '/books';

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

        axios.get('http://localhost:8080/books/')
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
                ? <div>
                    <p>Ocurrió un problema :(</p>
                    {console.log('ERRO ' + error)}
                </div>
                : <div>
                    {isLoading
                     ? <div><p className="loading">Cargando...</p></div> 
                     : <table className="bookTable">
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
                    }
                </div>
            }
            </div>
        );
    }
}

export default ListBooks;