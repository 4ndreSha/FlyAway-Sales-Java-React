<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Поиск рейсов</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery UI для автодополнения -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
</head>
<body style="background: url('images/search_bg.jpg') no-repeat fixed center; background-size: cover;">
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
<main style="height: 75vh">
    <div class="container py-4" style="background-color: white; border-radius: 10px; margin-top: 18%">
        <h2 class="mb-4">Поиск рейсов</h2>
        <form id="searchForm" action="searchFlights" method="post">
            <!-- Поле для ввода города -->
            <div class="mb-3">
                <label for="city" class="form-label">Город</label>
                <input type="text" class="form-control" id="city" name="city" placeholder="Введите город" required>
            </div>
            <!-- Выпадающее меню аэропортов, которое будет заполнено автоматически -->
            <div class="mb-3">
                <label for="airport" class="form-label">Аэропорт</label>
                <select class="form-select" id="airport" name="airport">
                    <option value="">Выберите аэропорт</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Найти рейсы</button>
        </form>
    </div>
</main>
<footer style="max-height:80px; height: 25vh" class="bg-dark text-white text-center py-3">
    <div class="container">&copy; FlyAway Sales 2025</div>
</footer>
<!-- Подключение jQuery и jQuery UI -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>
<script>
    // Массив объектов с информацией об аэропортах и соответствующих городах
    var airportData = [
        { airport: "Якутск", city: "Якутск" },
        { airport: "Мирный", city: "Мирный" },
        { airport: "Хабаровск-Новый", city: "Хабаровск" },
        { airport: "Елизово", city: "Петропавловск-Камчатский" },
        { airport: "Хомутово", city: "Иркутск" },
        { airport: "Владивосток", city: "Владивосток" },
        { airport: "Пулково", city: "Санкт-Петербург" },
        { airport: "Храброво", city: "Калининград" },
        { airport: "Кемерово", city: "Кемерово" },
        { airport: "Челябинск", city: "Челябинск" },
        { airport: "Магнитогорск", city: "Магнитогорск" },
        { airport: "Пермь", city: "Пермь" },
        { airport: "Сургут", city: "Сургут" },
        { airport: "Брянск", city: "Брянск" },
        { airport: "Минеральные Воды", city: "Минеральные Воды" },
        { airport: "Ставрополь", city: "Ставрополь" },
        { airport: "Астрахань", city: "Астрахань" },
        { airport: "Нижневартовск", city: "Нижневартовск" },
        { airport: "Кольцово", city: "Екатеринбург" },
        { airport: "Шереметьево", city: "Москва" },
        { airport: "Воронеж", city: "Воронеж" },
        { airport: "Внуково", city: "Москва" },
        { airport: "Сыктывкар", city: "Сыктывкар" },
        { airport: "Курумоч", city: "Самара" },
        { airport: "Домодедово", city: "Москва" },
        { airport: "Рощино", city: "Тюмень" },
        { airport: "Стригино", city: "Нижний Новгород" },
        { airport: "Богашёво", city: "Томск" },
        { airport: "Усть-Илимск", city: "Усть-Илимск" },
        { airport: "Норильск", city: "Норильск" }
    ];

    // Извлекаем уникальные города для автодополнения
    var cities = [...new Set(airportData.map(item => item.city))];

    // Настройка автодополнения для поля "city"
    $("#city").autocomplete({
        source: cities,
        minLength: 1,
        select: function(event, ui) {
            var selectedCity = ui.item.value;
            var options = '<option value="">Выберите аэропорт</option>';
            // Фильтруем аэропорты, соответствующие выбранному городу
            airportData.forEach(function(item) {
                if (item.city === selectedCity) {
                    options += '<option value="' + item.airport + '">' + item.airport + '</option>';
                }
            });
            $("#airport").html(options);
        }
    });

    // Если поле "city" очищается, сбросить список аэропортов
    $("#city").on("input", function() {
        if ($(this).val() === "") {
            $("#airport").html('<option value="">Выберите аэропорт</option>');
        }
    });

    // При сабмите формы, если аэропорт не выбран, выдаем сообщение и предотвращаем сабмит
    $("#searchForm").on("submit", function(e) {
        var airportVal = $("#airport").val();
        if (!airportVal) {
            e.preventDefault();
            alert("Пожалуйста, выберите аэропорт из списка после выбора города.");
        }
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
