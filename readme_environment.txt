Date: Sun. Mar. 8th, 2020

River Meadow Software Java Test Name: Migration Model

Tools Used:

Oracle JDK 11.0.6 2020-01-14 LTS
JetBrains IntelliJ IDEA Community 2019.3.3
Apache Cassandra 3.11.6
Python 2.7
Postman 7.13
Firefox 73.0.1
Maven 3.6.3
Spring Boot 2.0.0.RELEASE 
Spring Data
Spring Web

Difficulties Encountered:

I could not get @Data Lombok working properly...
- ended up coding all the constructors and getters/setters, making the model classes very verbose
I could not get @Tuple Spring Data Cassandra working properly...
- could not use composite objects for Credentials
I could not connect to the Apache Cassandra data store with Spring Boot 2.2.4 or 2.2.5
- was getting JMX errors at runtime, resolved by down grading to 2.0.0
I could not get the Cassandra Keyspace to drop automatically when the program ends
- manually had to use cqlsh > drop keyspace demo_keyspace; before re-running the application


