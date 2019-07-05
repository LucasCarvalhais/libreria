import React from 'react';
import renderer from 'react-test-renderer';
import { FormBook } from '../FormBook';

const book = { title: 'Teste', description: 'Teste', author: 'teste', edition: 1, };

test('FormBook has a valid snapshot', () => {
    const component = renderer.create(
        <FormBook book={book} handleChange={()=>{}} handleSubmit={()=>{}} />
    );
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});