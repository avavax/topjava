package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface DAO {
    void clear();

    void save(Meal meal);

    Meal getById(String uuid);

    void delete(String uuid);

    void update(Meal meal);

    List<Meal> getAll();

    int size();
}
