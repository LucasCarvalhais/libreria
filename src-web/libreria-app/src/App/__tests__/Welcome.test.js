import React from 'react';
import renderer from 'react-test-renderer';
import { Welcome } from '../Welcome';

test('Welcome has a valid snapshot', () => {
    const component = renderer.create(<Welcome />);
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});