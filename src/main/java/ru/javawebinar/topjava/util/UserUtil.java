package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UserUtil {

    public static final List<User> USERS = Arrays.asList(
            new User(1, "admin", "email1@email.ru", "password1", Role.ROLE_ADMIN),
            new User(2, "user1", "email2@email.ru", "password2", Role.ROLE_USER),
            new User(3, "user2", "email3@email.ru", "password3", Role.ROLE_USER)
    );
}

