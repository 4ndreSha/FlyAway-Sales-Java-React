import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from '../components/Header';
import Footer from '../components/Footer';

const Registration = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    patronymic: '',
    phone: ''
  });
  const [message, setMessage] = useState('');
  const navigate = useNavigate();
  const location = useLocation();

  // Запоминаем предыдущий путь перед регистрацией
  useEffect(() => {
    sessionStorage.setItem('lastVisitedPath', location.state?.from || '/');
  }, [location]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData),
        credentials: 'include'
      });
      const result = await response.text();

      if (result === 'Registration successful') {
        const lastVisitedPath = sessionStorage.getItem('lastVisitedPath') || '/';
        navigate(lastVisitedPath, { replace: true });
      } else {
        setMessage(result);
      }
    } catch (error) {
      setMessage('Ошибка регистрации');
    }
  };

  return (
    <>
      <Header />
      <div className="container mt-4 vh-100">
        <h2>Регистрация</h2>
        {message && <p className="text-danger">{message}</p>}
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label>Email:</label>
            <input type="email" name="email" className="form-control" value={formData.email} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label>Пароль:</label>
            <input type="password" name="password" className="form-control" value={formData.password} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label>Имя:</label>
            <input type="text" name="firstName" className="form-control" value={formData.firstName} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label>Фамилия:</label>
            <input type="text" name="lastName" className="form-control" value={formData.lastName} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label>Отчество:</label>
            <input type="text" name="patronymic" className="form-control" value={formData.patronymic} onChange={handleChange} />
          </div>
          <div className="mb-3">
            <label>Телефон:</label>
            <input type="text" name="phone" className="form-control" value={formData.phone} onChange={handleChange} />
          </div>
          <button type="submit" className="btn btn-primary">Зарегистрироваться</button>
        </form>
      </div>
      <Footer />
    </>
  );
};

export default Registration;
