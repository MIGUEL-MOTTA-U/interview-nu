# Template for a simple REST API with Spring Boot
## [Miguel Motta](https://github.com/MIGUEL-MOTTA-U)
### Prerequisites
- Java 21 or higher
- Maven 3.8 or higher

### Build and Run
1. Clone the repository:
```shell
    git clone https://github.com/MIGUEL-MOTTA-U/rest-app-springboot
```

2. Navigate to the project directory:
```shell
    cd rest-app-springboot
```

3. Build the project using Maven:
```shell
    mvn clean install package
```

4. Run the application:
```shell
    mvn spring-boot:run
```

### Test application:

Test the application by sending a GET request to the health endpoint:
```shell
    curl -Method GET -uri http://localhost:8080/api/example/health
```

### Configuration
The application can be configured using the [`application.properties`](src/main/resources/application.properties). You can set properties such as server port, database connection details, and other application-specific settings.

We use a .env file to inject environment variables into the application.properties file. You can create a .env file in the root directory of the project and define your environment variables there.
For example de params to set up CORS in our endpoints.

You can see an example of a .env file in the [.env.example](.env.example) file:
```properties
CORS_ALLOWED_ORIGINS=http://localhost,http://127.0.0.1:3000
CORS_ALLOWED_METHODS=GET,POST,PUT,DELETE,OPTIONS
CORS_ALLOWED_HEADERS=*
CORS_ALLOW_CREDENTIALS=true

SERVER_PORT=8080
```






