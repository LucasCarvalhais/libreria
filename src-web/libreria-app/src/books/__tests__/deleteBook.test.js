import React from 'react';
import renderer from 'react-test-renderer';
import DeleteBook from '../DeleteBook';

test('deleteBook has a valid snapshot', () => {
    const component = renderer.create(
        <DeleteBook />
    );
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});