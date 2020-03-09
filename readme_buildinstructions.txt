Date: Sun. Mar. 8th, 2020

River Meadow Software Java Test Name: Migration Model

IDE Build Instructions:

Zip File Provided:  MigrationModel.zip

- unzip file into a workspace folder
- the project contains all the source code and compiled class files

Apache Cassandra Instructions:

- assumes that you have Apache Cassandra running on your localhost (127.0.0.1) machine
  with no authentication on the default port 9042, otherwise you will have to change the
  application.properties file accordingly

Apache Maven Instructions:

- assumes that you have Maven installed in in your PATH
- find the pom.xml file, cd to that directory
- mvn clean
- mvn compile
- mvn package -Dmaven.test.skip=true

this creates target\demo-0.0.1-SNAPSHOT.jar

- cd target (folder where jar file is)

Java Instructions

- assumes that you have Java 11 installed in your PATH

java -jar demo-0.0.1-SNAPSHOT.jar
