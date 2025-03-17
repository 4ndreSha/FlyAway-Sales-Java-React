<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Результаты поиска рейсов</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* Карточка рейса с адаптивной высотой */
    .flight-card {
      padding: 10px;
      border: 1px solid #dee2e6;
      border-radius: 5px;
      margin-bottom: 10px;
      background-color: #f8f9fa;
    }
    @media (max-width: 560px) {
      .flight-card {
        height: 152px;
        overflow: hidden;
      }
    }
  </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  <div class="container">
    <a class="navbar-brand" href="index.jsp">FlyAway Sales</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto">
        <li class="nav-item"><a class="nav-link" href="index.jsp">Главная</a></li>
        <li class="nav-item"><a class="nav-link" href="searchFlights">Поиск рейсов</a></li>
        <li class="nav-item"><a class="nav-link" href="map">Карта маршрутов</a></li>
        <li class="nav-item"><a class="nav-link" href="account">Личный кабинет</a></li>
      </ul>
      <ul class="navbar-nav">
        <c:choose>
          <c:when test="${not empty sessionScope.passenger}">
            <li class="nav-item"><a class="nav-link" href="logout">Выход</a></li>
          </c:when>
          <c:otherwise>
            <li class="nav-item"><a class="nav-link" href="login">Вход</a></li>
            <li class="nav-item"><a class="nav-link" href="register">Регистрация</a></li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>
<div class="container my-4 pt-5">
  <h2 class="mb-4">Рейсы для аэропорта: <c:out value="${airport}" /></h2>
  <div class="row">
    <!-- Колонка Отправление -->
    <div class="col-md-6">
      <h4>Отправление</h4>
      <div class="d-flex flex-column mb-2">
        <div class="py-2">
          Сортировать по времени:
          <a href="searchFlights?airport=${airport}&depTimeSort=time_asc&depStatus=${depStatus}&arrTimeSort=${arrTimeSort}&arrStatus=${arrStatus}" class="btn btn-sm btn-outline-primary">Возрастанию</a>
          <a href="searchFlights?airport=${airport}&depTimeSort=time_desc&depStatus=${depStatus}&arrTimeSort=${arrTimeSort}&arrStatus=${arrStatus}" class="btn btn-sm btn-outline-primary">Убыванию</a>
        </div>
        <div class="d-flex flex-row">
          <div style="padding-right: 5px">Фильтр по статусу:</div>
          <select id="depStatusSelect" class="form-select form-select-sm w-25">
            <option value="All" ${depStatus eq 'All' ? 'selected' : ''}>All</option>
            <option value="Scheduled" ${depStatus eq 'Scheduled' ? 'selected' : ''}>Scheduled</option>
            <option value="On Time" ${depStatus eq 'On Time' ? 'selected' : ''}>On Time</option>
            <option value="Delayed" ${depStatus eq 'Delayed' ? 'selected' : ''}>Delayed</option>
            <option value="Departed" ${depStatus eq 'Departed' ? 'selected' : ''}>Departed</option>
            <option value="Arrived" ${depStatus eq 'Arrived' ? 'selected' : ''}>Arrived</option>
            <option value="Cancelled" ${depStatus eq 'Cancelled' ? 'selected' : ''}>Cancelled</option>
          </select>
        </div>
      </div>
      <c:if test="${empty depFlights}">
        <div class="alert alert-warning">Рейсы по отправлению не найдены.</div>
      </c:if>
      <c:if test="${not empty depFlights}">
        <div class="list-group">
          <c:forEach var="flight" items="${depFlights}">
            <a href="flightDetails?flightId=${flight.flightId}" class="list-group-item list-group-item-action flight-card">
              <div class="d-flex justify-content-between">
                <h5 class="mb-1">${flight.flightNo}</h5>
                <small>${flight.status}</small>
              </div>
              <p class="mb-1">
                <strong>Отправление:</strong> ${flight.departureAirportName} (${flight.departureCity})
                <br/>
                <strong>Прибытие:</strong> ${flight.arrivalAirportName} (${flight.arrivalCity})
              </p>
              <p class="mb-0">Время вылета: ${flight.scheduledDeparture}</p>
            </a>
          </c:forEach>
        </div>
      </c:if>
    </div>
    <!-- Колонка Прибытие -->
    <div class="col-md-6">
      <h4>Прибытие</h4>
      <div class="d-flex flex-column mb-2">
        <div class="py-2">
          Сортировать по времени:
          <a href="searchFlights?airport=${airport}&arrTimeSort=time_asc&arrStatus=${arrStatus}&depTimeSort=${depTimeSort}&depStatus=${depStatus}" class="btn btn-sm btn-outline-primary">Возрастанию</a>
          <a href="searchFlights?airport=${airport}&arrTimeSort=time_desc&arrStatus=${arrStatus}&depTimeSort=${depTimeSort}&depStatus=${depStatus}" class="btn btn-sm btn-outline-primary">Убыванию</a>
        </div>
        <div class="d-flex flex-row">
          <div style="padding-right: 5px">Фильтр по статусу:</div>
          <select id="arrStatusSelect" class="form-select form-select-sm w-25 pl-2">
            <option value="All" ${arrStatus eq 'All' ? 'selected' : ''}>All</option>
            <option value="Scheduled" ${arrStatus eq 'Scheduled' ? 'selected' : ''}>Scheduled</option>
            <option value="On Time" ${arrStatus eq 'On Time' ? 'selected' : ''}>On Time</option>
            <option value="Delayed" ${arrStatus eq 'Delayed' ? 'selected' : ''}>Delayed</option>
            <option value="Departed" ${arrStatus eq 'Departed' ? 'selected' : ''}>Departed</option>
            <option value="Arrived" ${arrStatus eq 'Arrived' ? 'selected' : ''}>Arrived</option>
            <option value="Cancelled" ${arrStatus eq 'Cancelled' ? 'selected' : ''}>Cancelled</option>
          </select>
        </div>
      </div>
      <c:if test="${empty arrFlights}">
        <div class="alert alert-warning">Рейсы по прибытии не найдены.</div>
      </c:if>
      <c:if test="${not empty arrFlights}">
        <div class="list-group">
          <c:forEach var="flight" items="${arrFlights}">
            <a href="flightDetails?flightId=${flight.flightId}" class="list-group-item list-group-item-action flight-card">
              <div class="d-flex justify-content-between">
                <h5 class="mb-1">${flight.flightNo}</h5>
                <small>${flight.status}</small>
              </div>
              <p class="mb-1">
                <strong>Отправление:</strong> ${flight.departureAirportName} (${flight.departureCity})
                <br/>
                <strong>Прибытие:</strong> ${flight.arrivalAirportName} (${flight.arrivalCity})
              </p>
              <p class="mb-0">Время прибытия: ${flight.scheduledArrival}</p>
            </a>
          </c:forEach>
        </div>
      </c:if>
    </div>
  </div>
</div>
<footer class="bg-dark text-white text-center py-3">
  <div class="container">&copy; FlyAway Sales 2025</div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Определяем значения по умолчанию из JSP переменных
    var defaultAirport = "${airport}";
    var defaultDepTimeSort = "${depTimeSort}" || "time_asc";
    var defaultDepStatus = "${depStatus}" || "All";
    var defaultArrTimeSort = "${arrTimeSort}" || "time_asc";
    var defaultArrStatus = "${arrStatus}" || "All";

    function updateDepStatus() {
        var newStatus = document.getElementById("depStatusSelect").value;
        var url = new URL(window.location.href);
        url.searchParams.set("airport", defaultAirport);
        url.searchParams.set("depTimeSort", defaultDepTimeSort);
        url.searchParams.set("depStatus", newStatus);
        url.searchParams.set("arrTimeSort", defaultArrTimeSort);
        url.searchParams.set("arrStatus", defaultArrStatus);
        window.location.href = url.toString();
    }
    function updateArrStatus() {
        var newStatus = document.getElementById("arrStatusSelect").value;
        var url = new URL(window.location.href);
        url.searchParams.set("airport", defaultAirport);
        url.searchParams.set("depTimeSort", defaultDepTimeSort);
        url.searchParams.set("depStatus", defaultDepStatus);
        url.searchParams.set("arrTimeSort", defaultArrTimeSort);
        url.searchParams.set("arrStatus", newStatus);
        window.location.href = url.toString();
    }
    document.getElementById("depStatusSelect").addEventListener("change", updateDepStatus);
    document.getElementById("arrStatusSelect").addEventListener("change", updateArrStatus);
</script>
</body>
</html>
