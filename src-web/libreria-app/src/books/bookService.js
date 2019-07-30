import axios from 'axios';
import { PATH_BASE, PATH_BOOKS } from '../Constants';

export async function getBook() {
    try {
        const result = await axios.get(`${PATH_BASE}${PATH_BOOKS}`);
        return {
            success: true,
            error: null,
            result
        };
    } catch (error) {
        return {
            success: false,
            error,
            result: null
        }
    }
}

export async function getBookById(bookId) {
    try {
        const result = await axios.get(`${PATH_BASE}${PATH_BOOKS}/${bookId}`);
        return { 
            success: true,
            error: null,
            result 
        };
    } catch (error) {
        return {
            success: false, 
            error, 
            result: null 
        };
    }
}

export async function saveBook(book) {
    try {
        await axios.post(`${PATH_BASE}${PATH_BOOKS}`, book);
        return { success: true, error: null };
    } catch (error) {
        return { success: false, error };
    }
}

export async function updateBook(bookId, book) {
    try {
        await axios.put(`${PATH_BASE}${PATH_BOOKS}/${bookId}`, book)
        return { success: true, error: null };
    } catch(error) {
        return { success: false, error };
    }
}

export async function deleteBook(bookId) {
    try {
        await axios.delete(`${PATH_BASE}${PATH_BOOKS}/${bookId}`);
        return { success: true, error: null }; 
    } catch (error) {
        return { success: false, error };
    }
}