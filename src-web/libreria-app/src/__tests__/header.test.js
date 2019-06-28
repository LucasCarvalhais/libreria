import React from 'react';
import renderer from 'react-test-renderer';
import { Header } from '../pageComponents';

test('header has a valid snapshot', () => {
    const component = renderer.create(
        <Header />
    )
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});

