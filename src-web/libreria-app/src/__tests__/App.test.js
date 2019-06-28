import React from 'react';
import ReactDom from 'react-dom';
import renderer from 'react-test-renderer';
import App, { MainPage } from '../App';

test('App renders without crashing', () => {
    const div = document.createElement('div');
    ReactDom.render(<App />, div);
    ReactDom.unmountComponentAtNode(div);
});

test('App has a valid snapshot', () => {
    const component = renderer.create(
        <App />
    );
    const tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});

describe('Renders specific components', () => {
    test('should render welcome page', () => {
        const component = renderer.create(
            <MainPage page='home' />
        );
        const tree = component.toJSON();
        expect(tree).toMatchSnapshot();
        expect(JSON.stringify(tree)).toContain('¡Bienvenido a Librería de Madrid!');
    });

    test('should render list books page', () => {
        const component = renderer.create(
            <MainPage page='list' />
        );
        const tree = component.toJSON();
        expect(tree).toMatchSnapshot();
        expect(JSON.stringify(tree)).toContain('Lista de los libros');
    });

    test('should render new book page', () => {
        const component = renderer.create(
            <MainPage page='create' />
        );
        const tree = component.toJSON();
        expect(tree).toMatchSnapshot();
        expect(JSON.stringify(tree)).toContain('Cadastrar el nuevo libro');
    });

    test('should render update book page', () => {
        const component = renderer.create(
            <MainPage page='update' />
        );
        const tree = component.toJSON();
        expect(tree).toMatchSnapshot();
        expect(JSON.stringify(tree)).toContain('Atualizar el libro');
    });

    test('should render delete book page', () => {
        const component = renderer.create(
            <MainPage page='delete' />
        );
        const tree = component.toJSON();
        expect(tree).toMatchSnapshot();
        expect(JSON.stringify(tree)).toContain('Deletar el libro');
    });

    test('should render in construcction page', () => {
        const component = renderer.create(
            <MainPage page='none' />
        );
        const tree = component.toJSON();
        expect(tree).toMatchSnapshot();
    });
});