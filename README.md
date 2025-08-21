# StudentTaskTracker

Student Task Tracker is a web application designed to help schools and teachers manage student tasks efficiently. It provides dashboards for students and admins, task assignment and tracking, authentication, and profile management.

## Features
- Admin and Student dashboards
- Task assignment and tracking
- Authentication (Sign Up, Login, Forgot Password)
- Profile management
- Responsive UI with separate CSS/JS for admin, student, and landing pages
- Error handling and access control

## Technologies Used
- Java (Spring Boot)
- Maven
- HTML, CSS, JavaScript
- Thymeleaf (for templates)

## Project Structure
```
StudentTaskTracker/
  student details (2)/student details/student-details/
    src/
      main/
        java/com/student/details/        # Java source code
        resources/
          static/                       # CSS/JS files
          templates/                    # HTML templates
      test/java/com/student/details/    # Test code
    pom.xml                             # Maven configuration
```

## Prerequisites
- Java 17 or above
- Maven 3.6+

## Setup Instructions
1. **Clone the repository**
   ```powershell
   git clone https://github.com/manojkumarsharma511/StudentTaskTracker.git
   cd StudentTaskTracker/student details (2)/student details/student-details
   ```
2. **Build the project**
   ```powershell
   .\mvnw.cmd clean install
   ```
   Or, if Maven is installed globally:
   ```powershell
   mvn clean install
   ```
3. **Run the application**
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```
   Or:
   ```powershell
   mvn spring-boot:run
   ```
4. **Access the application**
   - Open your browser and go to: [http://localhost:8080](http://localhost:8080)

## Usage
- **Admin Login:** Use the admin credentials to access the admin dashboard and manage tasks.
- **Student Login:** Students can view and update their assigned tasks.
- **Sign Up/Forgot Password:** Use the respective pages for account creation and password recovery.

## Customization
- Update `src/main/resources/application.properties` for database and server configuration.
- Modify static files in `src/main/resources/static/` for custom styles and scripts.
- Edit templates in `src/main/resources/templates/` for UI changes.

## Troubleshooting
- Ensure Java and Maven are installed and configured in your PATH.
- Check the logs for errors during build or runtime.
- For port issues, update the server port in `application.properties`.

## License
This project is for educational purposes.
