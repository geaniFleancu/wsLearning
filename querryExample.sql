create database EmployeePortal;

use EmployeePortal;
CREATE TABLE IF NOT EXISTS EmployeeInfo(
 id int AUTO_INCREMENT NOT NULL UNIQUE,
 employeeName VARCHAR(100) NOT NULL,
 age INT NOT NULL,
 email VARCHAR(100) NOT NULL,
 PRIMARY KEY(id));

use EmployeePortal;
CREATE TABLE IF NOT EXISTS EmployeeWorkInfo(
 id int AUTO_INCREMENT NOT NULL UNIQUE,
 employeeName VARCHAR(100) NOT NULL,
 departName VARCHAR(100) NOT NULL,
 departFunction VARCHAR(100) NOT NULL,
 PRIMARY KEY(id));

use EmployeePortal;
CREATE TABLE IF NOT EXISTS EmployeeWorkProjects(
 id int AUTO_INCREMENT NOT NULL UNIQUE,
 employeeName VARCHAR(100) NOT NULL,
 projectName VARCHAR(100) NOT NULL,
 PRIMARY KEY(id));

