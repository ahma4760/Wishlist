DROP USER IF EXISTS wuser@Localhost;
    CREATE USER wuser@Localhost IDENTIFIED BY '@hm@dogy@hy@114';

        SELECT User, Host FROM mysql.user;
GRANT SELECT, INSERT, UPDATE, DELETE
    ON productswishlist.*
    TO wuser@Localhost;

SHOW GRANTS FOR wuser@Localhost;