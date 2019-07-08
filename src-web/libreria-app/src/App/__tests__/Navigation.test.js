import React from 'react';
import renderer from 'react-test-renderer';
import { shallow } from 'enzyme';
import { Navigation } from '../Navigation';

const setPageName = jest.fn();

describe('Navigation', () => {
    test('call setPageName with "home" when click on first item', () => {
        const component = shallow(<Navigation setPageName={setPageName} />);
        const lListBooksComponent = component.find('p').at(0);
        lListBooksComponent.simulate('click');
        expect(setPageName).toBeCalledWith('home');
    });
    
    test('call setPageName with "list" when click on second item', () => {
        const component = shallow(<Navigation setPageName={setPageName} />);
        const lListBooksComponent = component.find('p').at(1);
        lListBooksComponent.simulate('click');
        expect(setPageName).toBeCalledWith('list');
    });
    
    test('call setPageName with "create" when click on third item', () => {
        const component = shallow(<Navigation setPageName={setPageName} />);
        const lListBooksComponent = component.find('p').at(2);
        lListBooksComponent.simulate('click');
        expect(setPageName).toBeCalledWith('create');
    });

    test('call setPageName with "update" when click on fourth item', () => {
        const component = shallow(<Navigation setPageName={setPageName} />);
        const lListBooksComponent = component.find('p').at(3);
        lListBooksComponent.simulate('click');
        expect(setPageName).toBeCalledWith('update');
    });
    
    test('call setPageName with "delete" when click on fifth item', () => {
        const component = shallow(<Navigation setPageName={setPageName} />);
        const lListBooksComponent = component.find('p').at(4);
        lListBooksComponent.simulate('click');
        expect(setPageName).toBeCalledWith('delete');
    });
});