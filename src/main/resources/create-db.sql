-- !!! Utilizzare utente root !!!

CREATE DATABASE pizzadb;
CREATE USER 'pizzauser'@'localhost' IDENTIFIED BY 'pizzapswd';
GRANT ALL PRIVILEGES ON *.* TO 'pizzauser'@'localhost' WITH GRANT OPTION;
CREATE USER 'pizzauser'@'%' IDENTIFIED BY 'pizzapswd';
GRANT ALL PRIVILEGES ON *.* TO 'pizzauser'@'%' WITH GRANT OPTION;