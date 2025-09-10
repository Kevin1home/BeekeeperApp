# Beekeeper Journal (Frontend)

**Module: React Application for Managing Hives and Bee Families**  
*A web-based React app for interacting with the Beekeeper backend API.*

---

## Overview

This frontend application provides an interactive interface for beekeepers to:

- View, add, and delete hives
- View, add, and delete bee families
- Chat with an AI assistant for beekeeping advice and managing of app

The app communicates with a backend API running at `http://localhost:8080`.

---

## Features

- **Hives Management**
  - View all hives
  - Add a new hive
  - Delete a hive
  - Search a hive by ID
- **Bee Families Management**
  - View all bee families
  - Add a new bee family
  - Delete a bee family
  - Search a bee family by ID
- **AI Chat**
  - Send text input to an AI model
  - Receive AI responses
  - Text-to-speech voice responses
  - Speech-to-text input
- **Responsive Navigation**
  - Home page
  - Journal page

---

## Usage

### Installation

```bash
# Install dependencies
npm install

# Start the development server
npm start
```

The app will run at http://localhost:3000.

### Navigation

- `/` – Home page  
- `/app` – Beekeeper journal with hives, bee families, and AI chat  

---

## Example Interactions

- **Add Hive**
```
Hive Name: Sunset Hive
Material: WOOD
Type: DADANT
Frames per Body: 10
```
Click "Add hive" → Hive is added and displayed in the list.

- **AI Chat**
```
User: How to strengthen a weak bee family?
AI: To strengthen your weak bee family, ...
```
Speech-to-text or manual input works for user messages.

- **Search Hive by ID**
```
Input: 3
Click "Find" → Hive details displayed if exists
```

---

## Project Structure

- `HomePage` – Displays the welcome screen with a button to go to the journal.  
- `AppContent` – Main journal page: hives, bee families, AI chat.  
- `AIChat` – Handles AI chat interactions with text-to-speech and speech recognition.  
- `App` – Defines routes for HomePage and AppContent.

---

## Requirements

- Node.js 18+  
- NPM 8+  
- Browser with Web Speech API support for text-to-speech and speech recognition  
- Backend API running at `http://localhost:8080`  

---

## License

Released under the **MIT License**.
