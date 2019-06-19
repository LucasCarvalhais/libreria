import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Header, Navigation, Footer } from './pageComponents';
import { Welcome } from './welcome';
import ListBooks from './listBooks';

class App extends Component {
    constructor(props) {
        super(props);

        this.state = {
            page: 'home',
        };

        this.setPageName = this.setPageName.bind(this);
    }

    setPageName(name) {
        this.setState({ page: name })
    }

    render() {
        const { page } = this.state;

        return (
            <div>
                <Header />
                <Navigation setPageName={this.setPageName} />
                <MainPage page={page} />
                <Footer />
            </div>
        );
    }
}

const MainPage = ({ page }) => {
    switch(page) {
        case 'home':
            return <Welcome />
        case 'list':
            return <ListBooks />
        default:
            return <p className="inConstruction">En construcción...</p>
    }
}

ReactDOM.render(
    <App />,
    document.getElementById("root")
);