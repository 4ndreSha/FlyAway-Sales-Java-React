import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from '../components/Header';
import Footer from '../components/Footer';

const Profile = () => {
  const [user, setUser] = useState(null);
  const [editableUser, setEditableUser] = useState(null);
  const [editing, setEditing] = useState(false);
  const [message, setMessage] = useState('');
  const [tickets, setTickets] = useState([]);

  useEffect(() => {
    fetch('/api/user/info', { credentials: 'include' })
      .then(response => response.json())
      .then(data => {
        setUser(data);
        setEditableUser({ ...data });
      })
      .catch(error => console.error('Ошибка загрузки пользователя:', error));

    fetch('/api/user/tickets', { credentials: 'include' })
      .then(response => response.json())
      .then(data => setTickets(data))
      .catch(error => console.error('Ошибка загрузки билетов:', error));
  }, []);

  const formatCurrency = (amount) => {
    return new Intl.NumberFormat('ru-RU', {
      style: 'currency',
      currency: 'RUB',
      minimumFractionDigits: 2
    }).format(amount);
  };

  const handleEditChange = (e) => {
    setEditableUser({ ...editableUser, [e.target.name]: e.target.value });
  };

  const handleSave = async () => {
    try {
      const response = await fetch('/api/user/update', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(editableUser),
        credentials: 'include'
      });

      const result = await response.text();
      if (result === "User updated successfully") {
        setUser({ ...editableUser });
        setEditing(false);
        setMessage("Данные успешно обновлены!");
        setTimeout(() => setMessage(''), 3000);
      } else {
        setMessage("Ошибка обновления данных");
      }
    } catch (error) {
      setMessage("Ошибка обновления данных");
    }
  };

  if (!user) {
    return <p className="text-center">Загрузка...</p>;
  }

  return (
    <>
      <Header />
      <div className="container mt-4 vh-100">
        <h2>Личный кабинет</h2>
        {message && <p className="text-success">{message}</p>}
        
        <div className="card p-3 mb-3">
          {editing ? (
            <>
              <div className="mb-3">
                <label className="form-label">Email:</label>
                <input type="email" name="email" 
                      className="form-control" 
                      value={editableUser.email} 
                      onChange={handleEditChange} 
                      required />
              </div>
              
              <div className="mb-3">
                <label className="form-label">Имя:</label>
                <input type="text" name="firstName" 
                      className="form-control" 
                      value={editableUser.firstName} 
                      onChange={handleEditChange} 
                      required />
              </div>

              <div className="mb-3">
                <label className="form-label">Фамилия:</label>
                <input type="text" name="lastName" 
                      className="form-control" 
                      value={editableUser.lastName} 
                      onChange={handleEditChange} 
                      required />
              </div>

              <div className="mb-3">
                <label className="form-label">Отчество:</label>
                <input type="text" name="patronymic" 
                      className="form-control" 
                      value={editableUser.patronymic || ''} 
                      onChange={handleEditChange} />
              </div>

              <div className="mb-3">
                <label className="form-label">Телефон:</label>
                <input type="text" name="phone" 
                      className="form-control" 
                      value={editableUser.phone} 
                      onChange={handleEditChange} />
              </div>

              <div className="d-grid gap-2 d-md-flex justify-content-md-end">
                <button className="btn btn-success me-md-2" 
                        onClick={handleSave}>
                  Сохранить
                </button>
                <button className="btn btn-secondary" 
                        onClick={() => setEditing(false)}>
                  Отмена
                </button>
              </div>
            </>
          ) : (
            <>
              <h4>{user.firstName} {user.lastName}</h4>
              <p>Email: {user.email}</p>
              {user.patronymic && <p>Отчество: {user.patronymic}</p>}
              <p>Телефон: {user.phone}</p>
              <button className="btn btn-primary" 
                      onClick={() => setEditing(true)}>
                Редактировать профиль
              </button>
            </>
          )}
        </div>

        <h3>История бронирований</h3>
        {tickets.length === 0 ? (
          <p>Нет забронированных билетов.</p>
        ) : (
          <div className="table-responsive">
            <table className="table table-striped table-hover">
              <thead className="thead-dark">
                <tr>
                  <th>Номер билета</th>
                  <th>Номер брони</th>
                  <th>Дата бронирования</th>
                  <th>Сумма</th>
                  <th>Статус</th>
                </tr>
              </thead>
              <tbody>
                {tickets.map(ticket => (
                  <tr key={ticket.ticketNo}>
                    <td>{ticket.ticketNo}</td>
                    <td>{ticket.bookRef}</td>
                    <td>{new Date(ticket.bookDate).toLocaleDateString('ru-RU', {
                      year: 'numeric',
                      month: 'long',
                      day: 'numeric',
                      hour: '2-digit',
                      minute: '2-digit'
                    })}</td>
                    <td>{formatCurrency(ticket.totalAmount)}</td>
                    <td>
                      <span className="badge bg-success">
                        {ticket.status || 'Подтверждено'}
                      </span>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
      <Footer />
    </>
  );
};

export default Profile;