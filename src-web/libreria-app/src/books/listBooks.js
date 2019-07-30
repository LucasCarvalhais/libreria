import React, { Component } from 'react';
import './Books.css';
import ErrorMessage from './ErrorMessage';
import BookTable from './BookTable';
import { getBook } from './bookService';

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

        const { result, error } = await getBook();
        
        this.setState({
            books: result.data,
            isLoading: false,
            error
        });
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