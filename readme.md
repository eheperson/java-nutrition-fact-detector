# Nutrition Fact Detector App in JAVA

This Java project simulates a nutrition fact detector system, demonstrating CRUD operations through a simple console application. It utilizes a MySQL database for data persistence.

>This repository created on behalf of *IYTE SEDS519 – Software Designs Pattern* Lecture *2024 Spring* semester project

## Usage (TODO)
Once the application is running, follow the on-screen instructions to interact with the nutrition facts system. 

The menu provides options to add, retrieve, update, and delete nutrition facts, as well as listing all available entries.

## Project structure
```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── app/
│   │   │   │   └── Main.java
│   │   │   ├── dao/
│   │   │   │   ├── INutritionDao.java
│   │   │   │   └── NutritionDaoImpl.java
│   │   │   ├── model/
│   │   │   │   └── Nutrition.java
│   │   │   └── util/
│   │   │       └── DbConnection.java
│   └── resources/
│       └── db/
│           └── schema.sql
├── docker-compose.yml
└── readme.md
```

## Prerequisites
- Java JDK 11 or later
- MySQL Server (Local or Docker-based setup)
- Eclipse IDE (for running within Eclipse)
- Docker and Docker Compose (optional, for containerized MySQL)
- Maven (for command-line execution)

## Preparation
### Database Setup
#### Using Docker
##### To start a MySQL instance using Docker:

```bash
docker run --name mysql-nutrition -e MYSQL_ROOT_PASSWORD=rootpassword -e MYSQL_DATABASE=nutritiondb -e MYSQL_USER=user -e MYSQL_PASSWORD=password -p 3306:3306 -d mysql:8.0
```

##### If you prefer using Docker Compose:

``` bash
docker-compose up -d
```

##### Manually or Remote Database
- Ensure MySQL Server is installed and running on your system or accessible remotely.
- Create a database named nutritiondb.
- Execute the SQL script found in src/resources/schema.sql to set up the required table structure.

#### Database Configuration
- Locate the config.properties file in `src/main/resources`.
- Update the `db.url`, `db.user`, and `db.password` properties to match your database connection details.

## Project Setup
### In Eclipse
#### Import the Project:
1. Open Eclipse and select File > Import.
2. Choose Existing Projects into Workspace and navigate to the project directory.
3. Select the project and click Finish.

#### Run the Application:
1. Right-click on Main.java located in src/main/java/app.
2. Select Run As > Java Application.

### From the Command Line
#### Build the Project:
> Ensure Maven is installed on your system.

1. Navigate to the project root directory.
2. Run the following command to compile the project:
``` bash 
mvn clean compile
```

#### Run the Application:
After building, execute the following command to run the application:
``` bash
mvn exec:java -Dexec.mainClass="app.Main"
```

## Troubleshooting
### Database Connection Issues: 
- Ensure the MySQL service is running and accessible. 
- If using Docker, verify the container is up. 
- For manual setups or remote databases, ensure the `config.properties` file reflects the correct connection details.

### Compilation Errors: 
- Confirm you're using Java JDK 11 or later and Maven is correctly set up on your system.

### Eclipse Issues: 
- Ensure you have imported the project correctly and set up the build path to include the necessary JDK.
