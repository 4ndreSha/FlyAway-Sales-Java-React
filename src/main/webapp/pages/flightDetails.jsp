<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Детали рейса</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
<div class="container my-4 vh-100 pt-5">
  <c:if test="${not empty flight}">
    <h2>Рейс: ${flight.flightNo}</h2>
    <div class="row">
      <div class="col-md-6">
        <h5>Расписание</h5>
        <p><strong>Вылет:</strong> ${flight.scheduledDeparture} (местное: ${flight.scheduledDepartureLocal})</p>
        <p><strong>Прилет:</strong> ${flight.scheduledArrival} (местное: ${flight.scheduledArrivalLocal})</p>
        <p><strong>Длительность:</strong> ${flight.scheduledDuration}</p>
        <p><strong>Аэропорты:</strong> ${flight.departureAirportName} - ${flight.arrivalAirportName}</p>
        <p><strong>Авиакомпания:</strong> ${flight.airline}</p>
      </div>
      <div class="col-md-6">
        <h5>Дополнительные услуги</h5>
        <form action="purchaseTicket" method="post">
        <input type="hidden" name="flightId" value="${flight.flightId}">
        <input type="hidden" name="amount" id="totalAmount" value="${flight.price}">
          <!-- Опции выбора места -->
          <div class="mb-3">
            <label for="seatOption" class="form-label">Выбор места:</label>
            <select class="form-select" id="seatOption" name="fareConditions">
                <option value="Economy" data-price="0">Эконом</option>
                <option value="Comfort" data-price="500">Комфорт (+500 руб.)</option>
                <option value="Business" data-price="1000">Бизнес (+1000 руб.)</option>
            </select>
          </div>
          <!-- Дополнительный багаж -->
          <div class="mb-3">
            <label for="extraBaggage" class="form-label">Дополнительный багаж (кг):</label>
            <input type="number" class="form-control" id="extraBaggage" name="extraBaggage" min="0" value="0">
          </div>
          <!-- Страховка -->
          <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" id="insurance" name="insurance">
            <label class="form-check-label" for="insurance">
              Страховка (+300 руб.)
            </label>
          </div>
          <!-- Питание -->
          <div class="mb-3">
              <label for="meal" class="form-label">Питание:</label>
              <select class="form-select" id="meal" name="meal">
                  <option value="standard" data-price="0">Стандартное (включено)</option>
                  <option value="special" data-price="200">Специальное меню (+200 руб.)</option>
              </select>
          </div>
          <div class="mb-3">
            <h4>Итоговая стоимость: <span id="totalPrice">${flight.price}</span> руб.</h4>
          </div>
          <button type="submit" class="btn btn-primary">Перейти к бронированию</button>
        </form>
      </div>
    </div>
  </c:if>
  <c:if test="${empty flight}">
    <div class="alert alert-warning">Рейс не найден.</div>
  </c:if>
</div>
<footer class="bg-dark text-white text-center py-3">
  <div class="container">&copy; FlyAway Sales 2025</div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function() {
    // Базовая стоимость
    const BASE_PRICE = 4700;

    // Элементы управления
    const seatSelect = document.getElementById('seatOption');
    const baggageInput = document.getElementById('extraBaggage');
    const insuranceCheckbox = document.getElementById('insurance');
    const mealSelect = document.getElementById('meal');
    const totalPriceElement = document.getElementById('totalPrice');

    // Функция расчета стоимости
    function calculateTotal() {
        let total = BASE_PRICE;

        // Доплата за место
        total += parseInt(seatSelect.options[seatSelect.selectedIndex].dataset.price) || 0;

        // Доплата за багаж (свыше 10 кг)
        const baggage = Math.max(0, parseInt(baggageInput.value) - 10) * 100;
        total += baggage;

        // Страховка
        if (insuranceCheckbox.checked) {
            total += 300;
        }

        // Питание
        total += parseInt(mealSelect.options[mealSelect.selectedIndex].dataset.price) || 0;

        // Обновление отображения
        document.getElementById('totalAmount').value = total;
        totalPriceElement.textContent = total.toLocaleString('ru-RU');
    }

    // Назначение обработчиков событий
    [seatSelect, baggageInput, insuranceCheckbox, mealSelect].forEach(element => {
        element.addEventListener('change', calculateTotal);
    });
    baggageInput.addEventListener('input', calculateTotal);

    // Первоначальный расчет
    calculateTotal();
});
</script>
</body>
</html>
