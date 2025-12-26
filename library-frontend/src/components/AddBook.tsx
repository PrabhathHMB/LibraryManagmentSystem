import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { createBook } from '../services/BookService';
import type { Book } from '../types/Book'; 

const AddBook: React.FC = () => {
    const navigate = useNavigate();
    const [book, setBook] = useState<Book>({
        title: '',
        author: '',
        genre: '',
        publicationYear: new Date().getFullYear(),
        shelfLocation: ''
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setBook({ ...book, [name]: value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            await createBook(book);
            navigate('/');
        } catch (error) {
            console.error("Error adding book", error);
        }
    };

    return (
        <div className="container">
            <h2>Add Book</h2>
            <form onSubmit={handleSubmit} className="book-form">
                <label>Title</label>
                <input type="text" name="title" value={book.title} onChange={handleChange} required />

                <label>Author</label>
                <input type="text" name="author" value={book.author} onChange={handleChange} required />

                <label>Publication Year</label>
                <input type="number" name="publicationYear" value={book.publicationYear} onChange={handleChange} required />

                <label>Genre</label>
                <input type="text" name="genre" value={book.genre} onChange={handleChange} required />

                <label>Shelf Location</label>
                <input type="text" name="shelfLocation" value={book.shelfLocation} onChange={handleChange} required />

                <div className="form-actions">
                    <button type="submit" className="btn btn-primary">Save Book</button>
                    <Link to="/" className="btn btn-secondary">Cancel</Link>
                </div>
            </form>
        </div>
    );
};

export default AddBook;