import React, { Component } from 'react';
import axios from 'axios';
import { FormSearch } from './FormSearch';
import { PATH_BASE, PATH_BOOKS } from '../Constants';
import { ErrorMessage } from './ErrorMessage';

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

    handleSearch(event) {
        axios.get(`${PATH_BASE}${PATH_BOOKS}/${this.state.bookId}`)
            .then(response => this.setState({
                book: response.data, 
                successSearch: true,
                successDelete: false,
                errorSearch: null,
                errorDelete: null }))
            .catch(error => this.setState({ 
                book: null,
                successSearch: false,
                successDelete: false,
                errorSearch: error,
                errorDelete: null, }));
        event.preventDefault();
    }

    handleDelete() {
        axios.delete(`${PATH_BASE}${PATH_BOOKS}${this.state.bookId}`)
            .then(() => this.setState({
                successSearch: false, 
                successDelete: true,
                errorSearch: null,
                errorDelete: null }))
            .catch(error => this.setState({ 
                successSearch: false,
                successDelete: false,
                errorSearch: null,
                errorDelete: error }));
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
                {!successSearch && 
                    <FormSearch
                        bookId={bookId}
                        handleChange={this.handleChange}
                        handleSubmit={this.handleSearch}
                    />
                }
                {errorDelete
                    ? <ErrorMessage error={errorDelete} />
                    : successDelete && <div className="message">SUceso!</div>
                }
                {errorSearch
                    ? <ErrorMessage error={errorSearch} />
                    : successSearch
                        ? <div>
                            <p className="message">
                                Desea remover el libro <em>{book.title}</em>, de <em>{book.author}</em>, cuya descripción es <em>{book.description}</em> y de <em>{book.edition}</em> edición? 
                            </p>
                            <button className="submitButton" onClick={() => this.handleDelete()}>Sí, quiero remover este libro.</button>
                        </div>
                        : <div className="message">
                            <p>Por favor seleciona un código del libro y clica en "Pesquisar".</p>
                        </div>
                }
            </div>  
        );
    }
}

export default DeleteBook;