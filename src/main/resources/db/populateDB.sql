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

INSERT INTO restaurants (date, name, address) VALUES
  ('2015-05-30', 'Континенталь', 'Декабристов 14' ),
  ('2015-05-30', 'Астория', 'Первого восстания 78'),
  ('2015-05-31', 'Астория', 'Первого восстания 78');


INSERT INTO meals (name, price, restaurants_id) VALUES
  ('Сок', 33, 100002),
  ('Суп Харчо', 62, 100002),
  ('Пельмени', 62, 100002),
  ('Жаркое', 78, 100003),
  ('Сок', 33, 100003),
  ('Суп Харчо', 62, 100003),
  ('Пельмени', 62, 100003),
  ('Сок', 33, 100004),
  ('Суп Харчо', 62, 100004),
  ('Пельмени', 62, 100004);

