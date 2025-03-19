import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from '../components/Header';
import Footer from '../components/Footer';

const FlightDetails = () => {
  const { flightId } = useParams();
  const navigate = useNavigate();
  const [flight, setFlight] = useState(null);
  const [message, setMessage] = useState('');

  useEffect(() => {
    // Получение деталей рейса по flightId
    fetch(`/api/flights/${flightId}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Рейс не найден');
        }
        return response.json();
      })
      .then(data => setFlight(data))
      .catch(error => console.error('Ошибка загрузки данных о рейсе:', error));
  }, [flightId]);

  const handleBooking = async () => {
    const response = await fetch('/api/bookings/create', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      credentials: 'include',
      body: JSON.stringify({ flightId })
    });
    const result = await response.text();

    if (result === "User not authenticated") {
      // Если не авторизован, перенаправляем на страницу логина
      navigate('/login', { state: { from: `/flight/${flightId}` } });
    } else {
      setMessage("Бронирование успешно!");
    }
  };

  if (!flight) {
    return <p className="text-center">Загрузка...</p>;
  }

  return (
    <>
      <Header />
      <div className="container mt-4  vh-100">
        <h2>Детали рейса</h2>
        {message && <p className="text-success">{message}</p>}
        <div className="card p-3">
          <p><strong>Номер рейса:</strong> {flight.flightNo}</p>
          <p><strong>Время вылета:</strong> {new Date(flight.scheduledDeparture).toLocaleString()}</p>
          <p><strong>Время прибытия:</strong> {new Date(flight.scheduledArrival).toLocaleString()}</p>
          <p><strong>Аэропорт вылета:</strong> {flight.departureAirport}</p>
          <p><strong>Аэропорт прибытия:</strong> {flight.arrivalAirport}</p>
          <p><strong>Статус:</strong> {flight.status}</p>
          <button className="btn btn-primary" onClick={handleBooking}>Забронировать</button>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default FlightDetails;
