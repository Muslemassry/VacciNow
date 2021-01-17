DROP TABLE IF EXISTS application;
DROP TABLE IF EXISTS vaccine;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS branch;
DROP TABLE IF EXISTS billionaires;

CREATE TABLE branch (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL
);

INSERT INTO branch (name, address) VALUES ('Alsalmaneya Hostpital', '11 Abbas Alakad, Nasr City, Cairo');
INSERT INTO branch (name, address) VALUES ('Dar Alshefaa', '38 Hadayek Alamadi, Almaadi, Cairo');
INSERT INTO branch (name, address) VALUES ('Giza Hostibal', '21 Mostafa Mahmoud, Almohamdeseen, Giza');

CREATE TABLE patient (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

INSERT INTO patient (name) VALUES ('Ismael Mohamed Hasan');
INSERT INTO patient (name) VALUES ('Ali Abd Alnaser');
INSERT INTO patient (name) VALUES ('Sara Alsayed');
INSERT INTO patient (name) VALUES ('Test Patient');

CREATE TABLE vaccine (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  price DOUBLE NOT NULL,
  status VARCHAR(1) NOT NULL DEFAULT 'V', -- V for available, A for applied, C for confirmed
  branch_id INT NOT NULL, 
  vaccination_date TIMESTAMP,
  FOREIGN KEY(branch_id) REFERENCES branch(id)
);

INSERT INTO vaccine (name, price, status, branch_id) VALUES ('Pfizer Covid .19', 100.0, 'C', 1);
INSERT INTO vaccine (name, price, status, branch_id) VALUES ('Pfizer Covid .19', 100.0, 'V', 1);
INSERT INTO vaccine (name, price, status, branch_id) VALUES ('Pfizer Covid .19', 100.0, 'V', 1);
INSERT INTO vaccine (name, price, status, branch_id) VALUES ('Pfizer Covid .19', 100.0, 'A', 2);
INSERT INTO vaccine (name, price, status, branch_id) VALUES ('Pfizer Covid .19', 100.0, 'V', 2);
INSERT INTO vaccine (name, price, status, branch_id) VALUES ('Pfizer Covid .19', 100.0, 'A', 3);
INSERT INTO vaccine (name, price, status, branch_id) VALUES ('Pfizer Covid .19', 100.0, 'V', 3);
INSERT INTO vaccine (name, price, status, branch_id) VALUES ('Pfizer Covid .19', 100.0, 'V', 3);
INSERT INTO vaccine (name, price, status, branch_id) VALUES ('Pfizer Covid .19', 100.0, 'V', 3);

CREATE TABLE application (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  payment_type VARCHAR(1) NOT NULL, -- C for cash, B for Card, F for fawry
  appointment TIMESTAMP NOT NULL,
  patient_id INT NOT NULL,
  branch_id INT NOT NULL,
  vaccine_id INT NOT NULL,
  FOREIGN KEY(patient_id) REFERENCES patient(id),
  FOREIGN KEY(branch_id) REFERENCES branch(id),
  FOREIGN KEY(vaccine_id) REFERENCES vaccine(id)
);

INSERT INTO application (payment_type, appointment, patient_id, branch_id, vaccine_id) VALUES ('C', '2021-01-14 10:00:00', 1, 1, 1);
INSERT INTO application (payment_type, appointment, patient_id, branch_id, vaccine_id) VALUES ('B', '2021-01-14 10:00:00', 2, 2, 4);
INSERT INTO application (payment_type, appointment, patient_id, branch_id, vaccine_id) VALUES ('F', '2021-01-14 10:00:00', 3, 3, 6);

