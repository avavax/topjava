package ru.javawebinar.topjava;

import ru.javawebinar.topjava.dao.MealDAO;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo application</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello TopJava Enterprise!");
        MealDAO meals = new MealDAO();
        //System.out.println(meals.getById(1).getDescription());
    }
}
