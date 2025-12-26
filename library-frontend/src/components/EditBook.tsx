import React, { useEffect, useState } from 'react';
import { useNavigate, useParams, Link } from 'react-router-dom';
import { getBookById, updateBook } from '../services/BookService';
import type { Book } from '../types/Book'; // ✅ Fixes 'Type-only import' error

const EditBook: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();
    const [book, setBook] = useState<Book | null>(null);

    // ✅ FIX: Function defined BEFORE it is used
    const loadBook = async (id: string) => {
        try {
            const response = await getBookById(id);
            setBook(response.data);
        } catch (error) {
            console.error("Error fetching book", error);
        }
    };

    // ✅ FIX: useEffect called AFTER function definition
    useEffect(() => {
        if (!id) return;
        const fetchBook = async () => {
            await loadBook(id);
        };
        void fetchBook();
    }, [id]);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (book) {
            const { name, value } = e.target;
            setBook({ ...book, [name]: value });
        }
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (id && book) {
            await updateBook(id, book);
            navigate('/');
        }
    };

    if (!book) return <p>Loading...</p>;

    return (
        <div className="container">
            <h2>Edit Book</h2>
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
                    <button type="submit" className="btn btn-primary">Update Book</button>
                    <Link to="/" className="btn btn-secondary">Cancel</Link>
                </div>
            </form>
        </div>
    );
};

export default EditBook;