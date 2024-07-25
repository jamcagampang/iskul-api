# REST API for iSkul Application

This repository is for the codebase of iSkul REST APIs.

Current APIs:
* **User Retrieval (Specific)**: GET: /users/{userId}
* **Classroom Retrieval (All)**: GET: /classrooms/
* **Classroom Retrieval (Specific)**: GET: /classrooms{classroomId}
* **Classroom Creation**: POST: /classrooms{classroomId}
* **Classroom Update**: PUT: /classrooms{classroomId}
* **Classroom Delete**: DELETE /classrooms{classroomId}

## Technology Used
* **Programming Language**: Java 17
* **Back-end Framework**: Spring Boot 3.3.1
* **Build Tool**: Maven
* **Database**: Oracle Autonomous Database 19c (Cloud)
* **Cloud Platform**: AWS (EC2 - For Server Deployment)
* **EC2 Server**: Amazon Linux
* **Domain**: Hostinger (iskul.online)
* **Logs Archival**: S3 Bucket + Shell Scripting + AWS CLI + Crontab

## Spring Boot Modules And Libraries
* **Persistence**: JPA (Hibernate)
* **Validation**: Spring Validation
* **Security**: Spring Security (Disabled)
* **Database Driver**: Oracle Driver
* **Utilities**: Actuator, Spring Boot DevTools, Lombok
* **Unit Testing**: JUnit 5, Mockito, H2 DB

## Tools
* **Startup**: Spring Initializr
* **API Documentation**: Swagger UI
* **API Testing**: Postman
* **AWS Deployment (Manual)**: Putty + WinSCP
* **CI / CD (Partial)**: Git + GitHub Actions + AWS CLI + Shell Scripting

## Best Practices
* Utilizing available tools/technology such as Lombok, Spring Boot DevTools
* Proper logging of application information
* Using web filters to catch request and response
* Creating different application profiles (dev, prod)
* Field validations
* Usage of constants
* Unit Testing (60% Coverage)
* Automation
