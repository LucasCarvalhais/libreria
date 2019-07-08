import React from 'react';
import ReactDom from 'react-dom';
import App, { MainPage } from '../App';
import { shallow } from 'enzyme';

test('App renders without crashing', () => {
    const div = document.createElement('div');
    ReactDom.render(<App />, div);
    ReactDom.unmountComponentAtNode(div);
});

describe('App', () => {
    test('should contain header', () => {
        const component = shallow(<App />);
        expect(component.find('header')).toHaveLength(1);
    });

    test('should contain navigation', () => {
        const component = shallow(<App />);
        expect(component.find('Navigation')).toHaveLength(1);
    });

    test('should contain main', () => {
        const component = shallow(<App />);
        expect(component.find('main')).toHaveLength(1);
    });

    test('should contain footer', () => {
        const component = shallow(<App />);
        expect(component.find('footer')).toHaveLength(1);
    });
});

describe('MainPage', () => {
    test('should render welcome page', () => {
        const component = shallow(<MainPage page='home'/>);
        expect(component.find('Welcome')).toHaveLength(1);
    });

    test('should render list books page', () => {
        const component = shallow(<MainPage page='list'/>);
        expect(component.find('ListBooks')).toHaveLength(1);
    });

    test('should render new book page', () => {
        const component = shallow(<MainPage page='create'/>);
        expect(component.find('NewBook')).toHaveLength(1);
    });

    test('should render update book page', () => {
        const component = shallow(<MainPage page='update'/>);
        expect(component.find('UpdateBook')).toHaveLength(1);
    });

    test('should render delete book page', () => {
        const component = shallow(<MainPage page='delete'/>);
        expect(component.find('DeleteBook')).toHaveLength(1);
    });

    test('should render in construcction page', () => {
        const component = shallow(<MainPage page='test'/>);
        expect(component.find('.inConstruction')).toHaveLength(1);
    });
});
