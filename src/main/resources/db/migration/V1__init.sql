CREATE EXTENSION IF NOT EXISTS CITEXT;

CREATE TABLE IF NOT EXISTS users (
  id       SERIAL NOT NULL PRIMARY KEY,
  login    CITEXT NOT NULL UNIQUE,
  email    CITEXT NOT NULL UNIQUE,
  password TEXT   NOT NULL,
  name     TEXT   NOT NULL,
  token    TEXT   NOT NULL
);

CREATE TABLE IF NOT EXISTS commands (
  id          SERIAL NOT NULL PRIMARY KEY,
  user_id     SERIAL NOT NULL REFERENCES users (id),
  url         TEXT   NOT NULL,
  description BYTEA  NOT NULL,
  params      BYTEA,
  command     TEXT
)

