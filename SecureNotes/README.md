# Secure Notes API

A Spring Boot REST API where users can register, login, and manage personal notes.

## Features

- User Registration and Login
- Session-based Authentication using Spring Security
- CRUD operations for notes
- H2 in-memory database

## Running the Application

```bash
mvn spring-boot:run
```

## Access

- Register: `http://localhost:8080/register`
- Login: `http://localhost:8080/login`
- Notes API: `/api/notes`
- H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`)

## Endpoints

- `POST /register` - Register a new user
- `POST /login` - Login
- `GET /api/notes` - List notes
- `POST /api/notes` - Create note
- `DELETE /api/notes/{id}` - Delete note

