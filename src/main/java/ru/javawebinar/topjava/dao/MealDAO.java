package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MealDAO implements DAO {

    protected ConcurrentMap<String, Meal> meals;

    public MealDAO() {
        meals = MealsUtil.createMealTestData().stream()
                .collect(Collectors.toConcurrentMap(
                        Meal::getUuid,
                        Function.identity()));
    }

    @Override
    public void clear() {
        meals.clear();
    }

    @Override
    public void save(Meal meal) {
        meals.put(meal.getUuid(), meal);
    }

    @Override
    public Meal getById(String uuid) {
        return meals.get(uuid);
    }

    @Override
    public void delete(String uuid) {
        meals.remove(uuid);
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getUuid(), meal);
    }

    @Override
    public List<Meal> getAll() {
        List<Meal> list = new ArrayList<>(meals.values());
        list.sort(Comparator.comparing(Meal::getDateTime));
        return list;
    }

    @Override
    public int size() {
        return meals.size();
    }
}
