# Telecom RESTful Backend API
## Tech Stack:
* Java 11
* Gradle
* SpringBoot
* Spring Web
* Spring Data JPA
* Lombok
* H2 in-memory DB
* Flyway
* Spring Boot Test
* JUnit
* Mockito
* MockMvc
* Swagger API

### This is the SpringBoot based RESTful API. It supports the below functions:

* Application support H2 in-memory DB.
* DB data is pre-populated by Flyway, SQL scripts can be found here
![](https://i.imgur.com/zxH0Z9j.png)

# How to run this application

There are multiple ways to run application

### 1. start from IntelliJ IDEA
* the default application.yaml will run profile h2


* For Lombok, should enalble annotation processing in IntelliJ IDEA
![](https://i.imgur.com/HWwNlVc.png)

* click Run 
![](https://i.imgur.com/akHDeqQ.png)

### 2. start with H2 in-memory DB
* How to install gradle: https://gradle.org/install/
* Run the command to build the whole project: **gradle clean build**
* start the application with H2 in-mem DB: **java -jar ./build/libs/telecom-demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2**

## Implementation Thought Explanation

There is a relation:
 (1 customer: N phone numbers)
### get all phone numbers:
For getting all phone numbers, it means the data would be very large, so I use pagenation for this API, and only find activated phone numbers

### get all phone numbers of a single customer
Only find activated phone numbers of this customer
### activate a phone number
activate this customer's phone number

# Swagger API definition
if you start the application successful, the Swagger API is here:
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

![](https://i.imgur.com/eVmPm2L.png)


## /api/v1/telecom/phone/activate/3/0491970156
![](https://i.imgur.com/EQq8WrV.png)

## /api/v1/telecom/phone/1
![](https://i.imgur.com/kbWWtFU.png)

## /api/v1/telecom/phone/all?page=0&size=5
![](https://i.imgur.com/cbOi5mZ.png)

![](https://i.imgur.com/5SHX89G.png)

# Unit Test & Integration Test
you can find test here:
![](https://i.imgur.com/hS2jme3.png)

### the test coverage
![](https://i.imgur.com/YFH7HSD.png)

# That's all! hope you'll like it!
