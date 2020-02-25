DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, userid) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (id, userid, description, datetime, calories)
VALUES (1, 100000, 'Завтрак юзера', '2020-02-20 08:00', 500),
       (2, 100000, 'Обед юзера', '2020-02-20 12:00', 600),
       (3, 100000, 'Ужин юзера', '2020-02-20 18:00', 700),
       (4, 100001, 'Завтрак админа', '2020-02-21 08:00', 650),
       (5, 100001, 'Обед админа', '2020-02-22 12:00', 1500),
       (6, 100001, 'Ужин админа', '2020-02-23 18:00', 300);
