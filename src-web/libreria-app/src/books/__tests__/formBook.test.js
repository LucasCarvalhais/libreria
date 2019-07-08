import React from 'react';
import { mount } from 'enzyme';
import { FormBook } from '../FormBook';

const handleChange = jest.fn();
const handleSubmit = jest.fn();

const book = { 
    title: 'Teste1', 
    description: 'Teste2', 
    author: 'Teste3', 
    edition: 2, 
};

describe('FormBook', () => {
    const form = mount(
        <FormBook 
            book={book} 
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
        expect(form.find('input').at(0).props().value).toBe('Teste1');
        expect(form.find('input').at(1).props().value).toBe('Teste2');
        expect(form.find('input').at(2).props().value).toBe('Teste3');
        expect(form.find('input').at(3).props().value).toBe(2);
    });

    test('should call the function of submit when form submits', () => {
        form.simulate('submit');
        expect(handleSubmit).toHaveBeenCalled();
    });
})