import React, { Component } from 'react';
import axios from 'axios';
import { PATH_BASE, PATH_BOOKS } from '../constants';
import { ErrorMessage } from './listBooks';

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
            success: false,
            error: null,
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSearch = this.handleSearch.bind(this);
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
                success: true,
                error: null }))
            .catch(error => this.setState({ 
                book: null,
                success: false,
                error }));
        event.preventDefault();
    }

    render() {
        const { bookId, book, success, error } = this.state;

        return (
            <div>
                <form className="formulario">
                    <label>
                        <span className="legend">Pesquisar libro: </span>
                        <input 
                            className="inputForm"
                            type="number"
                            name="bookId"
                            value={bookId}
                            onChange={this.handleChange}
                        />
                    </label>
                    <input 
                        className="submitButton" 
                        type="submit" 
                        value="Pesquisar"
                        onClick={this.handleSearch} 
                    />
                </form>
                {error
                    ? <ErrorMessage error={error} />
                    : success
                        ? <div>EM CONSTRUÇÃO</div>
                        : <div>
                            <p>Por favor seleciona un código del libro y clica en "Pesquisar".</p>
                        </div>
                }
            </div>  
        );
    }
}

export default UpdateBook;