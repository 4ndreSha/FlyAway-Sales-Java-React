import React, { useState, useEffect } from 'react';
import SearchFlightsComponent from '../components/SearchFlightsComponent';
import FlightResultsTwoColumnsComponent from '../components/FlightResultsTwoColumnsComponent';
import { useSearchParams } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';

const Search = () => {
  const [flights, setFlights] = useState(null); // Ожидается объект { departures: [...], arrivals: [...] }
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [urlParams, setUrlParams] = useSearchParams();

  const handleSearch = async (searchParams) => {
    setLoading(true);
    setError(null);
    try {
      const response = await fetch('/api/flights/search', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(searchParams)
      });
      if (!response.ok) {
        throw new Error('Ошибка при поиске рейсов');
      }
      const data = await response.json();
      setFlights(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    const airportCode = urlParams.get('airportCode');
    if (airportCode) {
      handleSearch({ airportCode: airportCode });
    }
  }, [urlParams]);

  return (
    <>
      <Header />
      <div class="min-vh-100">
        <SearchFlightsComponent onSearch={handleSearch} />
        {loading && <p>Загрузка...</p>}
        {error && <p style={{ color: 'red' }}>Ошибка: {error}</p>}
        {flights && <FlightResultsTwoColumnsComponent flightsData={flights} />}
      </div>
      <Footer />
    </>
  );
};

export default Search;
