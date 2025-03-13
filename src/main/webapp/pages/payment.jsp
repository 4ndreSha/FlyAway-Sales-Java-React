<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Оплата</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5">
  <h2>Оплата</h2>
  <p>Пожалуйста, выберите способ оплаты:</p>
  <form action="processPayment" method="post">
    <div class="mb-3">
      <select class="form-select" name="paymentMethod" required>
        <option value="">Выберите способ оплаты</option>
        <option value="card">Банковская карта</option>
        <option value="paypal">PayPal</option>
        <option value="wallet">Электронный кошелек</option>
      </select>
    </div>
    <!-- Здесь могут быть динамические поля для ввода данных карты, если выбран способ "card" -->
    <button type="submit" class="btn btn-success">Оплатить</button>
  </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
