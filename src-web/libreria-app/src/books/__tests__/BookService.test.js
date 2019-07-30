import { getBook } from '../bookService';
import axios from 'axios';

jest.mock('axios');

describe ('BookService', () => {
    const data = { book: [{
        bookId: 1,
        title: 'Un',
        description: 'Premier',
        author: 'teste',
        edition: 1
    }, {
        bookId: 2,
        title: 'Deux',
        description: 'Deuxième',
        author: 'teste',
        edition: 2
    }] };
    const response = { data };
    const expectedResponse = { 
        result: response, 
        error: null, 
        success: true 
    };
    
    test('should get all books', () => {
        axios.get.mockResolvedValue(response);
        
        return getBook().then(data => { 
            expect(data).toEqual(expectedResponse);
        });
    })

    test('should get error', () => {
        const error = { error: 'No Network Available' }
        axios.get.mockImplementation(() => Promise.reject(error));

        const expectedResponse = { result: null, error, success: false };
        return getBook().then(data => {
            expect(data).toEqual(expectedResponse);
        })
    })
})