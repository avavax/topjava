package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID_1 = 1;
    public static final int MEAL_ID_2 = 2;
    public static final int MEAL_ID_3 = 3;
    public static final int MEAL_ID_4 = 4;
    public static final int MEAL_ID_5 = 5;
    public static final int MEAL_ID_6 = 6;

    public static final Meal MEAL_1 = new Meal(MEAL_ID_1, LocalDateTime.parse("2020-02-20T08:00:00"), "Завтрак юзера", 500);
    public static final Meal MEAL_2 = new Meal(MEAL_ID_2, LocalDateTime.parse("2020-02-20T12:00:00"), "Обед юзера", 600);
    public static final Meal MEAL_3 = new Meal(MEAL_ID_3, LocalDateTime.parse("2020-02-20T18:00:00"), "Ужин юзера", 700);
    public static final Meal MEAL_4 = new Meal(MEAL_ID_4, LocalDateTime.parse("2020-02-21T08:00:00"), "Завтрак админа", 650);
    public static final Meal MEAL_5 = new Meal(MEAL_ID_5, LocalDateTime.parse("2020-02-22T12:00:00"), "Обед админа", 1500);
    public static final Meal MEAL_6 = new Meal(MEAL_ID_6, LocalDateTime.parse("2020-02-23T18:00:00"), "Ужин админа", 300);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.parse("2020-02-20T10:30:00"), "Второй завтрак", 800);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL_ID_1, LocalDateTime.parse("2020-02-20T08:00:00"), "Завтрак юзера", 500);
        updated.setDateTime(LocalDateTime.parse("2020-02-20T09:00:00"));
        updated.setDescription("Перемена блюд");
        updated.setCalories(1200);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
