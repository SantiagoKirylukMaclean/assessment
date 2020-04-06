--DROP TABLE IF EXISTS insurance;

INSERT INTO `role` VALUES (1,'ROLE_USER');
INSERT INTO `role` VALUES (2,'ROLE_PM');
INSERT INTO `role` VALUES (3,'ROLE_SUP');

INSERT INTO insurance (name, address, modify_date_time, automatic_accept_amount) VALUES ('Sallia','Gran via 123','2020-04-02 08:00:00.000', 50000.00);
INSERT INTO insurance (name, address, modify_date_time, automatic_accept_amount) VALUES ('Rotular','Avinguda Diagonal 123','2020-04-02 08:00:00.000', 45000.00);
INSERT INTO insurance (name, address, modify_date_time, automatic_accept_amount) VALUES ('Xenix','Marina 123','2020-04-02 08:00:00.000', 40000.00);

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

--Insert policys
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Car','Sergio','Almirón','Almiron@gmail.com',1);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('House','Sergio','Batista','Batista@gmail.com',1);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Life','Ricardo','Bochini','Bochini@gmail.com',1);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Car','Claudio','Borghi','Borghi@gmail.com',1);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('House','Jose','Brown','Brown@gmail.com',1);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Life','Daniel','Pasarella','Pasarella@gmail.com',1);
--INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Car','Jorge','Burruchaga','Burruchaga@gmail.com',1);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('House','Nestor','Clausen','Clausen@gmail.com',2);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Life','Jose','Cuciuffo','Cuciuffo@gmail.com',2);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Car','Diego','Maradona','Maradona@gmail.com',2);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('House','Jorge','Valdano','Valdano@gmail.com',2);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Life','Hector','Enrique','Enrique@gmail.com',2);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Car','Oscar','Garre','Garre@gmail.com',2);
--INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('House','Ricardo','Giusti','Giusti@gmail.com',2);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Life','Luis','Islas','Islas@gmail.com',3);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Car','Julio','Olarticoechea','Olarticoechea@gmail.com',3);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('House','Pedro','Pasculli','Pasculli@gmail.com',3);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Life','Nery','Pumpido','Pumpido@gmail.com',3);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Car','OScar','Ruggeri','Ruggeri@gmail.com',3);
INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('House','Carlos','Tapia','Tapia@gmail.com',3);
--INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Life','Marcelo','Trobbiani','Trobbiani@gmail.com',3);
--INSERT INTO policy (policy_description, covered_name, covered_last_name, covered_email, insurance_id) VALUES ('Car','Hector','Zelada','Zelada@gmail.com',3);
--

--Insert claim
INSERT INTO claim (state, guilty_policy_id, victim_policy_id, amount, MODIFY_DATE_TIME) VALUES (1,1,2,1600.05,'2020-04-02 08:00:00.000');
INSERT INTO claim (state, guilty_policy_id, victim_policy_id, amount, MODIFY_DATE_TIME) VALUES (1,3,7,1600.05,'2020-04-02 08:00:00.000');
INSERT INTO claim (state, guilty_policy_id, victim_policy_id, amount, MODIFY_DATE_TIME) VALUES (1,4,13,1600.05,'2020-04-02 08:00:00.000');
INSERT INTO claim (state, guilty_policy_id, victim_policy_id, amount, MODIFY_DATE_TIME) VALUES (1,8,9,1600.05,'2020-04-02 08:00:00.000');
INSERT INTO claim (state, guilty_policy_id, victim_policy_id, amount, MODIFY_DATE_TIME) VALUES (1,10,14,1600.05,'2020-04-02 08:00:00.000');
INSERT INTO claim (state, guilty_policy_id, victim_policy_id, amount, MODIFY_DATE_TIME) VALUES (1,11,5,1600.05,'2020-04-02 08:00:00.000');
INSERT INTO claim (state, guilty_policy_id, victim_policy_id, amount, MODIFY_DATE_TIME) VALUES (1,15,16,1600.05,'2020-04-02 08:00:00.000');
INSERT INTO claim (state, guilty_policy_id, victim_policy_id, amount, MODIFY_DATE_TIME) VALUES (1,17,6,1600.05,'2020-04-02 08:00:00.000');
INSERT INTO claim (state, guilty_policy_id, victim_policy_id, amount, MODIFY_DATE_TIME) VALUES (1,18,12,1600.05,'2020-04-02 08:00:00.000');

--
INSERT INTO NEGOTIATION (AMOUNT, DESCRIPTION_MESSAGE, MODIFY_DATE_TIME) VALUES (60001,'Claim is started by victim', '2020-04-06 00:51:43.554');
INSERT INTO NEGOTIATION (AMOUNT, DESCRIPTION_MESSAGE, MODIFY_DATE_TIME) VALUES (60002,'Claim is started by victim', '2020-04-06 00:51:43.554');
INSERT INTO NEGOTIATION (AMOUNT, DESCRIPTION_MESSAGE, MODIFY_DATE_TIME) VALUES (60003,'Claim is started by victim', '2020-04-06 00:51:43.554');
INSERT INTO NEGOTIATION (AMOUNT, DESCRIPTION_MESSAGE, MODIFY_DATE_TIME) VALUES (60004,'Claim is started by victim', '2020-04-06 00:51:43.554');
INSERT INTO NEGOTIATION (AMOUNT, DESCRIPTION_MESSAGE, MODIFY_DATE_TIME) VALUES (60005,'Claim is started by victim', '2020-04-06 00:51:43.554');
INSERT INTO NEGOTIATION (AMOUNT, DESCRIPTION_MESSAGE, MODIFY_DATE_TIME) VALUES (60006,'Claim is started by victim', '2020-04-06 00:51:43.554');
INSERT INTO NEGOTIATION (AMOUNT, DESCRIPTION_MESSAGE, MODIFY_DATE_TIME) VALUES (60007,'Claim is started by victim', '2020-04-06 00:51:43.554');
INSERT INTO NEGOTIATION (AMOUNT, DESCRIPTION_MESSAGE, MODIFY_DATE_TIME) VALUES (60008,'Claim is started by victim', '2020-04-06 00:51:43.554');
INSERT INTO NEGOTIATION (AMOUNT, DESCRIPTION_MESSAGE, MODIFY_DATE_TIME) VALUES (60009,'Claim is started by victim', '2020-04-06 00:51:43.554');

--

INSERT INTO CLAIM_NEGOTIATIONS (CLAIM_CLAIM_ID, NEGOTIATIONS_NEGOTIATION_ID ) VALUES (1,1);
INSERT INTO CLAIM_NEGOTIATIONS (CLAIM_CLAIM_ID, NEGOTIATIONS_NEGOTIATION_ID ) VALUES (2,2);
INSERT INTO CLAIM_NEGOTIATIONS (CLAIM_CLAIM_ID, NEGOTIATIONS_NEGOTIATION_ID ) VALUES (3,3);
INSERT INTO CLAIM_NEGOTIATIONS (CLAIM_CLAIM_ID, NEGOTIATIONS_NEGOTIATION_ID ) VALUES (4,4);
INSERT INTO CLAIM_NEGOTIATIONS (CLAIM_CLAIM_ID, NEGOTIATIONS_NEGOTIATION_ID ) VALUES (5,5);
INSERT INTO CLAIM_NEGOTIATIONS (CLAIM_CLAIM_ID, NEGOTIATIONS_NEGOTIATION_ID ) VALUES (6,6);
INSERT INTO CLAIM_NEGOTIATIONS (CLAIM_CLAIM_ID, NEGOTIATIONS_NEGOTIATION_ID ) VALUES (7,7);
INSERT INTO CLAIM_NEGOTIATIONS (CLAIM_CLAIM_ID, NEGOTIATIONS_NEGOTIATION_ID ) VALUES (8,8);
INSERT INTO CLAIM_NEGOTIATIONS (CLAIM_CLAIM_ID, NEGOTIATIONS_NEGOTIATION_ID ) VALUES (9,9);
