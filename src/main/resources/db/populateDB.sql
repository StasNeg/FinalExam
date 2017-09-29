DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;


INSERT INTO users (name, email, password)
VALUES  ('User', 'user@yandex.ru', 'password'),
        ('Admin', 'admin@gmail.com', 'password');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (date, name, price) VALUES
  ('2015-05-30', 'континенталь', 280),
  ('2015-05-30', 'континенталь1', 230),
  ('2015-05-30', 'континенталь2', 180),
  ('2015-05-30', 'континенталь3', 100);

INSERT INTO meals (name, price, restaurants_id) VALUES
  ('Сок', 100, 100002),
  ('Сок2', 80, 100002),
  ('Сок3 im miakot', 100, 100002),
  ('Сок', 100, 100003),
  ('Сок', 100, 100003),
  ('Сок', 30, 100004),
  ('Сок', 30, 100005),
  ('Сок', 30, 100005),
  ('Сок', 30, 100005);