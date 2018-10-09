DROP TABLE IF EXISTS employee;
CREATE TABLE IF NOT EXISTS employee (
  employee_id int(11) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name varchar(250) NOT NULL,
  last_name varchar(250) NOT NULL,
  added_date varchar(250) NOT NULL,
  employment_status BIT(1) NOT NULL, -- 0 for deleted record / 1 for valid record,
  company_id int NOT NULL,
  CONSTRAINT FOREIGN KEY(company_id) REFERENCES company(id)
) DEFAULT CHARSET=utf8;


CREATE TABLE company(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(250) NOT NULL,
	location VARCHAR(250),
	phone_number CHAR(8),
	added_date VARCHAR(250) NOT NULL,
	status BIT NOT NULL
);
