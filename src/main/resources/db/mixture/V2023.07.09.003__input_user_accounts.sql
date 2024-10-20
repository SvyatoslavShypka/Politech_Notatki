-- Create user_account's table
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
   -- "default"
   '$2a$10$nGVv0dMqjyzcS0pA/9lwt.RAGhy2vAlWrA05gzGkIspOSKdJRC9qu',
   true,
   true,
   true,
   true,
   'USER'),

    (DEFAULT,
   'super_admin@gmail.com',
   '$2a$10$IZRxEPN2gKePZMPdGlEYmOpMfd62.CjYdXd2LuXbikT9JF2Rz4D6i',
   true,
   true,
   true,
   true,
   'USER,ADMIN,SUPER_ADMIN')
;
