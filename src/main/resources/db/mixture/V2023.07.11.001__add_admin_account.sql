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
   'admin',
     -- "admin"
   '$2a$10$fU3tS7uddkaupjg5T7/EQuQyJnvO6V7P7HKNJb2CnlZ6Vhp/8VfFC',
   true,
   true,
   true,
   true,
   'ADMIN')
;
