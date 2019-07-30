import axios from 'axios';
import { PATH_BASE, PATH_BOOKS } from '../Constants';

export async function getBook() {
    let result, error, success;
    
    try {
        result = await axios.get(`${PATH_BASE}${PATH_BOOKS}`);
        error = null;
        success = true;
    } catch (err) {
        result = null;
        error = err;
        success = false;
    }
    
    return { result, error, success };
}

export async function getBookById(bookId) {
    let success;
    
    try {
        const response = await axios.get(`${PATH_BASE}${PATH_BOOKS}/${bookId}`);
        success = true;
        return { response, success };
    } catch (err) {
        success = false
        return { err, success };
    }
    
}

export async function saveBook(book) {
    try {
        await axios.post(`${PATH_BASE}${PATH_BOOKS}`, book);
        return 'success';
    } catch (err) {
        return err;
    }
}

export async function updateBook(bookId, book) {
    let success;
    try {
        await axios.put(`${PATH_BASE}${PATH_BOOKS}/${bookId}`, book)
        success = true;
        return { success };
    } catch(err) {
        success = false;
        return { success, err };
    }
}

export async function deleteBook(bookId) {
    let success;
    try {
        await axios.delete(`${PATH_BASE}${PATH_BOOKS}/${bookId}`);
        success = true;
        return { success }; 
    } catch (err) {
        success = false;
        return { success, err };
    }
}