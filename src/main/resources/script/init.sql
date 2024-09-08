DROP TABLE IF EXISTS storage;

CREATE TABLE storage
(
    id              bigserial PRIMARY KEY,
    file            TEXT      NOT NULL,
    title           TEXT      NOT NULL,
    creation_date   TIMESTAMP NOT NULL,
    description     TEXT      NOT NULL,
    fileIf          UUID      NOT NULL
);