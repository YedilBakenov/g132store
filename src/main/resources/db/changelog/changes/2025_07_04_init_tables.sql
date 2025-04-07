CREATE TABLE IF NOT EXISTS brands
(
    id          SERIAL PRIMARY KEY,
    name        varchar(255),
    country_id  int,
    description varchar(255)
);

CREATE TABLE IF NOT EXISTS countries
(
    id   SERIAL PRIMARY KEY,
    code varchar(255),
    name varchar(255)
);

CREATE TABLE IF NOT EXISTS products
(
    id          SERIAL PRIMARY KEY,
    created_at  timestamp,
    updated_at  timestamp,
    description varchar(255),
    name        varchar(255),
    price       double precision
);

CREATE TABLE IF NOT EXISTS products_countries
(
    product_id   int,
    countries_id int
);

CREATE TABLE IF NOT EXISTS users
(
    id        SERIAL PRIMARY KEY,
    age       int,
    full_name varchar(255),
    phone     varchar(255)
);


