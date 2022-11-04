CREATE TABLE users
(
    id       UUID PRIMARY KEY,
    name     VARCHAR   NOT NULL,
    surname  VARCHAR   NOT NULL,
    birthday TIMESTAMP NOT NULL
);