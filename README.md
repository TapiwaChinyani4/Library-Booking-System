# 📚 Library Booking System

This is a web-based Library Booking System developed using Java (Servlets, JSPs & API), designed to manage book borrowing, returns, and user activity within a library environment.

The system allows users to browse available books, make bookings, and track borrowed items, while administrators can manage records through the backend.

---

## 🛠️ Technologies Used
- Java (Servlets)
- JSP (Java Server Pages)
- API (JSON)
- SQL Server
- HTML/CSS
- JDBC

---

## 📁 Project Structure

The project is organised into the following main folders:

### src/
Contains all backend Java code:
- **Servlets** – Handle user requests and application logic
- **APIs** – Manage communication between components
- **utils** – Includes database connection and helper classes

---

### web/
Contains all frontend and view-related files:
- **JSP files** – User interface pages (login, booking, dashboard, etc.)
- Handles user interaction and displays data from the backend

---

### database/
Contains all database-related files:
- SQL scripts used to create and initialise the database
- Table structures and schema setup

---

### docs/
Contains system documentation:
- User manual explaining how to use the system
- Borrow log and return log documentation

---

## How the System Works
1. User accesses the system through JSP pages (web/)
2. Requests are handled by Servlets (src/)
3. Servlets communicate with the database via JDBC (utils/)
4. Data is stored and retrieved from SQL Server (database/)
5. Results are displayed back to the user via JSP pages

---

## Features
- User login system
- User register and password reset system
- Book borrowing and returning system
- Borrow and return tracking logs
- Database-driven records
- Simple and functional user interface
