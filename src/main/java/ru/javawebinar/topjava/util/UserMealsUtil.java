package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.TimeUtil.isBetweenInclusive;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2019, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(21, 0), 1600);
        mealsTo.forEach(System.out::println);

        System.out.println("");

        List<UserMealWithExcess> mealsToStream = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(21, 0), 2000);
        mealsToStream.forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> excess = new HashMap<>();
        for (UserMeal meal : meals) {
            LocalDate day = meal.getDateTime().toLocalDate();
            excess.merge(day, meal.getCalories(), Integer::sum);
        }

        List<UserMealWithExcess> result = new ArrayList<>();
        for (UserMeal meal : meals) {
            LocalTime lt = meal.getDateTime().toLocalTime();
            if (isBetweenInclusive(lt, startTime, endTime)) {
                result.add(new UserMealWithExcess(
                        meal.getDateTime(),
                        meal.getDescription(),
                        meal.getCalories(),
                        excess.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        return result;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> excess = meals.stream()
                .collect(Collectors.toMap(
                        userMeal -> userMeal.getDateTime().toLocalDate(),
                        UserMeal::getCalories,
                        Integer::sum
                ));
        return meals.stream()
                .filter(item -> isBetweenInclusive(item.getDateTime().toLocalTime(), startTime, endTime))
                .map(item -> new UserMealWithExcess(
                        item.getDateTime(),
                        item.getDescription(),
                        item.getCalories(),
                        excess.get(item.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
