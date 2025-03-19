import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from '../components/Header';
import Footer from '../components/Footer';

const Login = () => {
  const [credentials, setCredentials] = useState({ email: '', password: '' });
  const [message, setMessage] = useState('');
  const navigate = useNavigate();
  const location = useLocation();

  // Получаем предыдущий путь из sessionStorage
  useEffect(() => {
    sessionStorage.setItem('lastVisitedPath', location.state?.from || '/');
  }, [location]);

  const handleChange = (e) => {
    setCredentials({ ...credentials, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(credentials),
        credentials: 'include'
      });
      const result = await response.text();

      if (result === 'Login successful') {
        const lastVisitedPath = sessionStorage.getItem('lastVisitedPath') || '/';
        navigate(lastVisitedPath, { replace: true });
      } else {
        setMessage(result);
      }
    } catch (error) {
      setMessage('Ошибка авторизации');
    }
  };

  return (
    <>
      <Header />
      <div className="container mt-4 vh-100">
        <h2>Вход</h2>
        {message && <p className="text-danger">{message}</p>}
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label>Email:</label>
            <input type="email" name="email" className="form-control" value={credentials.email} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label>Пароль:</label>
            <input type="password" name="password" className="form-control" value={credentials.password} onChange={handleChange} required />
          </div>
          <button type="submit" className="btn btn-primary">Войти</button>
        </form>
      </div>
      <Footer />
    </>
  );
};

export default Login;
