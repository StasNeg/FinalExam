DELETE FROM votes;
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
  ('ROLE_ADMIN', 100001);


INSERT INTO restaurants (name, address) VALUES
  ('Континенталь', 'Декабристов 14' ),
  ('Астория', 'Первого восстания 78');


INSERT INTO menu (date,restaurants_id) VALUES
  ('2015-05-30', 100002 ),
  ('2015-05-30', 100003),
  ('2015-05-31', 100002),
  ('2015-05-31', 100003);


INSERT INTO meals (name, price, menu_id) VALUES
  ('Сок', 33, 100004),
  ('Суп Харчо', 62, 100004),
  ('Пельмени', 62, 100004),
  ('Жаркое', 78, 100005),
  ('Сок', 33, 100005),
  ('Суп Харчо', 62, 100005),
  ('Сок', 33, 100006),
  ('Суп Харчо', 62, 100006),
  ('Пельмени', 62, 100007),
  ('Сок', 33, 100007);


INSERT INTO votes (restaurant_id, user_id, date_vote) VALUES
  (100002, 100000, '2017-10-07'),
  (100003, 100001, '2017-10-07'),
  (100002, 100001, '2017-10-06');