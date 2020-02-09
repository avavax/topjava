<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<html>
<head>
    <title>Моя еда</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h3>Редактировать еду</h3>
        <form method="POST" action="meals">
            <input type="hidden" name="mealId" value="<c:out value="${meal.getUuid()}" />">
            <div class="form-group">
                <label for="datetime">Дата/время</label>
                <input type="datetime-local" class="form-control" id="datetime" name="datetime" required
                       value="<c:out value="${meal.getDateTime()}" />">
            </div>
            <div class="form-group">
                <label for="calories">Калории</label>
                <input type="number" class="form-control" id="calories" name="calories" required
                       value="<c:out value="${meal.getCalories()}" />">
            </div>
            <div class="form-group">
                <label for="description">Описание</label>
                <input type="text" class="form-control" id="description" name="description" required
                       value="<c:out value="${meal.getDescription()}" />">
            </div>
            <button type="submit" class="btn btn-primary mb-2">Сохранить</button>
         </form>
    </div>
</body>
</html>