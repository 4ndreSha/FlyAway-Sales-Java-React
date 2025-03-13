<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>FlyAway Sales – Путешествуйте с комфортом</title>
  <!-- Bootstrap 5 CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .jumbotron {
      background: url('images/airplane_bg.jpg') no-repeat fixed center;
      background-size: cover;
      color: white;
      text-align: center;
      padding: 15% 10%;
    }
    .overlay {
      background: rgba(0, 0, 0, 0.6);
      padding: 50px;
      border-radius: 10px;
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

<header class="jumbotron">
  <div class="overlay">
    <h1 class="display-4">FlyAway Sales – Открой мир вместе с нами!</h1>
    <p class="lead">Мы помогаем вам находить лучшие рейсы по доступным ценам.</p>
    <hr class="my-4">
    <p>Гибкие маршруты, выгодные предложения и комфортные путешествия.</p>
    <a href="searchFlights" class="btn btn-primary btn-lg">Начать поиск рейсов</a>
    <a href="map" class="btn btn-outline-light btn-lg">Открыть карту маршрутов</a>
  </div>
</header>

<main class="container my-5">
  <h2 class="text-center">Почему выбирают FlyAway Sales?</h2>
  <div class="row text-center mt-4">
    <div class="col-md-4">
      <h4>Лучшие цены</h4>
      <p>Мы сравниваем сотни авиакомпаний и агентств, чтобы предложить вам самые выгодные билеты.</p>
    </div>
    <div class="col-md-4">
      <h4>Удобство</h4>
      <p>Гибкие параметры поиска и удобная карта маршрутов помогут вам легко спланировать поездку.</p>
    </div>
    <div class="col-md-4">
      <h4>Поддержка 24/7</h4>
      <p>Наша команда поддержки всегда готова помочь вам на каждом этапе вашего путешествия.</p>
    </div>
  </div>
</main>

<footer class="bg-dark text-white text-center py-3">
  <div class="container">&copy; FlyAway Sales 2025. Все права защищены.</div>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
