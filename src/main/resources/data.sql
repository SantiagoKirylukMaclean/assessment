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

INSERT INTO insurance (name, address) VALUES ('Sallia','Gran via 123');
INSERT INTO insurance (name, address) VALUES ('Rotular','Avinguda Diagonal 123');
INSERT INTO insurance (name, address) VALUES ('Xenix','Marina 123');

--Insert Users
INSERT INTO user (username,email,password,name,last_name, active, insurance_id) VALUES ('john','john@nash.com','$2a$10$Y8me22Myj.xSG2HH5htIx.aQWW3mByPrd45ZTCKQqD4oyb..qcN3C', 'John', 'Nash',1,1);
INSERT INTO user (username,email,password,name,last_name, active, insurance_id) VALUES ('albert','albert@einstein.com','$2a$10$Y8me22Myj.xSG2HH5htIx.aQWW3mByPrd45ZTCKQqD4oyb..qcN3C', 'Albert', 'Einstein',1,1);
INSERT INTO user (username,email,password,name,last_name, active, insurance_id) VALUES ('nikola','nikola@tesla.com','$2a$10$Y8me22Myj.xSG2HH5htIx.aQWW3mByPrd45ZTCKQqD4oyb..qcN3C', 'Nikola','Tesla',1,1);

INSERT INTO user (username,email,password,name,last_name, active, insurance_id) VALUES ('richard','richard@hendricks.com','$2a$10$Y8me22Myj.xSG2HH5htIx.aQWW3mByPrd45ZTCKQqD4oyb..qcN3C', 'richard','Hendricks',1,2);
INSERT INTO user (username,email,password,name,last_name, active, insurance_id) VALUES ('gilfoyle','gilfoyle@gomez.com','$2a$10$Y8me22Myj.xSG2HH5htIx.aQWW3mByPrd45ZTCKQqD4oyb..qcN3C', 'Gilfoyle','gomez',1,2);
INSERT INTO user (username,email,password,name,last_name, active, insurance_id) VALUES ('dinesh','dinesh@chugtai.com','$2a$10$Y8me22Myj.xSG2HH5htIx.aQWW3mByPrd45ZTCKQqD4oyb..qcN3C', 'Dinesh','Chugtai',1,2);

INSERT INTO user (username,email,password,name,last_name, active, insurance_id) VALUES ('steve','steve@wozniak.com','$2a$10$Y8me22Myj.xSG2HH5htIx.aQWW3mByPrd45ZTCKQqD4oyb..qcN3C', 'Steve',' Wozniak',1,3);
INSERT INTO user (username,email,password,name,last_name, active, insurance_id) VALUES ('stevej','steve@jobs.com','$2a$10$Y8me22Myj.xSG2HH5htIx.aQWW3mByPrd45ZTCKQqD4oyb..qcN3C', 'Steve','Jobs',1,3);
INSERT INTO user (username,email,password,name,last_name, active, insurance_id) VALUES ('ronald','ronald@wayne.com','$2a$10$Y8me22Myj.xSG2HH5htIx.aQWW3mByPrd45ZTCKQqD4oyb..qcN3C', 'ronald','Wayne',1,3);
--

--Insert roles
INSERT INTO user_role (user_id, role_id) VALUES (1,1);
INSERT INTO user_role (user_id, role_id) VALUES (2,2);
INSERT INTO user_role (user_id, role_id) VALUES (3,3);

INSERT INTO user_role (user_id, role_id) VALUES (4,1);
INSERT INTO user_role (user_id, role_id) VALUES (5,2);
INSERT INTO user_role (user_id, role_id) VALUES (6,3);

INSERT INTO user_role (user_id, role_id) VALUES (7,1);
INSERT INTO user_role (user_id, role_id) VALUES (8,2);
INSERT INTO user_role (user_id, role_id) VALUES (9,3);

--Insert Policies
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Sergio','Almirón','Almiron@gmail.com',1);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Sergio','Batista','Batista@gmail.com',1);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Ricardo','Bochini','Bochini@gmail.com',1);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Claudio','Borghi','Borghi@gmail.com',1);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Jose','Brown','Brown@gmail.com',1);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Daniel','Pasarella','Pasarella@gmail.com',1);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Jorge','Burruchaga','Burruchaga@gmail.com',1);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Nestor','Clausen','Clausen@gmail.com',2);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Jose','Cuciuffo','Cuciuffo@gmail.com',2);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Diego','Maradona','Maradona@gmail.com',2);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Jorge','Valdano','Valdano@gmail.com',2);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Hector','Enrique','Enrique@gmail.com',2);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Oscar','Garre','Garre@gmail.com',2);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Ricardo','Giusti','Giusti@gmail.com',2);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Luis','Islas','Islas@gmail.com',3);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Julio','Olarticoechea','Olarticoechea@gmail.com',3);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Pedro','Pasculli','Pasculli@gmail.com',3);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Nery','Pumpido','Pumpido@gmail.com',3);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','OScar','Ruggeri','Ruggeri@gmail.com',3);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Carlos','Tapia','Tapia@gmail.com',3);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Marcelo','Trobbiani','Trobbiani@gmail.com',3);
INSERT INTO policie (policie_description, covered_name, covered_last_name, covered_email, policie_insurance) VALUES ('Auto','Hector','Zelada','Zelada@gmail.com',3);
--


--INSERT INTO INSURANCE_POLICIES VALUES (1,1);
--INSERT INTO INSURANCE_POLICIES VALUES (1,2);
--INSERT INTO INSURANCE_POLICIES VALUES (1,3);
--INSERT INTO INSURANCE_POLICIES VALUES (1,4);
--INSERT INTO INSURANCE_POLICIES VALUES (1,5);
--INSERT INTO INSURANCE_POLICIES VALUES (1,6);
--INSERT INTO INSURANCE_POLICIES VALUES (1,7);
--INSERT INTO INSURANCE_POLICIES VALUES (2,8);
--INSERT INTO INSURANCE_POLICIES VALUES (2,9);
--INSERT INTO INSURANCE_POLICIES VALUES (2,10);
--INSERT INTO INSURANCE_POLICIES VALUES (2,11);
--INSERT INTO INSURANCE_POLICIES VALUES (2,12);
--INSERT INTO INSURANCE_POLICIES VALUES (2,13);
--INSERT INTO INSURANCE_POLICIES VALUES (2,14);
--INSERT INTO INSURANCE_POLICIES VALUES (3,15);
--INSERT INTO INSURANCE_POLICIES VALUES (3,16);
--INSERT INTO INSURANCE_POLICIES VALUES (3,17);
--INSERT INTO INSURANCE_POLICIES VALUES (3,18);
--INSERT INTO INSURANCE_POLICIES VALUES (3,19);
--INSERT INTO INSURANCE_POLICIES VALUES (3,20);
--INSERT INTO INSURANCE_POLICIES VALUES (3,21);
--INSERT INTO INSURANCE_POLICIES VALUES (3,22);
--