create table sb_employee1
(
  employeeId int auto_increment
    primary key,
  firstName            varchar(250) not null,
  lastName             varchar(250) not null,
  addedDate            varchar(250) not null,
  employmentStatus     varchar(250) not null
) DEFAULT CHARSET=utf8;
