import React from 'react';
import { shallow } from 'enzyme';
import DeleteBook from '../DeleteBook';

describe('DeleteBook', () => {
    test('should show form to search book', () => {
        const component = shallow(<DeleteBook />);

        expect(component.find('FormSearch')).toHaveLength(1);
        expect(component.find('.delete-message')).toHaveLength(0);
        expect(component.find('.success-delete-message')).toHaveLength(0);
        expect(component.find('.error-search')).toHaveLength(0);
        expect(component.find('.error-delete')).toHaveLength(0);
    });

    test('should show message to delete after searching', () => {
        const component = shallow(<DeleteBook />).setState({
            successSearch: true,
            successDelete: false,
            errorSearch: null,
            errorDelete: null,
        });
        
        expect(component.find('FormSearch')).toHaveLength(0);
        expect(component.find('.delete-message')).toHaveLength(1);
        expect(component.find('.success-delete-message')).toHaveLength(0);
        expect(component.find('.error-search')).toHaveLength(0);
        expect(component.find('.error-delete')).toHaveLength(0);
    });

    test('should show success message after deleting', () => {
        const component = shallow(<DeleteBook />).setState({
            successSearch: true,
            successDelete: true,
            errorSearch: null,
            errorDelete: null,
        });

        expect(component.find('FormSearch')).toHaveLength(0);
        expect(component.find('.delete-message')).toHaveLength(0);
        expect(component.find('.success-delete-message')).toHaveLength(1);
        expect(component.find('.error-search')).toHaveLength(0);
        expect(component.find('.error-delete')).toHaveLength(0);
    });

    test('should show error message after searching', () => {
        const component = shallow(<DeleteBook />).setState({
            successSearch: false,
            successDelete: false,
            errorSearch: 'Error',
            errorDelete: null,
        });
        expect(component.find('FormSearch')).toHaveLength(0);
        expect(component.find('.delete-message')).toHaveLength(0);
        expect(component.find('.success-delete-message')).toHaveLength(0);
        expect(component.find('.error-search')).toHaveLength(1);
        expect(component.find('.error-delete')).toHaveLength(0);
    });

    test('should show error message after deleting', () => {
        const component = shallow(<DeleteBook />).setState({
            successSearch: true,
            successDelete: false,
            errorSearch: null,
            errorDelete: 'Error',
        });

        expect(component.find('FormSearch')).toHaveLength(0);
        expect(component.find('.delete-message')).toHaveLength(0);
        expect(component.find('.success-delete-message')).toHaveLength(0);
        expect(component.find('.error-search')).toHaveLength(0);
        expect(component.find('.error-delete')).toHaveLength(1);
    });

    test('should call handleDelete after clicking on delete button', () => {
        const component = shallow(<DeleteBook />).setState({
            successSearch: true,
            successDelete: false,
            errorSearch: null,
            errorDelete: null,
        });
        
        component.find('.delete-message').find('button').simulate('click');
        
        expect(component.prop('handleDelete')).toHaveBeenCalled;
    });
});

