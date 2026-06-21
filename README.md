# Person Management System

A full-stack Person Management application built using **Spring Boot**, **React**, **TypeScript**, and **PostgreSQL**. The application allows users to manage person records with complete CRUD functionality, server-side pagination, validation, and a modern Material UI interface.

---

## Live Demo

### Frontend

https://person-management-requip.netlify.app/

### Backend API

https://persons-crud-app.onrender.com/api/v1

---

## Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL (Neon)
- Maven
- ModelMapper
- Bean Validation (Jakarta Validation)

### Frontend

- React
- TypeScript
- Material UI
- React Hook Form
- Axios
- React Toastify

### Deployment

- Backend: Render
- Database: Neon PostgreSQL
- Frontend: Netlify

---

## Features

### Person Management

- Create Person
- Update Person
- Delete Person (Soft Delete)
- View Persons

### Validation

- Client-side validation
- Server-side validation
- Duplicate Email validation
- Duplicate Aadhaar validation
- Duplicate PAN validation

### Pagination

- Server-side pagination
- Configurable page size

### UI Features

- Responsive design
- Modern Material UI interface
- Confirmation dialog before delete
- Loading indicators while saving
- Toast notifications
- Form validation messages

---

## Project Structure

```
person-management
│
├── backend
│   ├── src
│   ├── pom.xml
│   └── Dockerfile
│
└── frontend
    ├── src
    ├── package.json
    └── vite.config.ts
```

---

## Backend Setup

### Clone Repository

```bash
git clone <repository-url>
cd person-management/backend
```

### Configure Environment Variables

Create environment variables:

```
DB_URL=jdbc:postgresql://<host>/neondb?sslmode=require
DB_USERNAME=<username>
DB_PASSWORD=<password>
```

### Run

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

Backend runs on

```
http://localhost:8080/api/v1
```

---

## Frontend Setup

Navigate to frontend

```bash
cd frontend
```

Install dependencies

```bash
npm install
```

Create

```
.env.development
```

```
VITE_API_URL=http://localhost:8080/api/v1
```

Run

```bash
npm run dev
```

Frontend runs on

```
http://localhost:5173
```

---

## Environment Variables

### Backend

| Variable    | Description               |
| ----------- | ------------------------- |
| DB_URL      | PostgreSQL connection URL |
| DB_USERNAME | Database username         |
| DB_PASSWORD | Database password         |

### Frontend

| Variable     | Description     |
| ------------ | --------------- |
| VITE_API_URL | Backend API URL |

---

## API Endpoints

| Method | Endpoint        | Description        |
| ------ | --------------- | ------------------ |
| GET    | `/persons`      | Get all persons    |
| GET    | `/persons/{id}` | Get person by ID   |
| POST   | `/persons`      | Create person      |
| PUT    | `/persons/{id}` | Update person      |
| DELETE | `/persons/{id}` | Soft delete person |

---

## Testing

The backend contains unit tests covering:

- Person creation
- Duplicate email validation
- Update functionality
- Soft delete
- Get person by ID
- Controller endpoint testing

Run tests

```bash
mvn test
```

---

## Deployment

### Backend

- Render

### Database

- Neon PostgreSQL

### Frontend

- Netlify

---

## Future Improvements

- Search functionality
- Sorting
- Filtering
- JWT Authentication
- Role-Based Authorization
- Docker Compose
- CI/CD Pipeline
- Swagger/OpenAPI Documentation
- Integration Tests
- Audit Logs

---

## Author

**Satish Balathe**

GitHub: https://github.com/satish6361

LinkedIn: https://linkedin.com/in/satishbalathe

Portfolio: https://satishb.netlify.app
