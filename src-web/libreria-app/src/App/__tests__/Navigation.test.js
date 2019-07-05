import React from 'react';
import renderer from 'react-test-renderer';
import { Navigation } from '../Navigation';

const setPageName = jest.fn();

test('Navigation has a valid snapshot', () => {
    const component = renderer.create(<Navigation setPageName={setPageName} />);
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});

test('quando clicar no menu "Listar livros", deve ser informada a página correspondente', () => {
    const component = renderer.create(
        <Navigation setPageName={setPageName} />
    );
    const listarLivro = component.root.findAllByType('td')[1];
    // Em teoria:
    // - pegar o elemento td
    // - simular o evento onClick
    // - expect(setPageName).toBeCalledWith('list');
})