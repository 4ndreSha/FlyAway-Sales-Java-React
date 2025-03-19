import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const SearchFlightsComponent = ({ onSearch }) => {
  const [city, setCity] = useState('');
  const [airportCode, setAirportCode] = useState('');
  const [airportData, setAirportData] = useState([]);
  const [airportOptions, setAirportOptions] = useState([]);
  const [citySuggestions, setCitySuggestions] = useState([]);

  const navigate = useNavigate();
  const location = useLocation();


  useEffect(() => {
    const fetchAirports = async () => {
      try {
        const response = await fetch('/api/airports');
        if (!response.ok) {
          throw new Error('Ошибка при получении данных аэропортов');
        }
        const data = await response.json();
        setAirportData(data);
      } catch (err) {
        console.error(err);
      }
    };

    fetchAirports();
  }, []);

  const allCities = [...new Set(airportData.map(item => item.city))];

  const handleCityChange = (e) => {
    const value = e.target.value;
    setCity(value);
    const suggestions = allCities.filter(c =>
      c.toLowerCase().includes(value.toLowerCase())
    );
    setCitySuggestions(suggestions);
    if (allCities.includes(value)) {
      const options = airportData.filter(item => item.city === value);
      setAirportOptions(options);
    } else {
      setAirportOptions([]);
    }
    setAirportCode('');
  };

  const handleSuggestionClick = (suggestion) => {
    setCity(suggestion);
    setCitySuggestions([]);
    const options = airportData.filter(item => item.city === suggestion);
    setAirportOptions(options);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!airportCode) {
      alert("Пожалуйста, выберите аэропорт из списка после выбора города.");
      return;
    }
    
    const searchParams = new URLSearchParams(location.search);
    searchParams.set('airportCode', airportCode);
    navigate({
      pathname: location.pathname,
      search: searchParams.toString()
    });
  };


  return (
    <main className="search-main">
      <div className="container py-2 search-container">
        <h2 className="mb-4">Поиск рейсов</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-3 position-relative">
            <label htmlFor="city" className="form-label">Город</label>
            <input
              type="text"
              className="form-control"
              id="city"
              placeholder="Введите город"
              required
              value={city}
              onChange={handleCityChange}
            />
            {citySuggestions.length > 0 && (
              <ul className="list-group position-absolute suggestions-list">
                {citySuggestions.map((suggestion, index) => (
                  <li
                    key={index}
                    className="list-group-item list-group-item-action"
                    onClick={() => handleSuggestionClick(suggestion)}
                    style={{ cursor: 'pointer' }}
                  >
                    {suggestion}
                  </li>
                ))}
              </ul>
            )}
          </div>
          <div className="mb-3">
            <label htmlFor="airport" className="form-label">Аэропорт</label>
            <select
              className="form-select"
              id="airport"
              required
              value={airportCode}
              onChange={(e) => {setAirportCode(e.target.value); console.log(e.target.value)}}
            >
              <option value="">Выберите аэропорт</option>
              {airportOptions.map((item, index) => (
                <option key={index} value={item.airportCode}>
                  {item.airportCode} - {item.airportName}
                </option>
              ))}
            </select>
          </div>
          <button type="submit" className="btn btn-primary">Найти рейсы</button>
        </form>
      </div>
    </main>
  );
};

export default SearchFlightsComponent;
