import React from 'react';
import App from '../App';
import { mount } from 'enzyme';
import { Navigation } from '../Navigation';
import { ListBooks } from '../../Books';
import { Welcome } from '../Welcome';

describe('App - Navigation', () => {
    test('should render a specific page when a item in navigation is clicked', () => {
        const component = mount(<App />);
        
        expect(component.find(Welcome)).toHaveLength(1);
        expect(component.find(ListBooks)).toHaveLength(0);

        component.find(Navigation).find('td').at(1).simulate('click');
        expect(component.find(Welcome)).toHaveLength(0);
        expect(component.find(ListBooks)).toHaveLength(1);
    });
});
