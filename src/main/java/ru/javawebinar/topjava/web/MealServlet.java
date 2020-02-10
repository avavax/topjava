package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.DAO;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEALS = "/meals.jsp";
    private DAO dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new MealDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            List<MealTo> meals = MealsUtil.filteredByStreams(dao.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_TEST);
            request.setAttribute("meals", meals);
            request.setAttribute("formatter", formatter);
            request.getRequestDispatcher(LIST_MEALS).forward(request, response);
            return;
        }

        String mealId = request.getParameter("mealId");
        switch (action) {
            case ("delete"):
                dao.delete(Integer.parseInt(mealId));
                response.sendRedirect("meals");
                break;
            case ("edit"):
                MealTo meal = MealsUtil.createTo(dao.getById(Integer.parseInt(mealId)), true);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
                break;
            case ("insert"):
                request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
                break;
            default:
                 response.sendRedirect("meals");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String mealId = request.getParameter("mealId");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("datetime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        if (mealId == null || mealId.isEmpty()) {
            dao.save(new Meal(0, dateTime, description, calories));
        } else {
            dao.update(new Meal(Integer.parseInt(mealId), dateTime, description, calories));
        }

        List<MealTo> meals = MealsUtil.filteredByStreams(dao.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_TEST);
        request.setAttribute("meals", meals);
        request.getRequestDispatcher(LIST_MEALS).forward(request, response);
    }

}
