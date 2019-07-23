import React from 'react';
import { shallow } from 'enzyme';
import MainPage from '../MainPage';

describe('MainPage', () => {
    test('should render welcome page', () => {
        const component = shallow(<MainPage page='home' />);
        expect(component.find('Welcome')).toHaveLength(1);
        expect(component.find('ListBooks')).toHaveLength(0);
        expect(component.find('NewBook')).toHaveLength(0);
        expect(component.find('UpdateBook')).toHaveLength(0);
        expect(component.find('DeleteBook')).toHaveLength(0);
    });

    test('should render list books page', () => {
        const component = shallow(<MainPage page='list' />);
        expect(component.find('Welcome')).toHaveLength(0);
        expect(component.find('ListBooks')).toHaveLength(1);
        expect(component.find('NewBook')).toHaveLength(0);
        expect(component.find('UpdateBook')).toHaveLength(0);
        expect(component.find('DeleteBook')).toHaveLength(0);
    });

    test('should render new book page', () => {
        const component = shallow(<MainPage page='create' />);
        expect(component.find('Welcome')).toHaveLength(0);
        expect(component.find('ListBooks')).toHaveLength(0);
        expect(component.find('NewBook')).toHaveLength(1);
        expect(component.find('UpdateBook')).toHaveLength(0);
        expect(component.find('DeleteBook')).toHaveLength(0);
    });

    test('should render update book page', () => {
        const component = shallow(<MainPage page='update' />);
        expect(component.find('Welcome')).toHaveLength(0);
        expect(component.find('ListBooks')).toHaveLength(0);
        expect(component.find('NewBook')).toHaveLength(0);
        expect(component.find('UpdateBook')).toHaveLength(1);
        expect(component.find('DeleteBook')).toHaveLength(0);
    });
    
    test('should render delete book page', () => {
        const component = shallow(<MainPage page='delete' />);
        expect(component.find('Welcome')).toHaveLength(0);
        expect(component.find('ListBooks')).toHaveLength(0);
        expect(component.find('NewBook')).toHaveLength(0);
        expect(component.find('UpdateBook')).toHaveLength(0);
        expect(component.find('DeleteBook')).toHaveLength(1);
    });
    test('should render in construcction page', () => {
        const component = shallow(<MainPage page='test' />);
        expect(component.find('Welcome')).toHaveLength(0);
        expect(component.find('ListBooks')).toHaveLength(0);
        expect(component.find('NewBook')).toHaveLength(0);
        expect(component.find('UpdateBook')).toHaveLength(0);
        expect(component.find('DeleteBook')).toHaveLength(0);
        expect(component.find('.inConstruction')).toHaveLength(1);
    });
});
