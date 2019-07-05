import React from 'react';
import ReactDOM from 'react-dom';
import renderer from 'react-test-renderer';
import ListBooks from '../ListBooks';

test('listBooks renderers without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<ListBooks />, div);
});

test('listBooks has a valid snapshot', () => {
    const component = renderer.create(
        <ListBooks />
    );
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
})


