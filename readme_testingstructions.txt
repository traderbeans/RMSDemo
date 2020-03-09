Date: Sun. Mar. 8th, 2020

River Meadow Software Java Test Name: Migration Model

REST API Testing can be done with a variety of tools:

- Postman
- SoapUI
- Web Browsers - Firefox
- CL Tools - curl
- Java Tests

Some REST CRUD examples using Postman:

Using HTTP CRUD (Create - POST, Read - GET, Update - PUT, Delete - DELETE)

Read, Get All:

GET http://localhost:8080/api/v1/source
GET http://localhost:8080/api/v1/target
GET http://localhost:8080/api/v1/migration

Read, Get Start Migration

GET http://localhost:8080/api/v1/migration/start/a9206b4f-fcb4-4425-b5f4-1ba3f801dce4

Read, Get Migration State

GET http://localhost:8080/api/v1/migration/state/a9206b4f-fcb4-4425-b5f4-1ba3f801dce4

Read, Get By Id (UUID):

GET http://localhost:8080/api/v1/source/519d5cf3-bd8a-4455-a159-824a9b43c154
GET http://localhost:8080/api/v1/target/519d5cf3-bd8a-4455-a159-824a9b43c154
GET http://localhost:8080/api/v1/migration/519d5cf3-bd8a-4455-a159-824a9b43c154

Delete, Delete By Id (UUID);

DELETE http://localhost:8080/api/v1/source/519d5cf3-bd8a-4455-a159-824a9b43c154
DELETE http://localhost:8080/api/v1/target/519d5cf3-bd8a-4455-a159-824a9b43c154
DELETE http://localhost:8080/api/v1/migration/519d5cf3-bd8a-4455-a159-824a9b43c154

Create, Post JSON RequestBody

POST http://localhost:8080/api/v1/source/

raw JSON below:

{"ipAddress":"10.2.2.24",
 "username":"uidThree",
 "password":"pwdThree",
 "domain":"three.org",
 "storage":{"c:":340,"d:":350,"e:":360}}

Update, Put JSON RequestBody

PUT http://localhost:8080/api/v1/source/519d5cf3-bd8a-4455-a159-824a9b43c154

raw JSON below:

{"ipAddress":"10.2.2.38",
 "username":"uidFour",
 "password":"pwdFour",
 "domain":"three.org",
 "storage":{"c:":840,"d:":850,"e:":860}}