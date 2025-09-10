# BeekeeperApp

**Module: Hive and Bee Family Management**  
*A Spring Boot application for managing bee families and hives, including AI-assisted operations.*
*Main goal of this application is to prove opportunity to use local LLM as an App-Manager.*

---

## Overview

BeekeeperApp is designed for beekeepers to manage bee families and hives efficiently.  
It supports storing, retrieving, adding, and deleting bee families and hives, 
with optional AI assistance for querying or managing these data.

---

## Features

- CRUD operations for **Bee Families**:
  - Add new bee family  
  - Retrieve all bee families  
  - Retrieve bee family by ID  
  - Delete bee family by ID  
- CRUD operations for **Hives**:
  - Add new hive  
  - Retrieve all hives  
  - Retrieve hive by ID  
  - Delete hive by ID  
- AI service to process user queries and perform operations in native way  
- In-memory and database-backed repositories for flexibility
- Logging for all operations

---

## Architecture

- **`BeekeeperApp`** – Main Spring Boot application entry point.  
- **Controllers** – Handle REST API requests:
  - `HomeController` – Root endpoint  
  - `BeeFamilyController` – CRUD operations for bee families  
  - `HiveController` – CRUD operations for hives  
  - `AiController` – AI-assisted queries  
- **Services** – Business logic layer:
  - `BeeFamilyServiceImpl` – Manages bee families  
  - `HiveServiceImpl` – Manages hives  
  - `AiServiceImpl` – Processes AI queries  
- **Repositories** – Data persistence layer:
  - `DbBeeFamilyRepo` / `InMemoryBeeFamilyRepo`  
  - `DbHiveRepo` / `InMemoryHiveRepo`  
- **Models** – Data objects:
  - `BeeFamily`, `BeeFamilyType`, `BeeFamilyPower`  
  - `Hive`, `HiveType`, `HiveMaterial`  
- **Exceptions** – Custom exceptions for error handling:  
  - `BeeFamilyAlreadyExistException`  
  - `BeeFamilyNotFoundException`  
  - `HiveAlreadyExistException`
  - `HiveNotFoundException`

---

## Project Structure

```
BeekeeperApp/
│
├─ src/main/java/com/beekeeperApp/
│  ├─ BeekeeperApp.java           # Main Spring Boot entry point
│  ├─ controller/
│  │  ├─ HomeController.java
│  │  ├─ BeeFamilyController.java
│  │  ├─ HiveController.java
│  │  └─ AiController.java
│  ├─ service/
│  │  ├─ beeFamily/
│  │  │  ├─ BeeFamilyService.java
│  │  │  └─ BeeFamilyServiceImpl.java
│  │  ├─ hive/
│  │  │  ├─ HiveService.java
│  │  │  └─ HiveServiceImpl.java
│  │  └─ ai/
│  │     ├─ AiService.java
│  │     └─ AiServiceImpl.java
│  ├─ repository/
│  │  ├─ beeFamily/
│  │  │  ├─ BeeFamilyRepo.java
│  │  │  ├─ DbBeeFamilyRepo.java
│  │  │  └─ InMemoryBeeFamilyRepo.java
│  │  └─ hive/
│  │     ├─ HiveRepo.java
│  │     ├─ DbHiveRepo.java
│  │     └─ InMemoryHiveRepo.java
│  ├─ model/
│  │  ├─ beeFamily/
│  │  │  ├─ BeeFamily.java
│  │  │  ├─ BeeFamilyType.java
│  │  │  └─ BeeFamilyPower.java
│  │  └─ hive/
│  │     ├─ Hive.java
│  │     ├─ HiveType.java
│  │     └─ HiveMaterial.java
│  └─ exception/
│     ├─ BeeFamilyAlreadyExistException.java
│     ├─ BeeFamilyNotFoundException.java
│     ├─ HiveAlreadyExistException.java
│     ├─ HiveNotFoundException.java
│
├─ src/main/resources/
│  ├─ application.properties
│  └─ schema.sql
│
├─ .mvn/                        # Maven wrapper files (optional)
├─ mvnw
├─ mvnw.cmd
└─ pom.xml
```

---

## Usage

### Requirements

- Java 17+
- Maven 3+

### Run the application

#### Using Maven Wrapper (recommended)

```bash
# Linux/macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

#### Using installed Maven

```bash
mvn spring-boot:run
```

### API Endpoints

**Bee Families**

```
GET    /beeFamily          - Retrieve all bee families
GET    /beeFamily/{id}     - Retrieve a bee family by ID
POST   /beeFamily          - Add a new bee family
DELETE /beeFamily/{id}     - Delete a bee family
```

**Hives**

```
GET    /hive               - Retrieve all hives
GET    /hive/{id}          - Retrieve a hive by ID
POST   /hive               - Add a new hive
DELETE /hive/{id}          - Delete a hive
```

**AI Queries**

```
GET    /ai?userInput=...   - Process AI query for hives
```

---

## Example AI Interaction

```
Input: Show all hives
Output: [Hive(id=0, name=Alpha, type=DADANT, material=WOOD, framesPerBody=10), ... ]

Input: Add new hive, name=Beta, type=DADANT, material=PLASTIC, frames=12
Output: Hive(id=1, name=Beta, type=DADANT, material=PLASTIC, framesPerBody=12)

Input: Delete hive with ID 0
Output: Hive was deleted
```

---

## License

Released under the **MIT License**.
