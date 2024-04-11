INSERT INTO client (name) VALUES
    ('Oleg Ivanov'),
    ('Kyrylo Dobrohot'),
    ('Oleksandr Ivashchenko'),
    ('Iryna Natalukha'),
    ('Andriy Dubovyk'),
    ('Alina Stefan'),
    ('Mykhailo Shcherban'),
    ('Anna Shabalina'),
    ('Olga Chernyshova'),
    ('Evgen Feshak');

INSERT INTO planet (id, name) VALUES
    ('MARS', 'Mars'),
    ('VEN', 'Venus'),
    ('EAR', 'Earth'),
    ('JUP', 'Jupiter'),
    ('SAT', 'Saturn');

INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES
    (1, 'MARS', 'VEN'),
    (2, 'MARS', 'EAR'),
    (3, 'VEN', 'MARS'),
    (4, 'EAR', 'SAT'),
    (5, 'MARS', 'JUP'),
    (6, 'JUP', 'VEN'),
    (7, 'EAR', 'MARS'),
    (8, 'JUP', 'SAT'),
    (9, 'SAT', 'VEN'),
    (10, 'VEN', 'JUP');