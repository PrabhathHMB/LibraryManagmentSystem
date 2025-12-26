import { BrowserRouter as Router, Routes, Route, Link, useLocation } from 'react-router-dom';
import BookList from './components/BookList';
import AddBook from './components/AddBook';
import EditBook from './components/EditBook';
import './App.css';

// Helper component to highlight active link
const NavLink = ({ to, children }: { to: string, children: React.ReactNode }) => {
  const location = useLocation();
  const isActive = location.pathname === to;
  return (
    <Link to={to} className={isActive ? 'nav-link active' : 'nav-link'}>
      {children}
    </Link>
  );
};

function App() {
  return (
    <Router>
      <div className="app-container">
        
        {/* Header Section matching Lab Sheet */}
        <header className="top-header">
          <div className="brand-section">
            <div className="logo-box">LB</div>
            <div className="title-text">
              <h1>Library Manager</h1>
              <p>React + Vite Frontend + Friendly UI</p>
            </div>
          </div>
          <nav className="main-nav">
            <NavLink to="/">Books</NavLink>
            <NavLink to="/add">Add Book</NavLink>
          </nav>
        </header>

        {/* Main Content Area */}
        <main className="content-area">
          <Routes>
            <Route path="/" element={<BookList />} />
            <Route path="/add" element={<AddBook />} />
            <Route path="/edit/:id" element={<EditBook />} />
          </Routes>
        </main>

      </div>
    </Router>
  );
}

export default App;

