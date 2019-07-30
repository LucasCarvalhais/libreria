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
            successDelete: false,
            errorSearch: null,
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
        const { success, error, result } = await getBookById(bookId);
        
        this.setState({
            book: result.data,
            successSearch: success,
            errorSearch: error
        });
    }

    async handleDelete() {
        const bookId = this.state.bookId;
        const { success, error } = await deleteBook(bookId);
        
        this.setState({
            successDelete: success,
            errorDelete: error
        });
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
                    ? <ErrorMessage 
                        className="error-delete" 
                        error={errorDelete} 
                    />
                    : successDelete
                        ? <div className="success-delete-message">
                            <p className="message">SUceso!</p>
                        </div>
                        : errorSearch
                            ? <ErrorMessage 
                                className="error-search" 
                                error={errorSearch} 
                            />
                            : successSearch
                                ? <div className="delete-message">
                                    <p className="message">
                                        Desea remover el libro <em>{book.title}</em>, de <em>{book.author}</em>, cuya descripción es <em>{book.description}</em> y de <em>{book.edition}</em> edición? 
                                    </p>
                                    <button 
                                        className="submit-button" 
                                        onClick={() => this.handleDelete()}
                                    >
                                        Sí, quiero remover este libro.
                                    </button>
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