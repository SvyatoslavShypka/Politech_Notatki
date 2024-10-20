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
   'jdbcuser',
     -- "jdbcDefault"
   '$2a$10$GiBxmijMXnv1Jgg15lPFsOX7CG7Dz2kXfj4NOUgjtq2UDP1EA.IDi',
   true,
   true,
   true,
   true,
   'USER,ADMIN')
;
