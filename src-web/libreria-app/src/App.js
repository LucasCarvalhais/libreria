import React, { Component } from 'react';
import { Header, Navigation, Footer } from './pageComponents';
import { Welcome } from './welcome';
import NewBook from './books/newBook';
import ListBooks from './books/listBooks';
import UpdateBook from './books/updateBooks';

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
                <Header />
                <Navigation setPageName={this.setPageName} />
                <main>
                    <MainPage page={page} />
                </main>
                <Footer />
            </div>
        );
    }
}

// Verificar se switch vale a pena e se tem alternativas melhores
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
        default:
            return <p className="inConstruction">En construcción...</p>;
    }
};

export default App;