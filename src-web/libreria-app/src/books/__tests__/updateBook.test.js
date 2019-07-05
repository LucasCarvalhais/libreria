import React from 'react';
import ReactDom from 'react-dom';
import renderer from 'react-test-renderer';
import UpdateBook from '../UpdateBook';

test('newBook renders without crashing', () => {
    const div = document.createElement('div');
    ReactDom.render(<UpdateBook />, div);
});

test('updateBook has a valid snapshot', () => {
    const component = renderer.create(
        <UpdateBook />
    );
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});