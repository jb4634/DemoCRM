# DemoCRM
A demo CRM project with Spring Boot and Vaadin 12.

### Requirements
* java SDK 1.8
* IDE (IntelliJ, Spring Tools,...) or mvn (Maven)
* docker

### Configuration
* Open terminal
* Start docker container with mysql database and create the database:
  * $ docker run --name mysql1 -e MYSQL_ROOT_PASSWORD=1234 -d -p 3306:3306 mysql:latest
  * $ docker exec -it mysql1 mysql -uroot -p
  * mysql> $ create database db_podjetje
* Clone this repository
* [Optional] Configuration for connecting to database can be changed in ./src/main/resources/application.properties
* [Optional] When starting the application for the first time, on an empty database,
             we can fill the database with dummy data with uncommenting the line in ./src/main/java/com/podjetje/democrm/DemocrmApplication:
             * //  fillTheDatabase(conclusionTypeRepository, customerRepository, meetingRepository, conclusionRepository);

### Building
With IDE:
* Open this project
* Edit configuration and set the java SDK
* Run DemocrmApplication 
  
[Optional] With Maven:
* Navigate to the project
* run commands:
  * $ mvn clean install
  * $ java -jar target/democrm-0.0.1-SNAPSHOT.jar
  * [Optional] Possible to run with plugin: $ mvn spring-boot:run
    
### Using the application
The application is accessible on "localhost:8080"

Functionalities:
* "Filter" filters Customers by first and/or last name
* "Clear filter" clears current filters
* "New customer" opens a form for creating a new customer
* Selecting a customer from the list opens a form for updating selected customer
* "Delete" deletes selected customer from the database
* "New Meeting" opens a form for creating a new customer
* Selecting a meeting from the list opens a form for updating selected customer

Not yet implemented on the frontend:
* CRUD for Conclusions
* Deleting a meeting
* List of Conclusions
