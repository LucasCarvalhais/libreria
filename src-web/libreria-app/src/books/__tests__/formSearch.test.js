import React from 'react';
import renderer from 'react-test-renderer';
import { FormSearch } from '../formSearch';

test('FormSearch has a valid snapshot', () => {
    const component = renderer.create(
        <FormSearch />
    );
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});