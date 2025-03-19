import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';

const statusOptions = [
  'All', 'Scheduled', 'On Time', 'Delayed', 'Departed', 'Arrived', 'Cancelled'
];

const FlightResultsTwoColumnsComponent = ({ flightsData }) => {
  const navigate = useNavigate();
  const [depSortOrder, setDepSortOrder] = useState('asc'); // Сортировка по времени отправления
  const [depStatusFilter, setDepStatusFilter] = useState('All');

  const [arrSortOrder, setArrSortOrder] = useState('asc'); // Сортировка по времени прибытия
  const [arrStatusFilter, setArrStatusFilter] = useState('All');

  const filteredDepartures = flightsData.departures
    .filter(flight => depStatusFilter === 'All' || flight.status === depStatusFilter)
    .sort((a, b) => {
      const timeA = new Date(a.scheduledDeparture).getTime();
      const timeB = new Date(b.scheduledDeparture).getTime();
      return depSortOrder === 'asc' ? timeA - timeB : timeB - timeA;
    });

  const filteredArrivals = flightsData.arrivals
    .filter(flight => arrStatusFilter === 'All' || flight.status === arrStatusFilter)
    .sort((a, b) => {
      const timeA = new Date(a.scheduledArrival).getTime();
      const timeB = new Date(b.scheduledArrival).getTime();
      return arrSortOrder === 'asc' ? timeA - timeB : timeB - timeA;
    });

  return (
    <div className="container mt-5">
      <div className="row">
        {/* Колонка "Отправление" */}
        <div className="col-md-6">
          <h2>Отправление</h2>
          <div className="d-flex mb-2">
            <div className="me-2">
              <label>Сортировать по времени:</label>
              <select
                className="form-select"
                value={depSortOrder}
                onChange={(e) => setDepSortOrder(e.target.value)}
              >
                <option value="asc">По возрастанию</option>
                <option value="desc">По убыванию</option>
              </select>
            </div>
            <div>
              <label>Фильтр по статусу:</label>
              <select
                className="form-select"
                value={depStatusFilter}
                onChange={(e) => setDepStatusFilter(e.target.value)}
              >
                {statusOptions.map((status, idx) => (
                  <option key={idx} value={status}>{status}</option>
                ))}
              </select>
            </div>
          </div>
          {filteredDepartures.length === 0 ? (
            <p>Рейсы не найдены.</p>
          ) : (
            <table className="table table-striped table-hover">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Номер рейса</th>
                  <th>Время вылета</th>
                  <th>Статус</th>
                </tr>
              </thead>
              <tbody>
                {filteredDepartures.map((flight) => (
                  <tr
                    key={flight.flightId}
                    style={{ cursor: 'pointer' }}
                    onClick={() => navigate(`/flight/${flight.flightId}`)}
                  >
                    <td>{flight.flightId}</td>
                    <td>{flight.flightNo}</td>
                    <td>{new Date(flight.scheduledDeparture).toLocaleString()}</td>
                    <td>{flight.status}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>

        {/* Колонка "Прибытие" */}
        <div className="col-md-6">
          <h2>Прибытие</h2>
          <div className="d-flex mb-2">
            <div className="me-2">
              <label>Сортировать по времени:</label>
              <select
                className="form-select"
                value={arrSortOrder}
                onChange={(e) => setArrSortOrder(e.target.value)}
              >
                <option value="asc">По возрастанию</option>
                <option value="desc">По убыванию</option>
              </select>
            </div>
            <div>
              <label>Фильтр по статусу:</label>
              <select
                className="form-select"
                value={arrStatusFilter}
                onChange={(e) => setArrStatusFilter(e.target.value)}
              >
                {statusOptions.map((status, idx) => (
                  <option key={idx} value={status}>{status}</option>
                ))}
              </select>
            </div>
          </div>
          {filteredArrivals.length === 0 ? (
            <p>Рейсы не найдены.</p>
          ) : (
            <table className="table table-striped table-hover">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Номер рейса</th>
                  <th>Время прибытия</th>
                  <th>Статус</th>
                </tr>
              </thead>
              <tbody>
                {filteredArrivals.map((flight) => (
                  <tr
                    key={flight.flightId}
                    style={{ cursor: 'pointer' }}
                    onClick={() => navigate(`/flight/${flight.flightId}`)}
                  >
                    <td>{flight.flightId}</td>
                    <td>{flight.flightNo}</td>
                    <td>{new Date(flight.scheduledArrival).toLocaleString()}</td>
                    <td>{flight.status}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
      </div>
    </div>
  );
};

export default FlightResultsTwoColumnsComponent;
