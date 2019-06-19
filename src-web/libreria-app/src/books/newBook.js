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
                ? <p>Uccurió un error :(</p>
                : success 
                ? <p>Suceso!</p>
                : <form className="formulario" onSubmit={this.handleSubmit}>
                    <label>
                        Título: <input 
                            type="text" 
                            name="title"
                            value={this.state.book.title}
                            onChange={this.handleChange} 
                        />
                    </label><br />
                    <label>
                        Descripción: <input 
                            type="text" 
                            name="description" 
                            value={this.state.book.description}
                            onChange={this.handleChange}
                        />
                    </label><br />
                    <label>
                        Autor: <input 
                            type="text" 
                            name="author" 
                            value={this.state.book.author}
                            onChange={this.handleChange}
                        />
                    </label><br />
                    <label>
                        Edición: <input 
                            type="number" 
                            name="edition" 
                            value={this.state.book.edition}
                            onChange={this.handleChange}
                        />
                    </label><br />
                    <input type="submit" value="Cadastrar" />
                </form>
                }
            </div>
        );
    }
}

export default NewBook;