import React, { Component } from 'react';
import ErrorMessage from './ErrorMessage';
import FormBook from './FormBook';
import { saveBook } from './bookService';
import './Books.css';

class NewBook extends Component {
    constructor(props) {
        super(props);

        this.state = {
            book: {
                title: '',
                description: '',
                author: '',
                edition: undefined,
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

    async handleSubmit(event) {
        event.preventDefault();
        const book = this.state.book;
        const response = await saveBook(book);
        if (response === 'success') {
            this.setState({ success: true });
        } else {
            this.setState({ error: response });
        } 
    }

    render() {
        const { book, success, error } = this.state;

        return(
            <div>
                <h1>Cadastrar el nuevo libro</h1>
                {error 
                    ? <ErrorMessage error={error} />
                    : success 
                        ? <p className="message">Suceso!</p>
                        : <FormBook 
                            book={book}
                            handleChange={this.handleChange}
                            handleSubmit={this.handleSubmit} 
                        />
                }
            </div>
        );
    }
}

export default NewBook;