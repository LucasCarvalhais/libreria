import React, { Component } from 'react';
import ErrorMessage from './ErrorMessage';
import FormBook from "./FormBook";
import FormSearch from './FormSearch';
import { getBookById, updateBook } from './bookService';

class UpdateBook extends Component {
    constructor(props) {
        super(props);

        this.state = {
            bookId: undefined,
            book: {
                title: '',
                descriptipn: '',
                author: '',
                edition: undefined,
            },
            successSearch: false,
            successUpdate: false,
            errorSearch: null,
            errorUpdate: null,
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSearch = this.handleSearch.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const name = event.target.name;
        if (name === 'bookId') {
            this.setState({ bookId: event.target.value });
        } else {
            const book = this.state.book;
            book[name] = event.target.value;
            this.setState({ book });
        }
    }

    async handleSearch(event) {
        event.preventDefault();
        const bookId = this.state.bookId;
        const { response, err, success } = await getBookById(bookId);
        if (success) {
            this.setState({
                book: response.data, 
                successSearch: true,
                successUpdate: false,
                errorSearch: null,
                errorUpdate: null 
            });
        } else {
            this.setState({ 
                book: null,
                successSearch: false,
                successSearch: false,
                errorSearch: err,
                errorUpdate: null, 
            });
        }
    }

    async handleSubmit(event) {
        event.preventDefault();
        const bookId = this.state.bookId;
        const book = this.state.book;
        const { success, err } = await updateBook(bookId, book);
        if (success) {
            this.setState({
                successSearch: false, 
                successUpdate: true,
                errorSearch: null,
                errorUpdate: null 
            });
        } else {
            this.setState({ 
                successSearch: false,
                successUpdate: false,
                errorSearch: null,
                errorUpdate: err, 
            });
        }
    }

    render() {
        const { 
            bookId, 
            book, 
            successSearch, 
            successUpdate, 
            errorSearch, 
            errorUpdate 
        } = this.state;

        return (
            <div>
                <h1>Atualizar el libro</h1>
                {!successSearch && 
                    <FormSearch
                        bookId={bookId}
                        handleChange={this.handleChange}
                        handleSubmit={this.handleSearch}
                    />
                }
                {errorUpdate
                    ? <ErrorMessage error={errorUpdate} />
                    : successUpdate && <div className="message">SUceso!</div>
                }
                {errorSearch
                    ? <ErrorMessage error={errorSearch} />
                    : successSearch
                        ? <FormBook
                            book={book}
                            handleChange={this.handleChange}
                            handleSubmit={this.handleSubmit}
                        />
                        : <div className="message">
                            <p>Por favor seleciona un código del libro y clica en "Pesquisar".</p>
                        </div>
                }
            </div>  
        );
    }
}

export default UpdateBook;  