CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    nick VARCHAR(255),
    login VARCHAR(255) UNIQUE,
    password VARCHAR(255) NOT NULL,
    additional_data VARCHAR(255),
    insert_time TIMESTAMP NOT NULL DEFAULT now()
    );

CREATE TABLE vehicles(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    login VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    additional_data VARCHAR(255),
    insert_time TIMESTAMP NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (login) REFERENCES users(login)
);

CREATE TABLE insurances (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    vehicle_id BIGSERIAL NOT NULL,
    insurer VARCHAR(255) NOT NULL,
    price REAL NOT NULL,
    additional_data VARCHAR(255),
    insert_time TIMESTAMP NOT NULL,
    CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);

