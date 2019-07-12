import React from 'react';
import ListBooks from '../ListBooks';
import { mount } from 'enzyme';
import axios from 'axios';

jest.mock('axios');

describe('ListBooks', () => {
    test('should load books', async () => {
        const response = {
            data: [{
                bookId: 1,
                title: "Un",
                description: "Uno",
                author: "One",
                edition: 1,
            },
            {
                bookId: 2,
                title: "Deux",
                description: "Dos",
                author: "Two",
                edition: 1,
            }]
        };
        axios.get.mockResolvedValue(response);
        const component = mount(<ListBooks />);
        const instance = component.instance();
        await instance.componentDidMount();
        expect(instance.state.isLoading).toBe(false);
        expect(instance.state.books).toBe(response.data);
    });
});

// How to test when axios fails?