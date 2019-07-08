import React from 'react';
import { shallow } from 'enzyme';
import { BookTable } from '../BookTable';

const props = {
    books: [
        { 
            bookId: 1, 
            title: 'teste1', 
            description: 'teste1', 
            author: 'Lucas', 
            edition: 1 
        },
        { 
            bookId: 2, 
            title: 'teste2', 
            description: 'teste2', 
            author: 'Lucas', 
            edition: 1 
        },
        { 
            bookId: 3, 
            title: 'teste3', 
            description: 'teste3', 
            author: 'Lucas', 
            edition: 1 
        }, 
    ],
    isLoading: false,
};

describe('BookTable', () => {
    test('should render 4 lines, including the header of table', () => {
        const component = shallow(<BookTable { ...props } />);
        expect(component.find('tr')).toHaveLength(4);
    });
});
