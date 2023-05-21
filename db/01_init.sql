CREATE USER app WITH PASSWORD 'secret_app_password';

CREATE SCHEMA card_holder_schema;

CREATE TABLE IF NOT EXISTS card_holder_schema.ref_status (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS card_holder_schema.card_holder (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    oib CHAR(11) UNIQUE NOT NULL,
    status_id INT REFERENCES card_holder_schema.ref_status(id) DEFAULT 1 NOT NULL
);

GRANT USAGE ON SCHEMA card_holder_schema TO app;

GRANT SELECT, INSERT, UPDATE, DELETE ON card_holder_schema.ref_status TO app;
GRANT SELECT, INSERT, UPDATE, DELETE ON card_holder_schema.card_holder TO app;

GRANT USAGE, SELECT, UPDATE ON SEQUENCE card_holder_schema.ref_status_id_seq TO app;
GRANT USAGE, SELECT, UPDATE ON SEQUENCE card_holder_schema.card_holder_id_seq TO app;