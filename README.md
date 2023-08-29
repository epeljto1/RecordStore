# RecordStore
RecordStore is a project that allows users to manage and organise
music records while securely creating and accessing their accounts. 
The system provides a user-friendly interface for performing 
CRUD operations on this entity. Each record contains a reference to the associated artist, and each artist  
contains a reference to the label they are signed to. Users can only add records by artists that are registered in the database.

**GUI**

_mvn clean install_

_mvn clean javafx:run_

**CLI**

_mvn clean install -P cli-app_

_java -jar target\RecordStore-cli-jar-with-dependencies.jar_

**JAVADOC**

_mvn clean javadoc:aggregate_
