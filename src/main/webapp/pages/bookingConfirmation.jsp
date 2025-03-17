<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Подтверждение бронирования</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f8f9fa;
    }
    .confirmation-card {
      max-width: 500px;
      margin: 100px auto 50px;
      padding: 30px;
      border: 1px solid #dee2e6;
      border-radius: 8px;
      background-color: #fff;
      box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
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
        <li class="nav-item"><a class="nav-link" href="map">Карта маршрутов</a></li>
        <li class="nav-item"><a class="nav-link" href="account">Личный кабинет</a></li>
      </ul>
      <ul class="navbar-nav me-auto">
        <li class="nav-item"><a class="nav-link" href="login">Вход</a></li>
        <li class="nav-item"><a class="nav-link" href="register">Регистрация</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container">
  <div class="confirmation-card text-center">
    <h2 class="text-success mb-3">Бронирование успешно оформлено!</h2>
    <p class="lead">Спасибо за ваш заказ.</p>
    <div class="mt-4">
      <a href="index.jsp" class="btn btn-primary me-2">На главную</a>
      <a href="account" class="btn btn-outline-secondary">Личный кабинет</a>
    </div>
  </div>
</div>
<footer class="bg-dark text-white text-center py-3 fixed-bottom">
  <div class="container">&copy; FlyAway Sales 2025</div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
