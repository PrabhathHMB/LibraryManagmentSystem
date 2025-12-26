import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import type { Book } from '../types/Book'; // ✅ Fixes 'Type-only import' error
import { getAllBooks, deleteBook } from '../services/BookService';

const BookList: React.FC = () => {
    const [books, setBooks] = useState<Book[]>([]);

    // ✅ FIX: Function defined BEFORE it is used
    const loadBooks = async () => {
        try {
            const response = await getAllBooks();
            setBooks(response.data);
        } catch (error) {
            console.error("Error fetching books", error);
        }
    };

    // ✅ FIX: useEffect is called AFTER the function is defined and runs async to avoid sync setState
    useEffect(() => {
        let mounted = true;
        (async () => {
            try {
                const response = await getAllBooks();
                if (mounted) {
                    setBooks(response.data);
                }
            } catch (error) {
                console.error("Error fetching books", error);
            }
        })();
        return () => {
            mounted = false;
        };
    }, []);

    const handleDelete = async (id: string) => {
        if (confirm("Are you sure you want to delete this book?")) {
            await deleteBook(id);
            loadBooks(); 
        }
    };

    return (
        <div className="container">
            <div className="header-row">
                <h2>Library — Book List</h2>
                <Link to="/add" className="btn btn-primary">Add Book</Link>
            </div>
            <table className="book-table">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Year</th>
                        <th>Genre</th>
                        <th>Shelf</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {books.map((book) => (
                        <tr key={book.id}>
                            <td>{book.title}</td>
                            <td>{book.author}</td>
                            <td>{book.publicationYear}</td>
                            <td>{book.genre}</td>
                            <td>{book.shelfLocation}</td>
                            <td>
                                <Link to={`/edit/${book.id}`} className="btn btn-edit">Edit</Link>
                                <button onClick={() => book.id && handleDelete(book.id)} className="btn btn-delete">Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default BookList;