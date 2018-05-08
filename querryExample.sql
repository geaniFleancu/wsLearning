create database EmployeePortal;

use EmployeePortal;
CREATE TABLE IF NOT EXISTS EmployeeInfo(
 id VARCHAR(200) AUTO_INCREMENT,
 name VARCHAR(100) NOT NULL,
 age INT NOT NULL,
 PRIMARY KEY(id));

use EmployeePortal;
CREATE TABLE IF NOT EXISTS EmployeeWorkInfo(
 id VARCHAR(200),
 employeeName VARCHAR(100) NOT NULL
 departName VARCHAR(100) NOT NULL,
 departFunction VARCHAR(100) NOT NULL,
 PRIMARY KEY(id));

use EmployeePortal;
CREATE TABLE IF NOT EXISTS EmployeeWorkProjects(
 id VARCHAR(200),
 employeeName VARCHAR(100) NOT NULL
 projectName VARCHAR(100) NOT NULL
 PRIMARY KEY(id));

