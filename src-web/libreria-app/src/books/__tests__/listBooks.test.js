import React from 'react';
import { shallow } from 'enzyme';
import axios from 'axios';
import ListBooks from '../ListBooks';

const books = [{
    bookId: 1,
    title: "Un",
    description: "Uno",
    author: "One",
    edition: 1,
},
{
    bookId: 2,
    title: "Deux",
    description: "Dos",
    author: "Two",
    edition: 1,
}];

describe('ListBooks', () => {
    test('should show book table', () => {
        const component = shallow(<ListBooks />).setState({
            books: books,
            error: null,
        });
        expect(component.find('BookTable')).toHaveLength(1);
    });

    test('should show error message', () => {
        const component = shallow(<ListBooks />).setState({
            books: [],
            error: 'Error',
        });
        expect(component.find('ErrorMessage')).toHaveLength(1);
    });
});