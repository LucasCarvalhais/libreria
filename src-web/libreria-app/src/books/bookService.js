import axios from 'axios';
import { PATH_BASE, PATH_BOOKS } from '../Constants';

export async function getBook() {
    let result, error;
    try {
        result = await axios.get(`${PATH_BASE}${PATH_BOOKS}`);
    } catch (err) {
        error = err;
    }
    return { result, error };
}
