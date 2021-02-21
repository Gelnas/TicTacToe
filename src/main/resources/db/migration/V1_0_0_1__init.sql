CREATE TABLE IF NOT EXISTS player (
    id             bigserial        PRIMARY KEY,
    username       varchar(50)      NOT NULL,
    password       varchar(70)      NOT NULL,
    email          varchar(60)      NOT NULL     UNIQUE,
    count_wins     int8             DEFAULT 0,
    count_defeat   int8             DEFAULT 0,
    created        timestamp        NOT NULL,
    updated        timestamp        NOT NULL
);

CREATE TABLE IF NOT EXISTS game (
    id             bigserial         PRIMARY KEY,
    status         varchar(11)       NOT NULL,
    field          varchar(17)       NOT NULL,
    created        timestamp         NOT NULL,
    updated        timestamp         NOT NULL
);

CREATE TABLE IF NOT EXISTS player_game (
    player_id      bigserial     NOT NULL,
    game_id        bigserial     NOT NULL,
    PRIMARY KEY    (player_id, game_id),
    FOREIGN KEY    (player_id)            REFERENCES player (id),
    FOREIGN KEY    (game_id)              REFERENCES game(id)
);

CREATE TABLE IF NOT EXISTS roles (
    id    bigserial   PRIMARY  KEY,
    role  varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS player_roles(
    player_id   int8    NOT NULL,
    role_id     integer NOT NULL,
    PRIMARY KEY (player_id, role_id),
    FOREIGN KEY (player_id) REFERENCES player (id),
    FOREIGN KEY (role_id)   REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS permission (
    id         bigserial   PRIMARY KEY,
    permission varchar(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles_permission (
    role_id   int8  NOT NULL,
    perm_id   int8  NOT NULL,
    PRIMARY  KEY (role_id, perm_id),
    FOREIGN KEY (perm_id) REFERENCES permission (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);