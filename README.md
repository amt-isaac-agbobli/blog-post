# Blog Post API

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

## Part 2: Kubernetes Deployment

### Deployment Configuration

The deployment configuration is defined in the `kubernetes.yaml` file. It includes the following key components:

#### Deployment

- **apiVersion**: `apps/v1`
  - The API version used to create this Deployment.
- **kind**: `Deployment`
  - The kind of Kubernetes object being created.
- **metadata**:
  - **name**: `blog-post-api`
    - The name of the Deployment.
  - **namespace**: `default`
    - The namespace in which the Deployment is created.
- **spec**:
  - **replicas**: `2`
    - The number of pod replicas to be created.
  - **selector**:
    - **matchLabels**:
      - **app**: `blog-post-api`
        - The label selector to identify the pods managed by this Deployment.
  - **template**:
    - **metadata**:
      - **labels**:
        - **app**: `blog-post-api`
          - The labels to be applied to the pods created by this Deployment.
    - **spec**:
      - **containers**:
        - **name**: `blog-post-api`
          - The name of the container.
        - **image**: `blog-post-api:latest`
          - The Docker image to be used for the container.
        - **imagePullPolicy**: `Never`
          - The image pull policy for the container.
        - **ports**:
          - **containerPort**: `8090`
            - The port on which the container listens.
        - **resources**:
          - **limits**:
            - **memory**: `512Mi`
              - The maximum amount of memory the container can use.
            - **cpu**: `1`
              - The maximum amount of CPU the container can use.
        - **env**:
          - **DB_HOST**:
            - **valueFrom**:
              - **configMapKeyRef**:
                - **name**: `db-config`
                - **key**: `host`
          - **DB_NAME**:
            - **valueFrom**:
              - **configMapKeyRef**:
                - **name**: `db-config`
                - **key**: `dbName`
          - **DB_USERNAME**:
            - **valueFrom**:
              - **secretKeyRef**:
                - **name**: `postgres-secrets`
                - **key**: `username`
          - **DB_PASSWORD**:
            - **valueFrom**:
              - **secretKeyRef**:
                - **name**: `postgres-secrets`
                - **key**: `password`

#### Service

- **apiVersion**: `v1`
  - The API version used to create this Service.
- **kind**: `Service`
  - The kind of Kubernetes object being created.
- **metadata**:
  - **name**: `blog-post-api-balancer`
    - The name of the Service.
- **spec**:
  - **selector**:
    - **app**: `blog-post-api`
      - The label selector to identify the pods targeted by this Service.
  - **ports**:
    - **protocol**: `TCP`
      - The protocol used by the Service.
    - **port**: `8090`
      - The port to expose to the outside.
    - **targetPort**: `8090`
      - The port the application is running on in the pods.
  - **type**: `LoadBalancer`
    - The type of Service, which is a LoadBalancer.

### Summary

This configuration sets up a Deployment with 2 replicas of the Blog Post API and exposes it via a LoadBalancer Service on port 8090. The database connection details are managed using ConfigMaps and Secrets.