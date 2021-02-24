delete from player;
delete from game;
delete from player_game;
delete from roles;
delete from player_roles;
delete from permission;
delete from roles_permission;

INSERT INTO roles (id, role)
VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO permission (id, permission)
VALUES (1, 'READ'), (2, 'WRITE'), (3, 'DELETE');

INSERT INTO roles_permission (role_id, perm_id)
VALUES (1, 1), (2, 1), (2, 2), (2, 3);

INSERT INTO player (id, username, password, email, count_wins, count_defeat, count_draw, created, updated)
VALUES (1, 'Tina', '$2y$04$63U.dyRiUW.sPTXBpmcIwOklxmTg33H6W/C8AHwNmEEk4PuaXn9OC', 'Tina@gmail.com', 5, 0, 0, parsedatetime('12-12-2020', 'dd-MM-yyyy'), parsedatetime('12-12-2020', 'dd-MM-yyyy')),
       (2, 'Alex', '$2y$04$63U.dyRiUW.sPTXBpmcIwOklxmTg33H6W/C8AHwNmEEk4PuaXn9OC', 'Alex@gmail.com', 14, 1, 0, parsedatetime('12-12-2020', 'dd-MM-yyyy'), parsedatetime('12-12-2020', 'dd-MM-yyyy')),
       (3, 'Nika', '$2y$04$63U.dyRiUW.sPTXBpmcIwOklxmTg33H6W/C8AHwNmEEk4PuaXn9OC', 'Nika@gmail.com', 2, 1, 0, parsedatetime('12-12-2020', 'dd-MM-yyyy'), parsedatetime('12-12-2020', 'dd-MM-yyyy'));

INSERT INTO player_roles (player_id, role_id)
VALUES (1,2), (2,1);

INSERT INTO game (id, status, field, created, updated)
VALUES (1, 'WON_PLAYER1', 'x~x~x ?~0~? 0~?~?', parsedatetime('12-12-2020', 'dd-MM-yyyy'), parsedatetime('12-12-2020', 'dd-MM-yyyy')),
       (2, 'IN_PROGRESS', '?~?~? ?~?~? ?~?~?', parsedatetime('12-12-2020', 'dd-MM-yyyy'), parsedatetime('12-12-2020', 'dd-MM-yyyy'));

INSERT INTO player_game (player_id, game_id)
VALUES (1, 1), (2, 1),
       (1, 2), (2, 2);

INSERT INTO history_moves (id, number, position, symbol, game_id)
values (1, 1, '00', 'x', 1), (2, 2, '11', '0', 1), (3, 3, '01', 'x', 1), (4, 4, '20', '0', 1), (5, 5, '02', 'x', 1);




