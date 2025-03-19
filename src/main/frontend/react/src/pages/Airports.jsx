import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import AirportsMap from '../components/AirportsMap';
import Header from '../components/Header';
import Footer from '../components/Footer';

const Airports = () => {
  const [airports, setAirports] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchAirports = async () => {
      setLoading(true);
      try {
        const response = await fetch('/api/airports');
        if (!response.ok) {
          throw new Error('Ошибка при получении данных аэропортов');
        }
        const data = await response.json();
        setAirports(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchAirports();
  }, []);

  return (
    <>
      <Header />
      <AirportsMap />
      <div className="container mt-4 min-vh-100">
      <h2>Список аэропортов</h2>
      {loading && <p>Загрузка...</p>}
      {error && <p style={{ color: 'red' }}>Ошибка: {error}</p>}
      {airports.length > 0 ? (
          <table className="table table-striped">
          <thead>
              <tr>
              <th>Код аэропорта</th>
              <th>Название</th>
              <th>Город</th>
              <th>Координаты</th>
              <th>Часовой пояс</th>
              </tr>
          </thead>
          <tbody>
              {airports.map((airport) => (
              <tr key={airport.airportCode}>
                  <td>{airport.airportCode}</td>
                  <td>{airport.airportName}</td>
                  <td>{airport.city}</td>
                  <td>{airport.coordinates}</td>
                  <td>{airport.timezone}</td>
              </tr>
              ))}
          </tbody>
          </table>
      ) : (
          !loading && <p>Аэропорты не найдены.</p>
      )}
      </div>
      <Footer />
    </>
  );
};

export default Airports;
