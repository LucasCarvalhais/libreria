import React from 'react';
import { render } from 'enzyme';
import { ErrorMessage } from '../ErrorMessage';
import Enzyme from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

Enzyme.configure({ adapter: new Adapter() });

const error = 'No Network Available';

test('ErrorMessage renders properly the message', () => {
    const component = render(<ErrorMessage error={error} />);
    expect(component.text()).toContain('No Network Available');
})