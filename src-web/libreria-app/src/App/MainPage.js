import React from 'react';
import Welcome from './Welcome';
import { NewBook, ListBooks, UpdateBook, DeleteBook } from '../Books';

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

export default MainPage;