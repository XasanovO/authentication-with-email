
---

# JWT-Secured API with Email Confirmation

## Overview

This project is a REST API secured with JWT tokens, featuring user registration, login, and account activation. Technologies used:
- **Spring Boot**
- **Spring Security**
- **JWT tokens**
- **PostgreSQL**
- **JPA (Hibernate)**
- **Validation**
- **Email Sender**

## Features

- User Registration
- Email Confirmation
- Account Activation
- User Login

## Technologies Used

- **Spring Boot**: Application framework
- **Spring Security**: Security framework
- **JWT tokens**: For securing the API
- **PostgreSQL**: Database management system
- **JPA (Hibernate)**: For ORM and database interactions
- **Validation**: For input validation
- **Email Sender**: For sending confirmation emails

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- PostgreSQL

### Running the Application

1. **Clone the repository**:
    ```sh
    git clone https://github.com/XasanovO/authorization-with-email.git
    cd authorization-with-email
    ```

2. **Build the project**:
    ```sh
    mvn clean install
    ```

3. **Run the application**:
    ```sh
    mvn spring-boot:run
    ```

## Usage

### Endpoints

- **Register User**: 
    - **POST** `/api/register`
    - Registers a new user with the following fields:
        - `firstname`: User's first name
        - `lastname`: User's last name
        - `email`: User's email address
        - `password`: User's password
        - `confirmPassword`: Confirmation of the user's password

- **Activate User**: 
    - **GET** `/api/activate?token={token}`
    - The token is sent to the user's email during registration.

- **Login**: 
    - **POST** `/api/login`
    - Provide email and password to receive a JWT token for authenticated requests.

### Example Request

#### Register User

```http
POST /api/register
Content-Type: application/json

{
  "firstname": "John",
  "lastname": "Doe",
  "email": "john.doe@example.com",
  "password": "SecurePassword123",
  "confirmPassword": "SecurePassword123"
}
```

#### Activate User

```http
GET /api/activate?token=some-jwt-token
```

#### Login

```http
POST /api/login
Content-Type: application/json

{
  "email": "john.doe@example.com",
  "password": "SecurePassword123"
}
```

---

