<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Бронирование</title>
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
<div class="container my-4">
  <h2>Данные пассажиров и оплата</h2>
  <form action="processBooking" method="post">
    <h5>Личные данные</h5>
    <div class="row mb-3">
      <div class="col-md-4">
        <label for="firstName" class="form-label">Имя</label>
        <input type="text" class="form-control" id="firstName" name="firstName" required>
      </div>
      <div class="col-md-4">
        <label for="lastName" class="form-label">Фамилия</label>
        <input type="text" class="form-control" id="lastName" name="lastName" required>
      </div>
      <div class="col-md-4">
        <label for="patronymic" class="form-label">Отчество</label>
        <input type="text" class="form-control" id="patronymic" name="patronymic">
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-md-4">
        <label for="birthDate" class="form-label">Дата рождения</label>
        <input type="date" class="form-control" id="birthDate" name="birthDate" required>
      </div>
      <div class="col-md-4">
        <label for="gender" class="form-label">Пол</label>
        <select class="form-select" id="gender" name="gender" required>
          <option value="">Выберите...</option>
          <option value="M">Мужской</option>
          <option value="F">Женский</option>
        </select>
      </div>
      <div class="col-md-4">
        <label for="passport" class="form-label">Паспорт (для международных рейсов)</label>
        <input type="text" class="form-control" id="passport" name="passport">
      </div>
    </div>
    <h5>Контактная информация</h5>
    <div class="row mb-3">
      <div class="col-md-6">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" name="email" required>
      </div>
      <div class="col-md-6">
        <label for="phone" class="form-label">Телефон</label>
        <input type="text" class="form-control" id="phone" name="phone" required>
      </div>
    </div>
    <h5>Оплата</h5>
    <div class="mb-3">
      <label for="paymentMethod" class="form-label">Способ оплаты</label>
      <select class="form-select" id="paymentMethod" name="paymentMethod" required>
        <option value="card">Банковская карта</option>
        <option value="wallet">Электронный кошелек</option>
      </select>
    </div>
    <div class="mb-3 form-check">
      <input type="checkbox" class="form-check-input" id="agree" name="agree" required>
      <label class="form-check-label" for="agree">Я согласен с правилами и условиями</label>
    </div>
    <button type="submit" class="btn btn-success">Оплатить</button>
  </form>
</div>
<footer class="bg-dark text-white text-center py-3">
  <div class="container">&copy; FlyAway Sales 2025</div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
