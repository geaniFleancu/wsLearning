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
 id VARCHAR(200),
 name VARCHAR(100) NOT NULL,
 age INT NOT NULL,
 email VARCHAR(255),
 PRIMARY KEY(id));

use EmployeePortal;
CREATE TABLE IF NOT EXISTS EmployeeWorkInfo(
 id VARCHAR(200),
 departName VARCHAR(100) NOT NULL,
 departFunction VARCHAR(100) NOT NULL,
 PRIMARY KEY(id));

use EmployeePortal;
CREATE TABLE IF NOT EXISTS EmployeeWorkProjects(
 id VARCHAR(200),
 employeeName VARCHAR(100) NOT NULL
 projectName VARCHAR(100) NOT NULL
 PRIMARY KEY(id));


 -- Creates the user
create user 'springuser'@'localhost' identified by 'ThePassword';

-- Gives all the privileges to the new user on the newly created database

grant all on EmployeePortal.* to 'springuser'@'localhost';


Create the application.properties file
Spring Boot gives you defaults on all things, the default in database is H2, so when you want to change this and use any other database you must define the connection attributes in the application.properties file.

In the sources folder, you create a resource file src/main/resources/application.properties

link:complete/src/main/resources/application.properties[]
Here, spring.jpa.hibernate.ddl-auto can be none, update, create, create-drop, refer to the Hibernate documentation for details.

none This is the default for MySQL, no change to the database structure.

update Hibernate changes the database according to the given Entity structures.

create Creates the database every time, but don’t drop it when close.

create-drop Creates the database then drops it when the SessionFactory closes.

We here begin with create because we don’t have the database structure yet. After the first run, we could switch it to update or none according to program requirements. Use update when you want to make some change to the database structure.

The default for H2 and other embedded databases is create-drop, but for others like MySQL is none

It is good security practice that after your database is in production state, you make this none and revoke all privileges from the MySQL user connected to the Spring application, then give him only SELECT, UPDATE, INSERT, DELETE.

This is coming in details in the end of this guide.

Create the @Entity model
src/main/java/hello/User.java

link:complete/src/main/java/hello/User.java[]
This is the entity class which Hibernate will automatically translate into a table.

Create the repository
src/main/java/hello/UserRepository.java

link:complete/src/main/java/hello/UserRepository.java[]
This is the repository interface, this will be automatically implemented by Spring in a bean with the same name with changing case The bean name will be userRepository

Create a new controller for your Spring application
src/main/java/hello/MainController.java

link:complete/src/main/java/hello/MainController.java[]
Note
The above example does not explicitly specify GET vs. PUT, POST, and so forth, because @GetMapping is a shortcut for @RequestMapping(method=GET). @RequestMapping maps all HTTP operations by default. Use @RequestMapping(method=GET) or other shortcut annotations to narrow this mapping.
Make the application executable
Although it is possible to package this service as a traditional WAR file for deployment to an external application server, the simpler approach demonstrated below creates a standalone application. You package everything in a single, executable JAR file, driven by a good old Java main() method. Along the way, you use Spring’s support for embedding the Tomcat servlet container as the HTTP runtime, instead of deploying to an external instance.

src/main/java/hello/Application.java

link:complete/src/main/java/hello/Application.java[]
https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/build_an_executable_jar_subhead.adoc

https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/build_an_executable_jar_with_both.adoc

Logging output is displayed. The service should be up and running within a few seconds.

Test the application
Now that the application is running, you can test it.

Use curl for example. Now you have 2 REST Web Services you can test

localhost:8080/demo/all This gets all data localhost:8080/demo/add This adds one user to the data

$ curl 'localhost:8080/demo/add?name=First&email=someemail@someemailprovider.com'
The reply should be

Saved
$ curl 'localhost:8080/demo/all'
The reply should be

[{"id":1,"name":"First","email":"someemail@someemailprovider.com"}]
Make some security changes
Now when you are on production environment, you may be exposed to SQL injection attacks. A hacker may inject DROP TABLE or any other destructive SQL commands. So as a security practice, make those changes to your database before you expose the application to users.

mysql> revoke all on db_example.* from 'springuser'@'localhost';
This revokes ALL the priviliges from the user associated with the Spring application. Now the Spring application cannot do anything in the database. We don’t want that, so

mysql> grant select, insert, delete, update on db_example.* to 'springuser'@'localhost';
This gives your Spring application only the privileges necessary to make changes to only the data of the database and not the structure (schema).

Now make this change to your src/main/resources/application.properties

spring.jpa.hibernate.ddl-auto=none
This is instead of create which was on the first run for Hibernate to create the tables from your entities.

When you want to make changes on the database, regrant the permissions, change the spring.jpa.hibernate.ddl-auto to update, then re-run your applications, then repeat. Or, better, use a dedicated migration tool such as Flyway or Liquibase.

Summary
Congratulations! You’ve just developed a Spring application which is bound to a MySQL database, Ready for production!

See Also
The following guides may also be helpful:

Accessing Data with JPA

Accessing Data with MongoDB

Accessing data with Gemfire

https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/footer.adoc