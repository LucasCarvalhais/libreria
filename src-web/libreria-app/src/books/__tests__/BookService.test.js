import * as BookService from '../bookService';
import axios from 'axios';

jest.mock('axios');

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

const error = { error: 'No Network Available' };

describe ('BookService', () => {
    test('should get all books', () => {
        axios.get.mockResolvedValue(response);
        const expectedResponse = { 
            success: true,
            result: response,
            error: null,
        };

        return BookService.getBook().then(data => { 
            expect(data).toEqual(expectedResponse);
        });
    });

    test('should catch error after failing to get all books', () => {
        axios.get.mockImplementation(() => Promise.reject(error));
        const expectedResponse = { 
            success: false,
            result: null, 
            error 
        };
        
        return BookService.getBook().then(data => {
            expect(data).toEqual(expectedResponse);
        })
    });

    test('should get book by id', () => {
        const book = data.book[0];
        const response = { data: book }
        axios.get.mockImplementation(() => Promise.resolve(response));
        const expectedResponse = {
            success: true,
            result: response,
            error: null
        };

        return BookService.getBookById(1).then(data => {
            expect(data).toEqual(expectedResponse);
        })
    });

    test('should catch error after failing to get book by id', () => {
        axios.get.mockImplementation(() => Promise.reject(error));
        const expectedResponse = {
            success: false,
            result: null,
            error
        };

        return BookService.getBookById(-2).then(data => {
            expect(data).toEqual(expectedResponse);
        })
    });

    test('should save a new book', () => {
        axios.post.mockImplementation(() => Promise.resolve());
        const expectedResponse = {
            success: true,
            error: null
        }

        return BookService.saveBook().then(output => {
            expect(output).toEqual(expectedResponse);
        })
    })

    test('should catch error after failing to save a new book', () => {
        axios.post.mockImplementation(() => Promise.reject(error));
        const expectedResponse = {
            success: false,
            error
        }

        return BookService.saveBook().then(output => {
            expect(output).toEqual(expectedResponse);
        })
    })

    test('should update book', () => {
        axios.put.mockImplementation(() => Promise.resolve());
        const expectedResponse = {
            success: true,
            error: null,
        }

        return BookService.updateBook(1, null).then(output => {
            expect(output).toEqual(expectedResponse);
        })
    })

    test('should catch error after failing to update book', () => {
        axios.put.mockImplementation(() => Promise.reject(error));
        const expectedResponse = {
            success: false,
            error
        }

        return BookService.updateBook(1, null).then(output => {
            expect(output).toEqual(expectedResponse);
        })
    })

    test('should delete book', () => {
        axios.delete.mockImplementation(() => Promise.resolve());
        const expectedResponse = {
            success: true,
            error: null,
        }

        return BookService.deleteBook(1).then(output => {
            expect(output).toEqual(expectedResponse);
        })
    })

    test('should catch error after failing to delete book', () => {
        axios.delete.mockImplementation(() => Promise.reject(error));
        const expectedResponse = {
            success: false,
            error
        }

        return BookService.deleteBook(1, null).then(output => {
            expect(output).toEqual(expectedResponse);
        })
    })
});