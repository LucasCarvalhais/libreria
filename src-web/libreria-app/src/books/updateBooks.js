import React, { Component } from 'react';
import axios from 'axios';
import { PATH_BASE, PATH_BOOKS } from '../constants';
import { ErrorMessage } from './listBooks';
import { Form } from './newBook';

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

    handleSearch(event) {
        axios.get(`${PATH_BASE}${PATH_BOOKS}${this.state.bookId}`)
            .then(response => this.setState({
                book: response.data, 
                successSearch: true,
                successUpdate: false,
                errorSearch: null,
                errorUpdate: null }))
            .catch(error => this.setState({ 
                book: null,
                successSearch: false,
                successSearch: false,
                errorSearch: error,
                errorUpdate: null, }));
        event.preventDefault();
    }

    handleSubmit(event) {
        axios.put(`${PATH_BASE}${PATH_BOOKS}${this.state.bookId}`, this.state.book)
            .then(() => this.setState({
                successSearch: false, 
                successUpdate: true,
                errorSearch: null,
                errorUpdate: null }))
            .catch(error => this.setState({ 
                successSearch: false,
                successUpdate: false,
                errorSearch: null,
                errorUpdate: error }));
        event.preventDefault();
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
                        handleSearch={this.handleSearch}
                    />
                }
                {errorUpdate
                    ? <ErrorMessage error={errorUpdate} />
                    : successUpdate && <div className="message">SUceso!</div>
                }
                {errorSearch
                    ? <ErrorMessage error={errorSearch} />
                    : successSearch
                        ? <Form
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

const FormSearch = ({ bookId, handleChange, handleSearch }) => 
    <form className="formulario">
        <label>
            <span className="legend">Pesquisar libro: </span>
            <input 
                className="inputForm"
                type="number"
                name="bookId"
                value={bookId}
                onChange={handleChange}
            />
        </label>
        <input 
            className="submitButton" 
            type="submit" 
            value="Pesquisar"
            onClick={handleSearch} 
        />
    </form>

export default UpdateBook;