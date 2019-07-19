import React from 'react';
import ReactDom from 'react-dom';
import { shallow } from 'enzyme';
import App from '../App';

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
