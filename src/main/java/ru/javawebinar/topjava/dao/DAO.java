package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface DAO {

    void save(Meal meal);

    Meal getById(int uuid);

    void delete(int uuid);

    void update(Meal meal);

    List<Meal> getAll();
}
