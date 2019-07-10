import React, { Component } from 'react';
import axios from 'axios';
import './Books.css';
import { PATH_BASE, PATH_BOOKS } from '../Constants';
import { ErrorMessage } from './ErrorMessage';
import { BookTable } from './BookTable';

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
    
    async fetchBooks() {
        this.setState({ isLoading: true });

        await axios.get(`${PATH_BASE}${PATH_BOOKS}`)
            .then(result => this.setState({ 
                books: result.data, 
                isLoading: false 
            }))
            .catch(error => this.setState({ error }));
    }

    componentDidMount() {
        this.fetchBooks();
    }

    render() {
        const { books, error, isLoading } = this.state;

        return (
            <div>
                <h1>Lista de los libros</h1>
                { error
                    ? <ErrorMessage error={error} />
                    : <BookTable 
                        books={books}
                        isLoading={isLoading} 
                    />
                }
            </div>
        );
    }
}

export default ListBooks;
