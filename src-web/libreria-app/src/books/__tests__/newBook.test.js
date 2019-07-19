import React from 'react';
import { shallow } from 'enzyme';
import NewBook from '../NewBook';

describe('NewBook', () => {
    test('should show error message', () => {
        const component = shallow(<NewBook />).setState({ error: 'No NetWork' });
        expect(component.find('ErrorMessage')).toHaveLength(1);
    });

    test('should show success message', () => {
        const component = shallow(<NewBook />).setState({ success: true });
        expect(component.find('.message')).toHaveLength(1);
    });

    test('should show the form', () => {
        const component = shallow(<NewBook />);
        expect(component.find('FormBook')).toHaveLength(1);
    });
});