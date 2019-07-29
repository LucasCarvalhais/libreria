import React, { Component } from 'react';
import FormSearch from './FormSearch';
import ErrorMessage from './ErrorMessage';
import { getBookById, deleteBook } from './bookService';

class DeleteBook extends Component {
    constructor(props) {
        super(props);

        this.state = {
            bookId: undefined,
            book: {
                title: '',
                description: '',
                author: '',
                edition: undefined,
            },
            successSearch: false,
            errorSearch: null,
            successDelete: false,
            errorDelete: null,
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSearch = this.handleSearch.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
    }

    handleChange(event) {
        this.setState({ bookId: event.target.value });
    }

    async handleSearch(event) {
        event.preventDefault();
        const bookId = this.state.bookId;
        const { response, err, success } = await getBookById(bookId);
        if (success) {
            this.setState({
                book: response.data, 
                successSearch: true,
                successDelete: false,
                errorSearch: null,
                errorDelete: null 
            });
        } else {
            this.setState({ 
                book: null,
                successSearch: false,
                successDelete: false,
                errorSearch: err,
                errorDelete: null, 
            });
        }
    }

    async handleDelete() {
        const bookId = this.state.bookId;
        const { success, err } = await deleteBook(bookId);
        if (success) {
            this.setState({
                successSearch: false, 
                successDelete: true,
                errorSearch: null,
                errorDelete: null 
            });
        } else {
            this.setState({ 
                successSearch: false,
                successDelete: false,
                errorSearch: null,
                errorDelete: err 
            });
        }
    }

    render() {
        const { 
            bookId, 
            book, 
            successSearch, 
            successDelete, 
            errorSearch, 
            errorDelete 
        } = this.state;

        return (
            <div>
                <h1>Deletar el libro</h1>
                { errorDelete
                    ? <ErrorMessage className="errorDelete" error={errorDelete} />
                    : successDelete
                        ? <div className="messageSuccess"><p className="message">SUceso!</p></div>
                        : errorSearch
                            ? <ErrorMessage className="errorSearch" error={errorSearch} />
                            : successSearch
                                ? <div className="messageDelete">
                                    <p className="message">
                                        Desea remover el libro <em>{book.title}</em>, de <em>{book.author}</em>, cuya descripción es <em>{book.description}</em> y de <em>{book.edition}</em> edición? 
                                    </p>
                                    <button className="submitButton" onClick={() => this.handleDelete()}>Sí, quiero remover este libro.</button>
                                </div>
                                : <FormSearch
                                    bookId={bookId}
                                    handleChange={this.handleChange}
                                    handleSubmit={this.handleSearch}
                                />
                }
            </div>  
        );
    }
}

export default DeleteBook;