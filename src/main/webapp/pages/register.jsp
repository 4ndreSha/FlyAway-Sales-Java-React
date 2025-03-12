<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h2>Регистрация</h2>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <form action="register" method="post">
        <div class="mb-3">
            <label for="email" class="form-label">Электронная почта</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Пароль</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="mb-3">
            <label for="firstName" class="form-label">Имя</label>
            <input type="text" class="form-control" id="firstName" name="firstName" required>
        </div>
        <div class="mb-3">
            <label for="lastName" class="form-label">Фамилия</label>
            <input type="text" class="form-control" id="lastName" name="lastName" required>
        </div>
        <div class="mb-3">
            <label for="patronymic" class="form-label">Отчество</label>
            <input type="text" class="form-control" id="patronymic" name="patronymic">
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Номер телефона</label>
            <input type="text" class="form-control" id="phone" name="phone">
        </div>
        <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
    </form>
    <p class="mt-3">Уже зарегистрированы? <a href="login">Войти</a></p>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
