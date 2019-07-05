import React from 'react';
import ReactDom from 'react-dom';
import renderer from 'react-test-renderer';
import DeleteBook from '../DeleteBook';

test('newBook renders without crashing', () => {
    const div = document.createElement('div');
    ReactDom.render(<DeleteBook />, div);
});

test('deleteBook has a valid snapshot', () => {
    const component = renderer.create(
        <DeleteBook />
    );
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});