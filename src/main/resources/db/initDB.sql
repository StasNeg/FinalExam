DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR                  NOT NULL,
  email      VARCHAR                  NOT NULL,
  password   VARCHAR                  NOT NULL,
  registered TIMESTAMP DEFAULT now()  NOT NULL,
  enabled    BOOL DEFAULT TRUE        NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants (
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date    TIMESTAMP NOT NULL,
  name    TEXT      NOT NULL,
  address TEXT      NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_address__date_idx
  ON restaurants (date, address);

CREATE TABLE meals (
  id             INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurants_id INTEGER         NOT NULL,
  name           TEXT            NOT NULL,
  price          NUMERIC(100, 2) NOT NULL,

  FOREIGN KEY (restaurants_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX meals_unique_name_restaurantID_price_idx ON meals(name,restaurants_id, price);
CREATE INDEX meals_restaurantID_idx ON meals(restaurants_id);

CREATE TABLE votes
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id INTEGER   NOT NULL,
  user_id       INTEGER   NOT NULL,
  date_vote     TIMESTAMP NOT NULL
);
CREATE UNIQUE INDEX votes_unique_user_restaurant_idx
  ON votes (restaurant_id, user_id);
