CREATE TABLE USERS (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(30),
    lastName VARCHAR(30),
    address VARCHAR(70),
    city VARCHAR(30)
);

//código para crear el procediminento almacenado en MySQL:
CREATE PROCEDURE GetAllUsers()
DELIMITER //
BEGIN
    SELECT * FROM USERS;
END //
