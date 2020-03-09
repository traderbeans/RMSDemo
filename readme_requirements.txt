Date: Sun. Mar. 8th, 2020

River Meadow Software Java Test Name: Migration Model

Please implement the following task using Java. You can use any available Java based framework to help here. Implement proper packaging and provide instructions on how to setup, compile and run your solution. You can implement this in Mac, Linux or Windows platform

Create the model classes:
Define class Volume that contains the following fields

Mount point  (c:\)
Total size of the volume
Define class Workload that contains the following fields:

IP address
Credentials (username, password, domain)
Storage that contains array of volumes
Constraints:

Username, password, IP cannot not be Null
IP cannot change
You cannot have more than one source with the same IP
Define class TargetCloud that contains the following fields:

Cloud type: aws, azure, vsphere, vcloud - no other types are allowed
Cloud credentials of type Credentials
Target object of type Workload
Define class Migration that contains the following

Selected Mount Points (array of strings)
Source field of type Workload
TargetCloud field of type TargetCloud
Migration state: not started, running, error, success
Implement run method - run method should sleep for 5 min (simulate running migration) copy source object to the target field of TargetCloud and target should only have volumes that are selected in the migration. For example, if source has: C:\ D: and E:\ and only C: was selected, target should only have C:\
Implement business logic to not allow running migrations when volume C:\ is not included in the selected mount points list
Define Cassandra tables for storing all the classes
Implement persistent layer for storing the data
Implement REST API server that exposes the model
It should allow to add/remove/modify each source, but not the IP address of the source
It should allow to add/remove/modify target
It should allow to add/remove/modify migration
It should allow to start migration (by calling run)
It should allow to see if migration is finished
It should not be allowed to change fields that cannot be changed
Implement test harness (curl?) for verification that REST API works as expected, using any language. Java is not required in this part of the exercise.
Create unit tests where applicable