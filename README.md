# PizzaAPI
Backend puntanta n 1 dell'Arte della Programmazione

1) Creare il DB su MySQL utilizzando l'utente root;

CREATE DATABASE pizzadb; 
CREATE USER 'pizzauser'@'localhost' IDENTIFIED BY 'pizzapswd'; 
GRANT ALL PRIVILEGES ON . TO 'pizzauser'@'localhost' WITH GRANT OPTION; 
CREATE USER 'pizzauser'@'%' IDENTIFIED BY 'pizzapswd'; 
GRANT ALL PRIVILEGES ON . TO 'pizzauser'@'%' WITH GRANT OPTION;

2) Modificare nel file application.properties il seguente attributo:
spring.datasource.url=jdbc:mysql://<YOUR_IP>:<YOUR_PORT>/pizzadb

3) Avviare l'applicazione! Saranno genererate le tabelle partendo dalle classi entità definite nel package com.adp.pizza.dataLayer.models;

4) Lanciare il test populateDB() presente nella classe PizzaApplicationTests.
  Saranno caricati nelle tabelle precedentemente create le pizze e alciuni condimenti visti nell'applicazione.

5) Tutto qua. Buon divertimento!!!
