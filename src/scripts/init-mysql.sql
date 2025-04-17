-- cardholder database
DROP DATABASE IF EXISTS cardholderdb;
DROP USER IF EXISTS `cardholderadmin`@`%`;
DROP USER IF EXISTS `paymentuseradmin`@`%`;
CREATE DATABASE IF NOT EXISTS `cardholderdb` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `cardholderadmin`@`%` IDENTIFIED BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `cardholderdb`.* TO `cardholderadmin`@`%`;
CREATE USER IF NOT EXISTS `paymentuseradmin`@`%` IDENTIFIED BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `cardholderdb`.* TO `paymentuseradmin`@`%`;
FLUSH PRIVILEGES;

-- card database
DROP DATABASE IF EXISTS carddb;
DROP USER IF EXISTS `cardadmin`@`%`;
DROP USER IF EXISTS `paymentuseradmin`@`%`;
CREATE DATABASE IF NOT EXISTS `carddb` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `cardadmin`@`%` IDENTIFIED BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `carddb`.* TO `cardadmin`@`%`;
CREATE USER IF NOT EXISTS `paymentuseradmin`@`%` IDENTIFIED BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `carddb`.* TO `paymentuseradmin`@`%`;
FLUSH PRIVILEGES;

-- card PAN database
DROP DATABASE IF EXISTS pandb;
DROP USER IF EXISTS `panadmin`@`%`;
DROP USER IF EXISTS `paymentuseradmin`@`%`;
CREATE DATABASE IF NOT EXISTS `pandb` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `panadmin`@`%` IDENTIFIED BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `pandb`.* TO `panadmin`@`%`;
CREATE USER IF NOT EXISTS `paymentuseradmin`@`%` IDENTIFIED BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `pandb`.* TO `paymentuseradmin`@`%`;
FLUSH PRIVILEGES;
