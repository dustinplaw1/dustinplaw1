CREATE TABLE IF NOT EXISTS contracts
(
contract_id varchar(20) not null primary key,
employee_id varchar(30) not null,
tool_id varchar(20) not null,
date_borrowed timestamp not null,
due_date timestamp not null,
date_returned timestamp null
);


CREATE TABLE IF NOT EXISTS employees
(
employee_id varchar(30) NOT NULL PRIMARY KEY,
first_name varchar(50) NOT NULL,
last_name varchar(50) NOT NULL,
phone_num varchar(15),
emp_description varchar(200),
employee_type varchar(20) NOT NULL
);

CREATE TABLE logins
(
employee_id varchar(30) NOT NULL PRIMARY KEY,
password varchar(50)
);

CREATE TABLE tools
(
tool_id varchar(20) NOT NULL PRIMARY KEY,
tool_name varchar(50) NOT NULL,
tool_description varchar(30)
);


INSERT INTO logins (employee_id, password) VALUES ('1', '123');
