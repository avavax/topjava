package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MealDAO implements DAO {

    protected ConcurrentMap<Integer, Meal> meals;
    protected AtomicInteger maxUuid = new AtomicInteger();

    public MealDAO() {
        meals = MealsUtil.createMealTestData().stream()
                .collect(Collectors.toConcurrentMap(
                        Meal::getUuid,
                        Function.identity()));
        maxUuid.set(meals.size());
    }

    @Override
    public void save(Meal meal) {
        int uuid = maxUuid.incrementAndGet();
        meals.put(uuid, new Meal(uuid, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
    }

    @Override
    public Meal getById(int uuid) {
        return meals.get(uuid);
    }

    @Override
    public void delete(int uuid) {
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
}
