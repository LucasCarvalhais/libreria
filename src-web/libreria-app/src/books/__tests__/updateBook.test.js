import React from 'react';
import { shallow } from 'enzyme';
import UpdateBook from '../UpdateBook';

describe('UpdateBook', () => {
    test('should show the form to search book', () => {
        const component = shallow(<UpdateBook />);
        
        expect(component.find('FormSearch')).toHaveLength(1);
        expect(component.find('FormBook')).toHaveLength(0);
        expect(component.find('.message')).toHaveLength(0);
        expect(component.find('ErrorMessage')).toHaveLength(0);
    });

    test('should show the form to update book', () => {
        const component = shallow(<UpdateBook />).setState({
            successSearch: true,
            successUpdate: false,
            errorSearch: null,
            errorUpdate: null,
        });
        
        expect(component.find('FormSearch')).toHaveLength(0);
        expect(component.find('FormBook')).toHaveLength(1);
        expect(component.find('.message')).toHaveLength(0);
        expect(component.find('ErrorMessage')).toHaveLength(0);
    });

    test('should show the success message', () => {
        const component = shallow(<UpdateBook />).setState({
            successSearch: true,
            successUpdate: true,
            errorSearch: null,
            errorUpdate: null,
        });

        expect(component.find('FormSearch')).toHaveLength(0);
        expect(component.find('FormBook')).toHaveLength(0);
        expect(component.find('.message')).toHaveLength(1);
        expect(component.find('ErrorMessage')).toHaveLength(0);
    });

    test('should show the error message', () => {
        const component = shallow(<UpdateBook />).setState({
            successSearch: false,
            successUpdate: false,
            errorSearch: 'Error',
            errorUpdate: null,
        });

        expect(component.find('FormSearch')).toHaveLength(0);
        expect(component.find('FormBook')).toHaveLength(0);
        expect(component.find('.message')).toHaveLength(0);
        expect(component.find('ErrorMessage')).toHaveLength(1);
    });
});
