CREATE TABLE person
(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(25) NOT NULL,
    pps_number VARCHAR(8) NOT NULL,
    date_birth DATE NOT NULL,
    mobile_phone_number VARCHAR(10),
    creation_date TIMESTAMP DEFAULT current_timestamp(),

);
CREATE UNIQUE INDEX "person_id_uindex" ON person (id);
CREATE UNIQUE INDEX "person_pps_number_uindex" ON person (pps_number);