import React from 'react';
import ReactDom from 'react-dom';
import renderer from 'react-test-renderer';
import NewBook from '../NewBook';

test('newBook renders without crashing', () => {
    const div = document.createElement('div');
    ReactDom.render(<NewBook />, div);
});

test('newBook has a valid snapshot', () => {
    const component = renderer.create(
        <NewBook />
    );
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});