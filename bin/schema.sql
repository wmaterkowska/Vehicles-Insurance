CREATE TABLE users(
    id_user BIGSERIAL PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255),
    enabled BOOLEAN
);

CREATE TABLE roles(
    id_role BIGSERIAL PRIMARY KEY,
    role VARCHAR(255)
);

CREATE TABLE users_roles (
    id_user BIGSERIAL,
    id_role BIGSERIAL,
    PRIMARY KEY (id_user, id_role),
    CONSTRAINT bc_user FOREIGN KEY (id_user) REFERENCES users(id_user),
    CONSTRAINT bc_role FOREIGN KEY (id_role) REFERENCES roles(id_role)
);


CREATE TABLE books (
    id_book BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    published_year INT,
    id_user BIGSERIAL REFERENCES users(id_user)
);

CREATE TABLE artists (
    id_artist BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    family_name VARCHAR(255),
    birth_place VARCHAR(255),
    birth_year INT,
    id_user BIGSERIAL REFERENCES users(id_user)
);

INSERT INTO "users" ( email, password, enabled)
    VALUES ( 'admin@gmail.com', 'admin12345', true);
INSERT INTO "users" ( email, password, enabled)
VALUES ( 'johnD@gmail.com', '123456789', true);

INSERT INTO "roles" (role) VALUES ('admin');
INSERT INTO "roles" (role) VALUES ('user');

INSERT INTO "users_roles" (id_user, id_role) VALUES (1, 1);
INSERT INTO "users_roles" (id_user, id_role) VALUES (2, 2);