# Ecommerce_Backend
[![Spring Boot Version](https://img.shields.io/badge/Spring_Boot-3.2.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java Version](https://img.shields.io/badge/Java-17-red.svg)](https://www.oracle.com/java/)

This repositorie is for create one shopee f any products, basic one base of the any shopee

## Table of Contents

- [Branch naming Convention](#branch-naming-conventions)
- [Backend Setup and Dependencies](#backend-setup-and-dependencies)
- [Environment](#environments)
- [Documentation swagger](#documentation-swagger)
- [Seed](#seed)

## Branch Naming Conventions

In our project, we adhere to well-defined branch naming conventions to ensure a consistent and organized workflow. Our branch names are structured to provide clear information about their purpose and context.

[type]-[hu-name]-[plane]-[responsible]

### Naming Format

Our branch names follow the format:

- **`type`**: Represents the type of branch. We use `FT` for Feature branches.

- **`hu-name`**: Stands for the Hypothetical User Story name, which provides context for the branch.

- **`plane`**: Indicates the plane number associated with the task or issue.


- **`responsible`**: Identifies the person or team responsible for the branch.

### Examples

- **Feature Branch Example:**

  - Branch Name: `FT-OVCOM-38-AZ`
  - Purpose: Development of a feature related to task OVCOM-38, focusing on the FRD scope, led by the team responsible for AZ.


By adhering to these branch naming conventions, we enhance clarity and traceability within our development process.

## Backend Setup and Dependencies

To set up and run this project, follow these steps:

1.  **Docker Setup**: Ensure that Docker is installed on your system. Docker will be used to manage the database container for the development environment. If you don't have Docker installed, you can download and install it from the Docker website.

2.  **Clone the Repository**: Clone this project repository to your local machine using Git. You can do this by running the following command in your terminal:
    ```
    git clone https://github.com/VictorMMosqueraG/Ecommerce_Backend.git
    ```
3. **Install JDK 17**
  You must install jdk 17, which to date is the one used in this repository

4. **Install Spring Boot**
 You must install SpringBoot 3.2.4, which to date is the one used in this repository

5. **Database Setup (Development Environment)**

  - Run Database with Docker: In the development environment, the database is managed using Docker. To start the database container, run the following command:

      ```
      docker-compose up -d
      ```

     This command is a one-time operation dedicated to creating the necessary image and container. When you wish to halt the testing, simply terminate the container using the provided command. When needed again, restart the container using the same specific command. This approach ensures a streamlined and convenient database management process.
      
    - View Running Containers: To see a list of running Docker containers, use the following command:

      ```
      docker ps
      ```

    - Start or Stop Containers: To start a stopped container, use:

      ```
      docker start "container-name"
      ```

    - To stop a running container, use:

      ```
      docker stop "container-name"
      ```
    - Open a terminal on your PC to install PSQL in case you don't have it installed
      ```
      brew install postgresql
      ```
For detailed instructions on running the backend in specific environments (development, staging, or production), please consult the dedicated sections within this documentation. Please be aware that instructions for staging and production environments will be included once they become available, and they will not rely on local Docker for deployment.

## Environments

For this project it is using environments `dev`, `stage` and `prod`, copy a data from `application.properties` and create new file with the name `application-dev.properties`

`NOTE:` The file `application-dev,properties` it is change dev for stage or prod example application-stage.properties or application-prod.properties (you can have the 3 files with this names) and the file original application.properties changed value the spring.profiles.active=dev a spring.profile.active=stage or spring.profiles.active=prod

## Documentation Swagger

```
http://localhost:8080/swagger-ui/index.html#/

```

## Seed
It is endpoint for create multiple data in all tables on we database
`NOTE:` not use in  `prod` or `stage`
```
post
http://localhost:8080/api/v1/seed/seed
```