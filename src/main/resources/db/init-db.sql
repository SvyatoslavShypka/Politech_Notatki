-- Create notes table
DROP TABLE IF EXISTS notes;
CREATE TABLE IF NOT EXISTS notes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    content VARCHAR(500)
);
-- Create user_account's table
CREATE TABLE IF NOT EXISTS user_account (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            username VARCHAR(50),
                                            password VARCHAR(255),
                                            is_account_non_expired BOOLEAN,
                                            is_account_non_locked BOOLEAN,
                                            is_credentials_non_expired BOOLEAN,
                                            is_enabled BOOLEAN,
                                            authorities TEXT
);

-- Create user_account's table
SET SCHEMA PUBLIC;
INSERT INTO user_account
(id,
 username,
 password,
 is_account_non_expired,
 is_account_non_locked,
 is_credentials_non_expired,
 is_enabled,
 authorities)
VALUES
    (DEFAULT,
     'user',
     '$2a$10$nGVv0dMqjyzcS0pA/9lwt.RAGhy2vAlWrA05gzGkIspOSKdJRC9qu',
     true,
     true,
     true,
     true,
     'USER'),

    (DEFAULT,
     'admin',
     '$2a$10$IZRxEPN2gKePZMPdGlEYmOpMfd62.CjYdXd2LuXbikT9JF2Rz4D6i',
     true,
     true,
     true,
     true,
     'ADMIN'),

    (DEFAULT,
     'super_admin',
     '$2a$10$IZRxEPN2gKePZMPdGlEYmOpMfd62.CjYdXd2LuXbikT9JF2Rz4D6i',
     true,
     true,
     true,
     true,
     'USER,ADMIN,SUPER_ADMIN')
;

INSERT INTO notes (id, title, content)
VALUES
    (DEFAULT,'Title1', 'Content1'),
    (DEFAULT, 'Title2', 'Content2'),
    (DEFAULT, 'Title3', 'Content3'),
    (DEFAULT, 'Title4', 'Content4'),
    (DEFAULT, 'Title5', 'Content5')
;



