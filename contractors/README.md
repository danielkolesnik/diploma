# Contract Students Management Service

### REST API endpoints:

// todo: add endpoints list

### How to start database server with Docker
##### Steps for macOS/Ubuntu
1. Setup docker([macOS](https://docs.docker.com/docker-for-mac/install) / [Ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/))
2. Pull image with database by executing following command:
    ```
    docker pull olegbarmin/diploma:DEVELOPMENT
    ```
3. Find ID of downloaded image by executing:
    ```
    docker image ls
    ```
4. Run a container from the image on 1433 port.
    ```
    docker run  -p 1433:1433 <IMAGE_ID>
    ```

### How to setup development environment
#### Requirements:
- Git (https://www.atlassian.com/git/tutorials/install-git#windows)
- JDK 1.8 or higher (https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- IntelliJ IDEA (https://www.jetbrains.com/idea/download/)

#### Steps to import project:
1. Clone the repository 
``` 
git clone git@github.com:olegbarmin/diploma.git 
``` 
2. Open IDEA
3. Select 'Import Project' option
![project](tutorial/resources/import_project.jpg)
4. Select `<project_root>/diploma/contractors` folder
5. Select 'Import project from external model' -> 'Gradle' and press 'Next'
![import](tutorial/resources/wrapper.jpg)
6. Select 'Use gradle 'wrapper' task configuration' option and press 'Finish'
![wrapper](tutorial/resources/wrapper.jpg)
7. Wait for the 'Sync' task to finish (it may take about 3 minutes)
![sync](tutorial/resources/sync.jpg)
8. Wait for the indexing to finish:
![indexing](tutorial/resources/indexing.jpg)
9. Verify that you project view looks the same:
![project_structure](tutorial/resources/project_strucute.jpg)
10. Run tests, by right clicking on 'java' folder in 'test' package:
![run_tests](tutorial/resources/run_tests.jpg)
11. Verify tests done successfully:
![tests](tutorial/resources/testing_done.jpg)
10. Done.

### How to run development server
#### Requirements:
- [Node.js](https://nodejs.org/)

#### Steps to run the project:
1. Start the Docker with database server, running following command:

    ``docker run  -p 1433:1433 <IMAGE_ID>``
2. Assemble the server module:

    ``./gradlew :server:assemble``
3. Assemble the client-server module with one of clients applications (```smo``` | ```oko```):
   
   ``./gradlew :client-server:assemble -Pclient=<CLIENT_NAME>``
4. Launch the server:

    ``java -jar server/build/libs/server-1.0-SNAPSHOT.jar``
5. Launch the client:

    ``java -jar client-server/build/libs/client-server-<CLIENT_NAME>-1.0-SNAPSHOT.jar``