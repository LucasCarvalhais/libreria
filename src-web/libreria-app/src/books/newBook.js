import React, { Component } from 'react';
import axios from 'axios';
import { PATH_BASE, PATH_BOOKS } from '../constants';
import './books.css';

class NewBook extends Component {
    constructor(props) {
        super(props);

        this.state = {
            book: {
                title: '',
                description: '',
                author: '',
                edition: 0,
            },
            success: false,
            error: null,
        }

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const name = event.target.name;
        const book = this.state.book;
        book[name] = event.target.value;
        this.setState({ book });
    }

    handleSubmit(event) {
        axios.post(`${PATH_BASE}${PATH_BOOKS}`, this.state.book)
            .then(response => this.setState({ success: true }))
            .catch(error => this.setState({ error }));
        event.preventDefault();
    }

    render() {
        const { book, success, error } = this.state;

        return(
            <div>
                <h1>Cadastrar el nuevo libro</h1>
                {error 
                ? <div>
                    <p>Uccurió un error :(</p>
                    <p>{error.toString()}</p>
                </div>
                : success 
                ? <p>Suceso!</p>
                : <form className="formulario" onSubmit={this.handleSubmit}>
                    <label>
                        <span className="legend">Título: </span>
                        <input
                            className="inputForm" 
                            type="text" 
                            name="title"
                            value={book.title}
                            onChange={this.handleChange} 
                        />
                    </label><br />
                    <label>
                        <span className="legend">Descripción: </span>
                        <input
                            className="inputForm" 
                            type="text" 
                            name="description" 
                            value={book.description}
                            onChange={this.handleChange}
                        />
                    </label><br />
                    <label>
                        <span className="legend">Autor: </span>
                        <input
                            className="inputForm" 
                            type="text" 
                            name="author" 
                            value={book.author}
                            onChange={this.handleChange}
                        />
                    </label><br />
                    <label>
                        <span className="legend">Edición: </span>
                        <input
                            className="inputForm" 
                            type="number" 
                            name="edition" 
                            value={book.edition}
                            onChange={this.handleChange}
                        />
                    </label><br />
                    <input id="submitBtn" type="submit" value="Cadastrar" />
                </form>
                }
            </div>
        );
    }
}

export default NewBook;