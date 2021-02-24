INSERT INTO roles (id, role)
VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO permission (id, permission)
VALUES (1, 'READ'), (2, 'WRITE'), (3, 'DELETE'), (4, 'CREATE'), (5, 'UPDATE');

INSERT INTO roles_permission (role_id, perm_id)
VALUES (1, 1), (1, 2), (1, 4), (1, 5), (2, 1), (2, 2), (2, 3), (2, 4), (2, 5);

INSERT INTO player (id, username, password, email, count_wins, count_defeat, count_draw, created, updated)
VALUES (0, 'bot', '0000', 'bot', 0, 0, 0, '21/02/2020', '21/02/2020');

