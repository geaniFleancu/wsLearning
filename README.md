# wsLearning

https://github.com/spring-guides/gs-accessing-data-mysql


pom.xml dependency

<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.1.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- JPA Data (We are going to use Repositories, Entities, Hibernate, etc...) -->

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Use MySQL Connector-J -->

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

Create the database

create database EmployeePortal;


use EmployeePortal;
CREATE TABLE IF NOT EXISTS EmployeeInfo(
 id VARCHAR(200), dept varchar(20),
 name VARCHAR(100) NOT NULL,
 age INT NOT NULL,
 PRIMARY KEY(id));

use EmployeePortal;
CREATE TABLE IF NOT EXISTS EmployeeWorkInfo(
 id VARCHAR(200), dept varchar(20),
 departName VARCHAR(100) NOT NULL,
 departFunction VARCHAR(100) NOT NULL,
 PRIMARY KEY(id));

use EmployeePortal;
CREATE TABLE IF NOT EXISTS EmployeeWorkProjects(
 id VARCHAR(200), dept varchar(20),
 employeeName VARCHAR(100) NOT NULL
 projectName VARCHAR(100) NOT NULL
 PRIMARY KEY(id));


 -- Creates the user
create user 'springuser'@'localhost' identified by 'ThePassword';

-- Gives all the privileges to the new user on the newly created database

mysql> grant all on EmployeePortal.* to 'springuser'@'localhost';