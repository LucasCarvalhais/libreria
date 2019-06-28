import React from 'react';
import renderer from 'react-test-renderer';
import { Footer } from '../pageComponents';

test('footer has a valid snapshot', () => {
    const component = renderer.create(<Footer />);
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});
