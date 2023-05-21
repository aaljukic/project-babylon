# Card Holder Management System

Card Holder Management System is a simple application developed using Spring Boot and PostgreSQL database system. Project's code name is *Babylon*. Ancient Babylon is often considered the place where basic banking practices were developed, so hence, that name.

The application utilizes a layered architecture. It's a CRUD (Create, Read, Update, Delete) application that manages the registration of new card holders, enables search, update, and soft delete for specific user. Deletion is implemented as a 'soft delete', which means that instead of permanently removing user records, they are merely marked as **INACTIVE**.

Another feature of the Card Holder Management System is ability to generate personalized files based on each user's specific database information. The system can generate multiple files for each user, with only one file active at any given time if that user is indeed **ACTIVE**. If **ACTIVE** file exist and user is deleted, current **ACTIVE** file will be marked as **INACTIVE**.

> files are stored in project root. ***services/babylon-card-holder-service/generated_cards***

PostgreSQL database image is defined in Dockerfile in db directory. There are two scripts:
- - 01_init.sql: creates app user (*required for Spring Boot application) and creates schema.
- - 02_data.sql: populates database with inital values for ***status*** and ***card_holder*** tables

## Prerequisites

**Important:** Before you begin, ensure you have met the following requirements:
- You have installed Docker and it is up and running. [Install Docker](https://docs.docker.com/get-docker/)
- You have installed Docker-Compose. [Install Docker-Compose](https://docs.docker.com/compose/install/)
- If you want to connect to Database, credentials for superuser are in ***.env*** file

## Running the Project (***prod*** or ***dev*** mode)

To run the project, follow these steps:

1. Clone the repository to your local machine:

```bash
git clone https://github.com/aaljukic/project-babylon.git
```

2. Change to the project directory:

```bash
cd project-babylon
```

3. Run eather ***prod*** or ***dev*** Docker Compose file:

- > ***prod*** configuration will start database. After that Multi-stage build will start and it will build, package and run JAR file when container starts. Maven and Eclipse-Temurin Docker images are defined in Dockerfile of project's root **services/babylon-card-holder-service/Dockerfile**
- > ***dev*** configuration will only start database so it is possible to develop the application.

```bash
docker-compose -f docker-compose.prod.yml up
```

```bash
docker-compose -f docker-compose.dev.yml up
```

The application should now be up and running at http://localhost:8080.  

## Documentation

API documentation can be accessed via Swagger UI at http://localhost:8080/swagger-ui/index.html. You can test the endpoints directly from the UI or use a tool like Postman.

## Postman Collection
For your convenience, a Postman collection has been provided in the docs directory of this repository. This collection contains pre-configured requests for all of the API endpoints.

## Clean run (when database scripts are modified)

> Sometimes we want to modify schema while we are developing. In that case we need to rebuild everything from scratch.

if you want to start again and clean run entire setup, follow these steps:

1. Stop and remove all the containers defined in docker-compose.dev.yml file
```sh
docker-compose -f docker-compose.dev.yml down
```
2. Remove the volume named project-babylon_db_data
```sh
docker volume rm project-babylon_db_data  
```
3. Rebuild everything from scratch.
```sh
docker-compose -f docker-compose.dev.yml build --no-cache
```
4. Start and run the entire app as defined in the docker-compose.dev.yml file
```sh
docker-compose -f docker-compose.dev.yml up
```

> same applies for *docker-compose.prod.yml* file.

## License
This project is licensed under the MIT License - see the LICENSE file for details.


## Contact
If you want to contact me you can reach me at [LinkedIn](https://www.linkedin.com/in/adis-aljuki%C4%87-66072b188/)