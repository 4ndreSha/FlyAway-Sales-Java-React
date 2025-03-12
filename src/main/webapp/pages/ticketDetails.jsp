<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Детали билета</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .detail-card {
      max-width: 700px;
      margin: 50px auto;
      box-shadow: 0 3px 10px rgba(0,0,0,0.1);
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
        <li class="nav-item"><a class="nav-link" href="map.jsp">Карта маршрутов</a></li>
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
<div class="container my-5 pt-1 pb-5">
  <div class="card detail-card">
    <div class="card-header text-center">
      <h4>Детали билета № <c:out value="${ticketDetails.ticketNo}" /></h4>
    </div>
    <div class="card-body">
      <p><strong>Номер бронирования (PNR):</strong> <c:out value="${ticketDetails.bookRef}" /></p>
      <p><strong>Дата бронирования:</strong> <c:out value="${ticketDetails.bookDate}" /></p>
      <p><strong>Общая сумма:</strong> <c:out value="${ticketDetails.totalAmount}" /> руб.</p>
      <hr/>
      <p><strong>Авиарейс:</strong> <c:out value="${ticketDetails.flightNo}" /></p>
      <p><strong>Время вылета:</strong> <c:out value="${ticketDetails.scheduledDeparture}" /></p>
      <p><strong>Время прилета:</strong> <c:out value="${ticketDetails.scheduledArrival}" /></p>
      <p><strong>Аэропорт отправления:</strong> <c:out value="${ticketDetails.departureAirport}" /></p>
      <p><strong>Аэропорт прибытия:</strong> <c:out value="${ticketDetails.arrivalAirport}" /></p>
      <p><strong>Статус рейса:</strong> <c:out value="${ticketDetails.status}" /></p>
      <p><strong>Тип тарифа:</strong> <c:out value="${ticketDetails.fareConditions}" /></p>
      <p><strong>Стоимость тарифа:</strong> <c:out value="${ticketDetails.amount}" /> руб.</p>
      <p><strong>Тип самолёта:</strong> <c:out value="${ticketDetails.aircraftCode}" /></p>
      <p><strong>Фактический вылет:</strong> <c:out value="${ticketDetails.actualDeparture}" /></p>
      <p><strong>Фактический прилет:</strong> <c:out value="${ticketDetails.actualArrival}" /></p>
    </div>
  </div>
  <div class="text-center mt-3">
    <a href="account" class="btn btn-secondary">Вернуться в личный кабинет</a>
  </div>
</div>
<footer class="bg-dark text-white text-center py-3 fixed-bottom">
  <div class="container">&copy; FlyAway Sales 2025</div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
