
INSERT INTO users (name, username, password)
VALUES ('John Doe', 'johndoe@gmail.com', 'hashed_password_for_johndoe'),
       ('Mike Smith', 'mikesmith@yahoo.com', 'hashed_password_for_mikesmith');


INSERT INTO tasks (title, description, status, expiration_date)
VALUES ('Buy cheese', NULL, 'TODO', '2023-01-29 12:00:00'),
       ('Do homework', 'Math, Physics, Literature', 'IN_PROGRESS', '2023-01-31 00:00:00'),
       ('Clean rooms', NULL, 'DONE', NULL),
       ('Call Mike', 'Ask about meeting', 'TODO', '2023-02-01 00:00:00');


INSERT INTO users_tasks (user_id, task_id)
VALUES (1, 2),  -- John Doe -> Do homework
       (1, 3),  -- John Doe -> Clean rooms
       (1, 4),  -- John Doe -> Call Mike
       (2, 1),  -- Mike Smith -> Buy cheese
       (2, 2),  -- Mike Smith -> Do homework
       (2, 3),  -- Mike Smith -> Clean rooms
       (2, 4);  -- Mike Smith -> Call Mike


INSERT INTO users_roles (user_id, role)
VALUES (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');
