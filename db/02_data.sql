INSERT INTO card_holder_schema.ref_status (description)
VALUES ('ACTIVE'),
       ('INACTIVE');

INSERT INTO card_holder_schema.card_holder (first_name, last_name, oib, status_id)
VALUES ('Pero', 'Peric', '12345678901', 1),
       ('Bob', 'Alice', '23456789012', 2);
