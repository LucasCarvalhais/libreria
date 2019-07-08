import React from 'react';
import { shallow } from 'enzyme';
import { Navigation } from '../Navigation';

const setPageName = jest.fn();

describe('Navigation', () => {
    const component = shallow(<Navigation setPageName={setPageName} />);
    
    test('check if there are five columns', () => {
        expect(component.find('td')).toHaveLength(5);
    })

    test('call setPageName with "home" when click on first item', () => {
        component.find('td').at(0).simulate('click');
        expect(setPageName).toBeCalledWith('home');
    });
    
    test('call setPageName with "list" when click on second item', () => {
        component.find('td').at(1).simulate('click');
        expect(setPageName).toBeCalledWith('list');
    });
    
    test('call setPageName with "create" when click on third item', () => {
        component.find('td').at(2).simulate('click');
        expect(setPageName).toBeCalledWith('create');
    });

    test('call setPageName with "update" when click on fourth item', () => {
        component.find('td').at(3).simulate('click');
        expect(setPageName).toBeCalledWith('update');
    });
    
    test('call setPageName with "delete" when click on fifth item', () => {
        component.find('td').at(4).simulate('click');
        expect(setPageName).toBeCalledWith('delete');
    });
});