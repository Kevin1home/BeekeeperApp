# BeekeeperApp

**Module: Hive and Bee Family Management**  
*A full-stack application with AI-assisted operations for managing bee families and hives.*

Main goal: demonstrate usage of a local LLM as an App-Manager.

---

## Overview

BeekeeperApp is designed for beekeepers to efficiently manage bee families and hives.  
It provides CRUD operations for hives and bee families and integrates an AI assistant powered by a local LLM (Ollama) for intelligent queries and commands.

The project includes:

- Backend: Spring Boot + Spring AI
- Frontend: React web app
- LLM: Ollama model (local deployment)

---

## Features

- **Bee Families**
  - Add, retrieve, and delete bee families
  - Fetch by ID or view all
- **Hives**
  - Add, retrieve, and delete hives
  - Fetch by ID or view all
- **AI Chat Assistant**
  - Processes user questions about beekeeping
  - Executes commands like adding, retrieving, or deleting hives via LLM
  - Text-to-speech responses
  - Speech-to-text input
- **Flexible Storage**
  - In-memory and database-backed repositories
- **Logging**
  - Full operation logs for backend actions

---

## Architecture

### Backend (Spring Boot)

- **Entry point:** `BeekeeperApp.java`
- **Controllers:**
  - `BeeFamilyController` – CRUD for bee families
  - `HiveController` – CRUD for hives
  - `AiController` – Handles AI queries
- **Services:**
  - `BeeFamilyServiceImpl`, `HiveServiceImpl`, `AiServiceImpl`
- **Repositories:**
  - `DbBeeFamilyRepo` / `InMemoryBeeFamilyRepo`
  - `DbHiveRepo` / `InMemoryHiveRepo`
- **Models:**
  - `BeeFamily`, `BeeFamilyType`, `BeeFamilyPower`
  - `Hive`, `HiveType`, `HiveMaterial`
- **Exceptions:**
  - `BeeFamilyAlreadyExistException`, `BeeFamilyNotFoundException`
  - `HiveAlreadyExistException`, `HiveNotFoundException`

### Frontend (React)

- `HomePage` – Welcome screen
- `AppContent` – Main journal page (hives, bee families, AI chat)
- `AIChat` – Handles AI chat with text-to-speech and speech recognition
- `App` – Routing for HomePage and AppContent
- Uses `fetch` to communicate with backend API (`http://localhost:8080`)

---

## Requirements

- **Backend**
  - Java 17+
  - Maven 3+
- **Frontend**
  - Node.js 18+
  - NPM 8+
  - Browser with Web Speech API support
- **LLM**
  - Ollama installed and running locally

---

## Backend Setup

1. **Install Java 17+ and Maven**
2. **Start the backend**
   - Using Maven Wrapper:

```bash
# Linux/macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

- Using installed Maven:

```bash
mvn spring-boot:run
```

3. **Backend API endpoints**

**Bee Families**

```
GET    /beeFamily
GET    /beeFamily/{id}
POST   /beeFamily
DELETE /beeFamily/{id}
```

**Hives**

```
GET    /hive
GET    /hive/{id}
POST   /hive
DELETE /hive/{id}
```

**AI Queries**

```
GET    /ai?userInput=...
```

---

## Frontend Setup

1. Navigate to `frontend/`
2. Install dependencies:

```bash
npm install
```

3. Start the frontend server:

```bash
npm start
```

- Open in browser: `http://localhost:3000`

- Navigation:
  - `/` – Home page
  - `/app` – Beekeeper journal (hives, bee families, AI chat)

---

## LLM (Ollama) Setup

1. Install Ollama: https://ollama.com/
2. Start Ollama server:

```bash
ollama serve
```

- Now AI queries from frontend or backend will communicate with the local LLM.

---

## Example Interactions

**AI Chat**

```
User: Show all hives
AI: [Hive(id=0, name=Alpha, type=DADANT, material=WOOD, framesPerBody=10), ...]

User: Add new hive, name=Beta, type=DADANT, material=PLASTIC, frames=12
AI: Hive(id=1, name=Beta, type=DADANT, material=PLASTIC, framesPerBody=12)

User: Delete hive with ID 0
AI: Hive was deleted
```

**Frontend CRUD**

- Add hive: Fill form → Click "Add hive"
- Delete bee family: Click "Delete" next to item
- Search by ID: Enter ID → Click "Find"

---

## License

Released under the **MIT License**.
