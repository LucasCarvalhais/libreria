import React from 'react';
import renderer from 'react-test-renderer';
import { BookTable } from '../BookTable';

const props = {
    books: [
        { bookId: 1, title: 'teste1', description: 'teste1', author: 'Lucas', edition: 1 },
        { bookId: 2, title: 'teste2', description: 'teste2', author: 'Lucas', edition: 1 },
        { bookId: 3, title: 'teste3', description: 'teste3', author: 'Lucas', edition: 1 }, 
    ],
    isLoading: false,
};

test('bookTable has a valid snapshot', () => {
    const component = renderer.create(<BookTable {...props} />);
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});
