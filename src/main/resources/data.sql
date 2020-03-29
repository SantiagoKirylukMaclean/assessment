--DROP TABLE IF EXISTS insurance;

--CREATE TABLE insurance (
--id LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
--name VARCHAR(256) NOT NULL,
--username VARCHAR(256) NOT NULL,
--password VARCHAR(256) NOT NULL
--);

INSERT INTO `role` VALUES (1,'ROLE_USER');
INSERT INTO `role` VALUES (2,'ROLE_PM');
INSERT INTO `role` VALUES (3,'ROLE_SUP');

INSERT INTO insurance (name) VALUES ('Sallia');
INSERT INTO insurance (name) VALUES ('Rotular');
INSERT INTO insurance (name) VALUES ('Xenix');

--Insert Users
INSERT INTO user (username,email,password,name,last_name, active, insurance_id, role_id) VALUES ('john','john@nash.com','12345', 'John', 'Nash',1,1,1);
INSERT INTO user (username,email,password,name,last_name, active, insurance_id, role_id) VALUES ('albert','albert@einstein.com','12345', 'Albert', 'Einstein',1,1,2);
INSERT INTO user (username,email,password,name,last_name, active, insurance_id, role_id) VALUES ('nikola','nikola@tesla.com','12345', 'Nikola','Tesla',1,1,3);

INSERT INTO user (username,email,password,name,last_name, active, insurance_id, role_id) VALUES ('richard','richard@hendricks.com','12345', 'richard','Hendricks',1,2,1);
INSERT INTO user (username,email,password,name,last_name, active, insurance_id, role_id) VALUES ('gilfoyle','gilfoyle@gomez.com','12345', 'Gilfoyle','gomez',1,2,2);
INSERT INTO user (username,email,password,name,last_name, active, insurance_id, role_id) VALUES ('dinesh','dinesh@chugtai.com','12345', 'Dinesh','Chugtai',1,2,3);

INSERT INTO user (username,email,password,name,last_name, active, insurance_id, role_id) VALUES ('steve','steve@wozniak.com','12345', 'Steve',' Wozniak',1,3,1);
INSERT INTO user (username,email,password,name,last_name, active, insurance_id, role_id) VALUES ('stevej','steve@jobs.com','12345', 'Steve','Jobs',1,3,2);
INSERT INTO user (username,email,password,name,last_name, active, insurance_id, role_id) VALUES ('ronald','ronald@wayne.com','12345', 'ronald','Wayne',1,3,3);
--

--Insert Policies
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Sergio','Almirón','Almiron@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Sergio','Batista','Batista@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Ricardo','Bochini','Bochini@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Claudio','Borghi','Borghi@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Jose','Brown','Brown@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Daniel','Pasarella','Pasarella@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Jorge','Burruchaga','Burruchaga@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Nestor','Clausen','Clausen@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Jose','Cuciuffo','Cuciuffo@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Diego','Maradona','Maradona@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Jorge','Valdano','Valdano@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Hector','Enrique','Enrique@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Oscar','Garre','Garre@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Ricardo','Giusti','Giusti@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Luis','Islas','Islas@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Julio','Olarticoechea','Olarticoechea@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Pedro','Pasculli','Pasculli@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Nery','Pumpido','Pumpido@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','OScar','Ruggeri','Ruggeri@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Carlos','Tapia','Tapia@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Marcelo','Trobbiani','Trobbiani@gmail.com');
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email) VALUES ('Auto','Hector','Zelada','Zelada@gmail.com');
--
INSERT INTO INSURANCE_POLICIES VALUES (1,1);
INSERT INTO INSURANCE_POLICIES VALUES (1,2);
INSERT INTO INSURANCE_POLICIES VALUES (1,3);
INSERT INTO INSURANCE_POLICIES VALUES (1,4);
INSERT INTO INSURANCE_POLICIES VALUES (1,5);
INSERT INTO INSURANCE_POLICIES VALUES (1,6);
INSERT INTO INSURANCE_POLICIES VALUES (1,7);
INSERT INTO INSURANCE_POLICIES VALUES (2,8);
INSERT INTO INSURANCE_POLICIES VALUES (2,9);
INSERT INTO INSURANCE_POLICIES VALUES (2,10);
INSERT INTO INSURANCE_POLICIES VALUES (2,11);
INSERT INTO INSURANCE_POLICIES VALUES (2,12);
INSERT INTO INSURANCE_POLICIES VALUES (2,13);
INSERT INTO INSURANCE_POLICIES VALUES (2,14);
INSERT INTO INSURANCE_POLICIES VALUES (3,15);
INSERT INTO INSURANCE_POLICIES VALUES (3,16);
INSERT INTO INSURANCE_POLICIES VALUES (3,17);
INSERT INTO INSURANCE_POLICIES VALUES (3,18);
INSERT INTO INSURANCE_POLICIES VALUES (3,19);
INSERT INTO INSURANCE_POLICIES VALUES (3,20);
INSERT INTO INSURANCE_POLICIES VALUES (3,21);
INSERT INTO INSURANCE_POLICIES VALUES (3,22);
--