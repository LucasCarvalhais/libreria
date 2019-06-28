import React from 'react';
import renderer from 'react-test-renderer';
import { Navigation } from '../pageComponents';

const setPageName = () => {};

test('Navigation has a valid snapshot', () => {
    const component = renderer.create(<Navigation setPageName={setPageName} />);
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});
