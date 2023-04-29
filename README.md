# Customer Information Management application
This is a simple Spring Boot application with the basic setup done to get you started quickly with the development.   

# Getting Started

### Technical Stack
The project uses below technical stack but feel free to add any dependency if required but do mention if you do so.

* Spring Boot version 3.0.6
* Java version 17. [Download here.](https://adoptium.net/download/)
* H2 in memory DB
* Gradle as a build tool

### Setting up the project
* Clone the project in your local machine.
* Import the project in your preferred IDE as a Gradle project and all the dependencies will be auto-downloaded.
* Run the project by going CustomerInfoManagementApplication and *Run As* or *Debug As* a *Java Project*.

# Project Details
* The project contains a Customer entity with details as first name, last name and mobile number.
* Seed data is provided to get you started in schema.sql and data.sql file which is present in src/main/resources.
* schema.sql contains DDL statements and data.sql contains DML statements for providing seed data or for testing purpose.
* Once the spring boot application is started the H2 database can be accessed to query the results using [H2 console](http://localhost:8080/h2-console)
* JDBC url, user name and password to connect to H2 database can be found in application.properties.
* *Important do not use hibernate.ddl-auto property to create tables. Tables should be created using SQL added in schema.sql*
* *Remember the H2 is setup as in memory database so data won't be preserved once application is shutdown. So any data created outside of schema.sql or data.sql will be lost between server restarts.*


# User Stories
Now that project setup and project details are understood lets see what are the new requirements.  
Right now the application maintains customer information in a database but the client wants this information should be available to other third parties wanting to access this information.  
Detailed requirements are mentioned below.

### Requirements
1. Get Customer information over an REST API call
    * Any third-party application should be able to get Customer related information in JSON format.
    * The third-party should be able to request either all customer or filter information using any or all of the parameters like first name, last name and mobile number.
    * First name and Last name should be able to return result which partially match. Example if we have a user with first name Kathy then if we get request using first name filter criteria as "Kat", "aTHY" or "Ath" then Kathy should be returned.
    * For mobile number only exact match should be returned.
    * Do not use native SQL only use JPQL or HQL.
2. Ability to create a new customer over REST API.
    * Third party application should be able to create a customer using REST API.
    * Customer should be only created if the mobile number is not already present in DB.
    * If there is any error while creating the Customer appropriate message should be returned in response.
    * For example lets say if we already have a mobile number in system and request is received for same mobile number then in response we should get 500 status with message "Unable to create Customer. Mobile number already present.".
3. Modify the application to support multiple mobile number for a single customer.
    * Currently application supports only one mobile number but client wants the ability to store multiple mobile number.
    * That means a customer can contain at least one or multiple mobile numbers.
    * Once a user fetches customer using REST API all the mobile numbers should be returned along with Customer details.
4. Modify the application to be able to save a customer with multiple mobile number over REST API
    * Ability so that a **new** customer having one or multiple mobile number can be saved. This is similar to requirement 2 but now that multiple mobile numbers are supported that will change.
5. Ability to delete over REST API
    * There should be a way to delete a customer by using mobile number.
6. Ability to update mobile number for an existing customer.
     * Ability so that for an **existing** customer a mobile number can be added.
     * There should be a way to delete a mobile number from an **existing** customer.

### Acceptance Criteria
There are three acceptance criteria
* Each requirement should be done as a separate working commit. That means after commit one all the Requirement mentioned in 1 should be fulfilled. In commit two all the Requirements mentioned in 1 and 2 should be fulfilled and so on...
* Commit message should contain information as what was implemented or changed.
* Share the postman collection used for testing and put it in src/main/resources. If you have trouble exporting collection a simple text file with REST API URL, request body and sample response will help.
  

The client needs the application as soon as possible so plan accordingly.  
  
If all the requirements were not completed in provided time try to complete as many requirements as possible in the given time frame without sacrificing code quality.