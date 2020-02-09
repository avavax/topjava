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
        <h3><a href="index.html">На главную</a></h3>
        <hr>
        <h2>Моя еда</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Дата/время</th>
                <th scope="col">Описание</th>
                <th scope="col">Калории</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <c:forEach var="meal" items="${meals}">
                <tr style="color: <c:out value="${meal.isExcess() ? 'red' : 'green'}" />">
                    <td>${TimeUtil.formatDateTime(meal.getDateTime())}</td>
                    <td>${meal.getDescription()}</td>
                    <td>${meal.getCalories()}</td>
                    <td><a href="meals?action=delete&mealId=<c:out value="${meal.getUuid()}"/>">Удалить</a></td>
                    <td><a href="meals?action=edit&mealId=<c:out value="${meal.getUuid()}"/>">Править</a></td>
                </tr>
            </c:forEach>
        </table>
        <a href="meals?action=insert" class="btn btn-primary mb-2">Добавить еду</a>
    </div>
</body>
</html>

