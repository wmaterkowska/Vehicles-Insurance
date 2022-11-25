CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    nick VARCHAR(255),
    login VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    additional_data VARCHAR(255),
    insert_time TIMESTAMP NOT NULL DEFAULT now()
    );

CREATE TABLE vehicles(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    login VARCHAR(255) NOT NULL REFERENCES users(login),
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    additional_data VARCHAR(255),
    insert_time TIMESTAMP NOT NULL
);

CREATE TABLE insurance_offers (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    vehicle_id BIGSERIAL NOT NULL REFERENCES vehicles(id),
    insurer VARCHAR(255) NOT NULL,
    price REAL NOT NULL,
    additional_data VARCHAR(255),
    insert_time TIMESTAMP NOT NULL
);
