import React from 'react';
import { render } from 'enzyme';
import { ErrorMessage } from '../ErrorMessage';

const error = 'No Network Available';

test('ErrorMessage renders properly the message', () => {
    const component = render(<ErrorMessage error={error} />);
    expect(component.text()).toContain('No Network Available');
});