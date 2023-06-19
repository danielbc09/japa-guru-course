# Hibernate and Spring Data JPA: Beginner to Guru

Course for JPA "Guru" from udemy

## Section 2: Introduction to Spring DATA JPA

    - JPA
        - Is the JPA version of data repository access of the Spring Data family of Prrojects
        - Is Abstraction Lazyer bult on top of JPA
        - Hibernate is an implementation of JPA
    -Hibernate
        - Is a obkect Relational Mapping Tool which implements JPA API
        - ORM 
    -  What is SQL ?
    - JDBC :  Is the API that handle the connectivity to the DB.
    - The Repository Pattern
        - Book Domain Driven Design in 2004.
    - When use JPA Operation:
        - Is very good and fast for object CRUD operations.
        - When you have control of the database schema.
        - When you have multiple operations against a small set of objects.
    - When NOT to Use JPA?
        - is not very good for batch operations.
        - Fine for simple operations, costly for 10's of thousands of operations.
        - SQL is a very powerful language.
        
    - Initializr first course project.(sdjpa-intro)
        - JPA entities.
        - Implementing Hash and equals for the entities.
        - Data Repositories.
    - SQL Logging Properties
    - H2 Database Console.

## Section 3: MySQL

    - Relational database principles.
        - Database Tables
        - Primary key.
        - Table Relations:
            - one to one
            - one to many
            - many to many
        - ACID principle
        - RDBMS have 4 important characteristics:
            - Data definition
            - Data manipulation
            - Data retrieval
            - Administration

## Section 4: Introduction to Testing with Spring Boot

    - Testing with databases.
    - JPA test API:
            - test splice. 
            - Test transaction.
    - Bootstrap in test.

## Section 5: Hibernate with MySQL

    - Configure Hibernate to talk with MySQL.
    - Hibernate DDL Schema generation tool.
        - Hibernate has the ability to reflect on JPA annotated classes to determine necessary database structure.
        - Hibernate schema generation Tool uses reflection on JPA Entities to deermine database structure.
        - default is camel case to snake case.
            - productioNDescription -> PRODUCT_DESCRIPTION.
        - Datatypes are alse defaulted.
        - DB Schema management:
            - You could do it with tools as flyway or liquidbase.
        - Integration test with MySQL
    - Schema initialization.

## Section 5: Using LiquidBase

    - Migrations Overview.
    - Database Migration tools can:
        - Create a new database.
        - Hold history of migrations.
        - Have a reproducible state of the database
        - Help manage changes being applied to numerous database instances.
    - Liquidbase Terminology
    - Liquidbase best practices ¿?.
    - Run liquid base:
            - Command line.
            - Maven.
            - Spring Boot.
    - https://docs.liquibase.com/tools-integrations/maven/home.html
    - Use liquidbase with maven.
    - Organize changelog.
    - https://docs.liquibase.com/tools-integrations/springboot/springboot.html

## Section 6: Using Flyway
    - Flyway is easy to use.
    - Commands
        - Migrate
        - Info
        - Validate
        - Undo
        - Baseline
        - Repair.
    - Configure Spring Boot support for Flyway
    - Alter existing table with Flaway
    - Configuring Flyway

## Others
    -Dspring.profiles.active=local in VM parameters