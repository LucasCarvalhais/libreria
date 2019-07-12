import React from 'react';
import renderer from 'react-test-renderer';
import DeleteBook from '../DeleteBook';
import { shallow } from 'enzyme';
import { FormSearch } from '../FormSearch';

const handleDelete = jest.fn();

describe('DeleteBook', () => {
    test('should show form to search book', () => {
        const component = shallow(<DeleteBook />);
        expect(component.find(FormSearch)).toHaveLength(1);
    });

    test('should show message to delete after searching', () => {
        const component = shallow(<DeleteBook />).setState({
            successSearch: true,
            successDelete: false,
            errorSearch: null,
            errorDelete: null,
        });
        expect(component.find('.messageDelete')).toHaveLength(1);
    });

    test('should show success message after deleting', () => {
        const component = shallow(<DeleteBook />).setState({
            successSearch: true,
            successDelete: true,
            errorSearch: null,
            errorDelete: null,
        });
        expect(component.find('.messageSuccess')).toHaveLength(1);
    });

    test('should show error message after searching', () => {
        const component = shallow(<DeleteBook />).setState({
            successSearch: false,
            successDelete: false,
            errorSearch: 'Error',
            errorDelete: null,
        });
        expect(component.find('.errorSearch')).toHaveLength(1);
    });

    test('should show error message after deleting', () => {
        const component = shallow(<DeleteBook />).setState({
            successSearch: true,
            successDelete: false,
            errorSearch: null,
            errorDelete: 'Error',
        });
        expect(component.find('.errorDelete')).toHaveLength(1);
    });
});

