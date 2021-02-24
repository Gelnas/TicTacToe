INSERT INTO player (id, username, password, email, count_wins, count_defeat, count_draw, created, updated)
VALUES (1, 'user', '$2y$04$63U.dyRiUW.sPTXBpmcIwOklxmTg33H6W/C8AHwNmEEk4PuaXn9OC', 'user@gmail.com', 0, 0, 0, '21/02/2020', '21/02/2020');
INSERT INTO player_roles (player_id, role_id)
VALUES (1,2);
