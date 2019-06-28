import React from 'react';
import renderer from 'react-test-renderer';
import { ErrorMessage } from '../errorMessage';

const error = {
    error: 'No Network Available',
};

test('errorMessage has a valid snapshot', () => {
    const component = renderer.create(<ErrorMessage {...error} />);
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});
