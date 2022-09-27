CREATE TABLE CAR
(
    id serial primary key,
    brand text not null,
    model text not null,
    price integer check (CAR.price > 0)
);
CREATE TABLE DRIVER
(
    id serial primary key,
    name text not null,
    age add check (age>18),
    DRIVER LICENSE boolen default false,
    car_id serial REFERENCES CAR (id)
);