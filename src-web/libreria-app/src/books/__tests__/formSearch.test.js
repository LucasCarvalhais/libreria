import React from 'react';
import { mount } from 'enzyme';
import { FormSearch } from '../FormSearch';

const handleChange = jest.fn();
const handleSubmit = jest.fn();

const bookId = 1;

describe('FormSearch', () => {
    const form = mount(
        <FormSearch 
            bookId={bookId} 
            handleChange={handleChange} 
            handleSubmit={handleSubmit} 
        />
    );
    
    test('should call the function when input changes', () => {
        const input = form.find('input').at(0);
        input.simulate('change');
        expect(handleChange).toHaveBeenCalled();
    });

    test('should have values according of the book', () => {
        expect(form.find('input').at(0).props().value).toBe(1);
    });

    test('should call the function of submit when form submits', () => {
        form.simulate('submit');
        expect(handleSubmit).toHaveBeenCalled();
    });
})