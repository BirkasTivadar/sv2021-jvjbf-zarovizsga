CREATE TABLE players
(
    id          BIGINT AUTO_INCREMENT,
    player_name VARCHAR(255),
    birth_date  DATE,
    player_pos  VARCHAR(255),
    team_id     BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (team_id) REFERENCES teams (id)
);