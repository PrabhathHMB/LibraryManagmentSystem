import axios from 'axios';
import type { Book } from '../types/Book';

// ✅ Ensure this matches your Spring Boot port (default is 8080)
const API_URL = 'http://localhost:8080/api/books';

export const getAllBooks = async () => {
    return await axios.get<Book[]>(API_URL);
};

export const getBookById = async (id: string) => {
    return await axios.get<Book>(`${API_URL}/${id}`);
};

export const createBook = async (book: Book) => {
    return await axios.post<Book>(API_URL, book);
};

export const updateBook = async (id: string, book: Book) => {
    return await axios.put<Book>(`${API_URL}/${id}`, book);
};

export const deleteBook = async (id: string) => {
    return await axios.delete(`${API_URL}/${id}`);
};