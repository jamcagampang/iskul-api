# REST API for iSkul Application

This repository is for the codebase of iSkul REST APIs.

Current APIs:

**Accounts API**:
* **User Retrieval (Specific)**: GET: /users/{userId}
* Documentation: Ongoing

**Classroom API**:
* **Classroom Retrieval (All)**: GET: /classrooms/
* **Classroom Retrieval (Specific)**: GET: /classrooms{classroomId}
* **Classroom Creation**: POST: /classrooms{classroomId}
* **Classroom Update**: PUT: /classrooms{classroomId}
* **Classroom Delete**: DELETE /classrooms{classroomId}
* Documentation: http://iskul-api.s3-website-ap-southeast-1.amazonaws.com/classroom/

## Technology Used
* **Programming Language**: Java 17
* **Back-end Framework**: Spring Boot 3.3.1
* **Build Tool**: Maven
* **Database**: Oracle Autonomous Database 19c (Cloud)
![image](https://github.com/user-attachments/assets/8dad5e90-2c10-4398-9fbd-880f0a7a7577)
* **Cloud Platform**: AWS (EC2 - Amazon Linux)
![image](https://github.com/user-attachments/assets/d711289b-4e4d-4b5e-a1e3-e622bcb15b8f)
* **Logs Archival**: S3 Bucket + Shell Scripting + AWS CLI + Crontab
![image](https://github.com/user-attachments/assets/b5997dec-eacd-4763-b286-4dabeb45287a)

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
