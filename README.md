#JWT-Secured API with Email Confirmation
Overview
This project is a REST API secured with JWT tokens, featuring user registration, login, and account activation. The API sends an email confirmation link during registration. When the link is clicked, the user's account is activated. Technologies used in this project include:

Spring Boot
Spring Security
JWT tokens
PostgreSQL
JPA (Hibernate)
Validation
Email Sender
Features
User Registration: Users submit their information via the registration endpoint.
Email Confirmation: A token is created from the user's details and sent to their email.
Account Activation: The user clicks the link in the email, which activates their account by verifying the token and saving the user details in the database.
User Login: Activated users can log in to access secured endpoints.
Technologies Used
Spring Boot: Application framework.
Spring Security: Security framework.
JWT tokens: For securing the API.
PostgreSQL: Database management system.
JPA (Hibernate): For ORM and database interactions.
Validation: For input validation.
Email Sender: For sending confirmation emails.
Getting Started
Prerequisites
Java 11 or higher
Maven
PostgreSQL
Configuration
Update the application.properties file with your specific configuration:

properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD
spring.datasource.driverClassName=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true

# JWT Configuration
security.jwt.secret-key=3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b
security.jwt.expiration-time=604800000

# Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=YOUR_GMAIL
spring.mail.password=YOUR_APP_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
Running the Application
Clone the repository:

sh
git clone [https://github.com/yourusername/your-repository.git](https://github.com/XasanovO/authorization-with-emai)
cd your-repository
Update application.properties: Replace the placeholder values with your actual PostgreSQL and email credentials.

Build the project:

sh
mvn clean install
Run the application:

sh
mvn spring-boot:run
Usage
Endpoints
Register User:

POST /api/register
Send user details (name, email, password, etc.) in the request body.
Activate User:

GET /api/activate?token={token}
The token is sent to the user's email during registration.
Login:

# POST /api/login
Provide email and password to receive a JWT token for authenticated requests.
Example Request
Register User
http
POST /api/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "SecurePassword123"
}
Activate User
http
Копировать код
#GET /api/activate?token=some-jwt-token
Login
http
#POST /api/login
Content-Type: application/json

{
  "email": "john.doe@example.com",
  "password": "SecurePassword123"
}
Notes
Ensure your email credentials in the application.properties are correct to enable email sending.
The database configuration should be adjusted according to your local setup.
The JWT secret key and expiration time should be kept secure and configured appropriately.
Feel free to modify the content as per your specific needs.
