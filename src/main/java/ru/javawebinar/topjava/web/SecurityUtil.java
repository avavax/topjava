package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {

    private static int CURRENT_USER = 1;

    public static int authUserId() {
        return CURRENT_USER;
    }

    public static void setAuthUserId(int userId) {
        CURRENT_USER = userId;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}