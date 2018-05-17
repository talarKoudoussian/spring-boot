create table sb_employee1
(
  employeeId int auto_increment
    primary key,
  firstName  varchar(250) charset utf8 not null,
  lastName   varchar(250) charset utf8 not null,
  addedDate  datetime                  not null,
  status     int                       not null
);