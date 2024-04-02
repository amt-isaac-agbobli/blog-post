
## Part 1: Spring Boot Security

### Lab 1: Secure a REST API with JWT Authentication

In this lab, I have built a REST API for a social media platform. The API requires users to be authenticated securely before accessing their profiles or posting content. I have implemented JWT authentication in the Spring Boot application.

#### Tasks:

1. **Implement JWT Authentication**: I have implemented JWT (JSON Web Tokens) authentication in the Spring Boot application. This involved generating a JWT token upon successful user authentication.

2. **User Login**: Users can log in with their username and password. Upon successful authentication, they receive a JWT token. This token is used to authenticate subsequent requests from the user.

3. **Secure API Endpoints**: I have secured the API endpoints by requiring a valid JWT token in the authorization header of the request. This ensures that only authenticated users can access these endpoints.

4. **Validate and Decode JWT Tokens**: I have implemented logic within the controllers to validate and decode JWT tokens. This allows me to extract the user information embedded in the token and use it to perform user-specific operations.

The project is now complete and can be run by following these steps:

1. Clone the repository: `git clone <repository-url>`
2. Navigate to the project directory: `cd <project-directory>`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

Please ensure you have Maven and Java installed on your machine before running the project.

For more information on JWT and how it works, you can refer to [JWT.io](https://jwt.io/introduction/).