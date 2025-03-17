<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Личный кабинет</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
<div class="container min-vh-100 my-5 pt-3">
   <h2>Личный кабинет</h2>
   <div class="card mb-4">
      <div class="card-header">
         Личная информация
      </div>
      <div class="card-body">
         <p><strong>Email:</strong> ${sessionScope.passenger.email}</p>
         <p><strong>Имя:</strong> ${sessionScope.passenger.firstName}</p>
         <p><strong>Фамилия:</strong> ${sessionScope.passenger.lastName}</p>
         <p><strong>Отчество:</strong> ${sessionScope.passenger.patronymic}</p>
         <p><strong>Телефон:</strong> ${sessionScope.passenger.phone}</p>
         <a href="editProfile" class="btn btn-sm btn-outline-primary">Редактировать профиль</a>
      </div>
   </div>

   <h3>Мои билеты</h3>
   <c:if test="${empty tickets}">
      <div class="alert alert-warning">У вас нет билетов.</div>
   </c:if>
   <c:if test="${not empty tickets}">
      <table class="table table-striped">
         <thead>
            <tr>
               <th>Номер билета</th>
               <th>Номер бронирования (PNR)</th>
               <th>Дата бронирования</th>
               <th>Общая сумма</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach var="ticket" items="${tickets}">
               <tr onclick="location.href='ticketDetails?ticketNo=${ticket.ticketNo}'" style="cursor:pointer;">
                  <td>${ticket.ticketNo}</td>
                  <td>${ticket.bookRef}</td>
                  <td>${ticket.bookDate}</td>
                  <td>${ticket.totalAmount}</td>
               </tr>
            </c:forEach>
         </tbody>
      </table>
   </c:if>
</div>
<footer class="bg-dark text-white text-center py-3">
   <div class="container">&copy; FlyAway Sales 2025</div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
