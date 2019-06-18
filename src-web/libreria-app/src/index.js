import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Header, Navigation, Footer } from './headerNavFooter';
import { Welcome } from './welcome';
import ListBooks from './listBooks';

class App extends Component {
    render() {
        return (
            <div>
                <Header />
                <Navigation />
                <ListBooks />
                <Footer />
            </div>
        );
    }
}

ReactDOM.render(
    <App />,
    document.getElementById("root")
);