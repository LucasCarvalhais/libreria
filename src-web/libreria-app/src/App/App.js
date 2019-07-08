import React, { Component } from 'react';
import { Navigation } from './Navigation';
import { Welcome } from './Welcome';
import { NewBook, ListBooks, UpdateBook, DeleteBook } from '../Books';
import './App.css';

class App extends Component {
    constructor(props) {
        super(props);

        this.state = {
            page: 'home',
        };
        
        this.setPageName = this.setPageName.bind(this);
    }

    setPageName(name) {
        this.setState({ page: name });
    }
    
    render() {
        const { page } = this.state;

        return (
            <div>
                <header>
                    <h1>Librería de Madrid</h1>
                </header>
                <Navigation setPageName={this.setPageName} />
                <main>
                    <MainPage page={page} />
                </main>
                <footer>
                    <p>
                        En construcción... No es un sitio oficial, sólo un sitio para practicar el uso de Spring (back-end) y React (front-end). ¡Cualquier feedback or sugerencias son muy bienvenidas! :D
                    </p>
                </footer>
            </div>
        );
    }
};

const MainPage = ({ page }) => {
    switch (page) {
        case 'home':
            return <Welcome />;
        case 'list':
            return <ListBooks />;
        case 'create':
            return <NewBook />;
        case 'update':
            return <UpdateBook />;
        case 'delete':
            return <DeleteBook />;
        default:
            return <p className="inConstruction">En construcción...</p>;
    }
};

export default App;

export { MainPage };