DROP TABLE IF EXISTS employee;
CREATE TABLE IF NOT EXISTS employee (
  employee_id int(11) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name varchar(250) NOT NULL,
  last_name varchar(250) NOT NULL,
  added_date varchar(250) NOT NULL,
  employment_status TINYINT(4) NOT NULL COMMENT '0 for deleted record / 1 for valid record ',
) DEFAULT CHARSET=utf8;