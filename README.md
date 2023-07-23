[![CI-CD](https://github.com/augustomarques/customer-registration/actions/workflows/cicd.yml/badge.svg)](https://github.com/augustomarques/customer-registration/actions/workflows/cicd.yml)

[![Bugs](https://sonarqube.augusto-dev.com/api/project_badges/measure?project=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu&metric=bugs&token=sqb_d627f6302542b0308ffbc513f86b12002b2d4f49)](https://sonarqube.augusto-dev.com/dashboard?id=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu)
[![Code Smells](https://sonarqube.augusto-dev.com/api/project_badges/measure?project=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu&metric=code_smells&token=sqb_d627f6302542b0308ffbc513f86b12002b2d4f49)](https://sonarqube.augusto-dev.com/dashboard?id=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu)
[![Coverage](https://sonarqube.augusto-dev.com/api/project_badges/measure?project=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu&metric=coverage&token=sqb_d627f6302542b0308ffbc513f86b12002b2d4f49)](https://sonarqube.augusto-dev.com/dashboard?id=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu)
[![Duplicated Lines (%)](https://sonarqube.augusto-dev.com/api/project_badges/measure?project=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu&metric=duplicated_lines_density&token=sqb_d627f6302542b0308ffbc513f86b12002b2d4f49)](https://sonarqube.augusto-dev.com/dashboard?id=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu)
[![Lines of Code](https://sonarqube.augusto-dev.com/api/project_badges/measure?project=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu&metric=ncloc&token=sqb_d627f6302542b0308ffbc513f86b12002b2d4f49)](https://sonarqube.augusto-dev.com/dashboard?id=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu)
[![Maintainability Rating](https://sonarqube.augusto-dev.com/api/project_badges/measure?project=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu&metric=sqale_rating&token=sqb_d627f6302542b0308ffbc513f86b12002b2d4f49)](https://sonarqube.augusto-dev.com/dashboard?id=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu)
[![Quality Gate Status](https://sonarqube.augusto-dev.com/api/project_badges/measure?project=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu&metric=alert_status&token=sqb_d627f6302542b0308ffbc513f86b12002b2d4f49)](https://sonarqube.augusto-dev.com/dashboard?id=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu)
[![Reliability Rating](https://sonarqube.augusto-dev.com/api/project_badges/measure?project=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu&metric=reliability_rating&token=sqb_d627f6302542b0308ffbc513f86b12002b2d4f49)](https://sonarqube.augusto-dev.com/dashboard?id=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu)
[![Security Hotspots](https://sonarqube.augusto-dev.com/api/project_badges/measure?project=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu&metric=security_hotspots&token=sqb_d627f6302542b0308ffbc513f86b12002b2d4f49)](https://sonarqube.augusto-dev.com/dashboard?id=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu)
[![Security Rating](https://sonarqube.augusto-dev.com/api/project_badges/measure?project=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu&metric=security_rating&token=sqb_d627f6302542b0308ffbc513f86b12002b2d4f49)](https://sonarqube.augusto-dev.com/dashboard?id=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu)
[![Technical Debt](https://sonarqube.augusto-dev.com/api/project_badges/measure?project=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu&metric=sqale_index&token=sqb_d627f6302542b0308ffbc513f86b12002b2d4f49)](https://sonarqube.augusto-dev.com/dashboard?id=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu)
[![Vulnerabilities](https://sonarqube.augusto-dev.com/api/project_badges/measure?project=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu&metric=vulnerabilities&token=sqb_d627f6302542b0308ffbc513f86b12002b2d4f49)](https://sonarqube.augusto-dev.com/dashboard?id=augustomarques_customer-registration_AYlvpLQWMuLZSEg205yu)

# Customer Registration

#### The "customer-registration" project is a CRUD of customers and bank accounts.

## How to use

The project can be used in two ways: through the application hosted in the cloud or running locally through Docker.
All API documentation has been generated through OpenAPI (Swagger) and can be accessed at swagger-ui/index.html.

## Using cloud-hosted application

The application is hosted in:

```
https://customer-registration.augusto-dev.com
```

The API documentation is available at:

```
https://customer-registration.augusto-dev.com/swagger-ui/index.html
```

## Running the application locally

To run the application it is necessary to have [Docker](https://docs.docker.com/desktop/install/linux-install/)
and [Docker Compose](https://docs.docker.com/compose/install/) installed.

Just run `docker-compose` and the application and all its dependencies will be started.

```
docker-compose up -d
```

To access the API documentation [Swagger](http://localhost:8080/swagger-ui.html) just access the address:

```
http://localhost:8080/swagger-ui.html
```

The metrics of the application are exposed through the [Actuator](http://localhost:8080/actuator)

```
http://localhost:8080/actuator
```

## Language, framework and tools used:

- Kotlin and Spring
    - [Kotlin](https://kotlinlang.org/)
    - [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
    - [SpringBoot](https://spring.io/projects/spring-boot)

- Database
    - [MySQL](https://www.mysql.com/)

- Dependency management
    - [Maven](https://maven.apache.org/)

- Unit and integration tests
    - [JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
    - [Mockk](https://mockk.io/)
    - [Datafaker](https://www.datafaker.net/documentation/getting-started/)
    - [Testcontainer](https://www.testcontainers.org/) - [MySQL](https://www.testcontainers.org/modules/databases/mysql/)

- API documentation
    - [OpenAPI 3 - Swagger](https://swagger.io/specification/)

- Database Versioning
    - [Flyway](https://flywaydb.org/)

- Containerization
    - [Docker](https://docs.docker.com/)
    - [Docker Compose](https://docs.docker.com/compose/)

- Coverage and analysis of static code
    - [Sonarcube](https://www.sonarsource.com/products/sonarqube/)
    - [Jacoco](https://www.jacoco.org/jacoco/)

- CI/CD
    - [Github Actions](https://docs.github.com/en/actions)

- Cloud
    - [Railway](https://railway.app/)

