package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEALS = "/meals.jsp";
    private MealDAO dao;

    public MealServlet() {
        super();
        dao = new MealDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<MealTo> meals;
        String forward;
        String action = request.getParameter("action");

        if (action == null) {
            forward = LIST_MEALS;
            meals = MealsUtil.filteredByStreams(dao.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_TEST);
            request.setAttribute("meals", meals);
        } else if (action.equalsIgnoreCase("delete")) {
            forward = LIST_MEALS;
            String mealId = request.getParameter("mealId");
            dao.delete(mealId);
            meals = MealsUtil.filteredByStreams(dao.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_TEST);
            request.setAttribute("meals", meals);
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            String mealId = request.getParameter("mealId");
            MealTo meal = MealsUtil.createTo(dao.getById(mealId), true);
            request.setAttribute("meal", meal);
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("mealId");

        if (uuid == null || uuid.isEmpty()) {
            Meal meal = new Meal();
            parseRequestToMeal(request, meal);
            dao.save(meal);
        } else {
            Meal meal = dao.getById(uuid);
            parseRequestToMeal(request, meal);
            dao.update(meal);
        }

        List<MealTo> meals = MealsUtil.filteredByStreams(dao.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_TEST);
        request.setAttribute("meals", meals);
        request.getRequestDispatcher(LIST_MEALS).forward(request, response);
    }

    private void parseRequestToMeal(HttpServletRequest request, Meal meal) {
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("datetime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        meal.setDateTime(dateTime);
        meal.setCalories(calories);
        meal.setDescription(description);
    }
}
