<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Карта аэропортов</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Leaflet CSS -->
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" />
    <style>
        body {
            background-color: #f8f9fa;
            color: #212529;
        }

        #map {
            height: 70vh;
            margin: 20px auto;
            border: 2px solid #dee2e6;
            border-radius: 8px;
            background: white;
        }

        .leaflet-popup-content-wrapper {
            background: #fff !important;
            color: #212529 !important;
            border: 1px solid #0d6efd;
            border-radius: 5px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1);
        }

        .leaflet-popup-tip {
            background: #fff !important;
            border: 1px solid #0d6efd !important;
        }

        .custom-btn {
            background-color: #0d6efd !important;
            border: none;
            width: 100%;
            margin-top: 10px;
            color: white !important;
        }

        .airport-title {
            color: #0d6efd;
            font-weight: 600;
        }

        .custom-popup .leaflet-popup-content-wrapper {
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.2);
        }

        .custom-popup .leaflet-popup-content {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container d-flex justify-content-between">
        <a class="navbar-brand" href="index.jsp">FlyAway Sales</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav me-auto">
            <li class="nav-item"><a class="nav-link" href="index.jsp">Главная</a></li>
            <li class="nav-item"><a class="nav-link" href="searchFlights">Поиск рейсов</a></li>
            <li class="nav-item"><a class="nav-link" href="map.jsp">Карта маршрутов</a></li>
            <li class="nav-item"><a class="nav-link" href="account">Личный кабинет</a></li>
          </ul>
          <ul class="navbar-nav me-auto">
            <li class="nav-item"><a class="nav-link" href="login">Вход</a></li>
            <li class="nav-item"><a class="nav-link" href="register">Регистрация</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container-fluid vh-100" style="background: url('images/map_bg.jpg') no-repeat fixed center; background-size: cover; padding-top: 64px;">
        <h1 class="text-center mb-4 text-primary">Аэропорты России</h1>

        <div class="row justify-content-center">
            <div class="col-md-10">
                <div id="map"></div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS + Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Leaflet JS -->
    <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>

    <script>
        // Инициализация карты
        const map = L.map('map').setView([55.7558, 37.6173], 4);

        // Светлая тема карты
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '© OpenStreetMap contributors'
        }).addTo(map);

        // Загрузка данных
        fetch('<%= request.getContextPath() %>/cities')
                .then(response => response.json())
                .then(cities => {
                    cities.forEach(city => {
                        // Экранируем JSP-синтаксис с помощью обратного слеша
                        const popupContent = `
                            <div class="p-2">
                                <div class="airport-title mb-2">\${escapeHtml(city.airportName)}</div>
                                <div class="d-flex align-items-center text-muted">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#0d6efd" class="bi bi-geo-alt me-2" viewBox="0 0 16 16">
                                        <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A32 32 0 0 1 8 14.58a32 32 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10"/>
                                        <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4m0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                                    </svg>
                                    <div>\${escapeHtml(city.cityName)}</div>
                                </div>
                                <a href="\${getFlightsUrl(city.airportName)}"
                                   class="btn btn-sm custom-btn mt-2">
                                    Просмотреть рейсы
                                </a>
                            </div>
                        `;

                        // Создаем popup
                        const popup = L.popup().setContent(popupContent);

                        // Создаем маркер
                        L.marker([city.latitude, city.longitude], {
                            /* иконка */
                        }).bindPopup(popup).addTo(map);
                    });
                });

            // Функции escapeHtml и getFlightsUrl должны быть объявлены ДО их использования
            const escapeHtml = (str) => {
                const div = document.createElement('div');
                div.textContent = str;
                return div.innerHTML;
            };

            const getFlightsUrl = (airport) => {
                const base = '<%= request.getContextPath() %>/searchFlights';
                return `searchFlights?airport=\${encodeURIComponent(airport)}&depTimeSort=time_asc&depStatus=All&arrTimeSort=time_asc&arrStatus=All`;
            };
    </script>
    <footer class="bg-dark text-white text-center py-3">
      <div class="container">&copy; FlyAway Sales 2025</div>
    </footer>
</body>
</html>