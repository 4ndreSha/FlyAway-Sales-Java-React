import React from 'react';
import { Link } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';
import 'bootstrap/dist/css/bootstrap.min.css';

const Home = () => {
  return (
    <>
      <Header />
      
      <main className="container-fluid p-0 vh-100">
        {/* Hero Section */}
        <section className="bg-primary text-white py-5">
          <div className="container text-center py-5">
            <h1 className="display-4 mb-4">
              FlyAway Sales - Ваш надёжный партнёр в мире авиапутешествий
            </h1>
            <p className="lead mb-4">
              Бронируйте авиабилеты по лучшим ценам и наслаждайтесь комфортом перелётов
            </p>
            <div className="d-flex gap-3 justify-content-center">
              <Link to="/search" className="btn btn-light btn-lg px-4">
                Поиск рейсов
              </Link>
              <Link to="/airports" className="btn btn-outline-light btn-lg px-4">
                Карта аэропортов
              </Link>
            </div>
          </div>
        </section>

        {/* Features Section */}
        <section className="py-5">
          <div className="container">
            <div className="row g-4">
              <div className="col-md-6">
                <h2 className="mb-4">Почему выбирают нас?</h2>
                <div className="card border-0 shadow-sm h-100">
                  <div className="card-body">
                    <h5 className="card-title">Наши преимущества</h5>
                    <ul className="list-group list-group-flush">
                      <li className="list-group-item">✅ Лучшие цены на рынке</li>
                      <li className="list-group-item">✅ Мгновенное подтверждение</li>
                      <li className="list-group-item">✅ 24/7 Поддержка клиентов</li>
                      <li className="list-group-item">✅ Безопасные платежи</li>
                    </ul>
                  </div>
                </div>
              </div>
              
              <div className="col-md-6">
                <h2 className="mb-4">О компании</h2>
                <div className="card border-0 shadow-sm h-100">
                  <div className="card-body">
                    <p className="card-text">
                      FlyAway Sales - ведущий сервис по бронированию авиабилетов, 
                      предоставляющий услуги более чем в 100 странах мира. 
                      Наша миссия - сделать воздушные путешествия доступными, 
                      удобными и приятными для каждого пассажира.
                    </p>
                    <p className="card-text">
                      С 2010 года мы помогаем миллионам путешественников 
                      реализовывать их мечты о новых горизонтах.
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </main>

      <Footer />
    </>
  );
};

export default Home;