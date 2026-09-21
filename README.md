<a id="top"></a>

# Academic System

![Java](https://img.shields.io/badge/Java-25-orange)
![Maven](https://img.shields.io/badge/Maven-3.9-blue)
![Docker](https://img.shields.io/badge/Docker-enabled-blue)
![GitHub%20Actions](https://img.shields.io/badge/GitHub%20Actions-enabled-blue)
![Status](https://img.shields.io/badge/status-in%20development-yellow)

Academic System is a Java application developed as a semester assignment for the Object-Oriented Programming course taught by Rodrigo Martins Pagliares at the Computer Science Department of Universidade Federal de Alfenas (UNIFAL-MG).

The system supports academic class and assessment management while incrementally exercising object-oriented programming, software engineering, testing, logging, security, persistence, containerization, GUI development, and CI/CD practices.

## About This Fork

This repository is a fork of
[`pagliares/academic-system-semester-assignment-2026`](https://github.com/pagliares/academic-system-semester-assignment-2026),
the starter repository for the course. Working on a fork of it was the format required for the
assignment, so the fork relationship is intentional and is kept on purpose.

The starter provided a Maven skeleton: `pom.xml`, `.gitignore`, `LICENSE`, a README stub and a
placeholder `Main` class — ten files in total.

Everything else in this repository was written by Gabriel Henrique: 59 application classes,
14 test classes, the `Dockerfile`, and the four GitHub Actions workflows (CI, PR validation,
Docker publish and release), plus changes to `pom.xml` and `.gitignore`. That is 88 files and
about 3,700 lines added across 13 commits on top of the starter.

The domain model, the layered architecture, the RBAC security model, the persistence
implementations (TXT, JSON, XML), the report generators, the JavaFX interface and the test suite
described below are all part of that work.

---

<a id="table-of-contents"></a>

## Table of Contents

- [Overview](#overview)
- [Project Goals](#project-goals)
- [Main Concepts Practiced](#main-concepts-practiced)
- [Technologies](#technologies)
- [Architecture](#architecture)
- [Security Model](#security-model)
- [Repository Structure](#repository-structure)
- [How to Run](#how-to-run)
- [Domain Model](#domain-model)
- [Persistence Evolution](#persistence-evolution)
- [Reporting Features](#reporting-features)
- [Branch Protection](#branch-protection)
- [User Story Roadmap](#user-story-roadmap)
- [Implemented User Stories](#implemented-user-stories)
- [Future Improvements](#future-improvements)
- [Educational Purpose](#educational-purpose)
- [License](#license)


### User Story Groups

- [Academic Features](#academic-features)
- [Persistence Features](#persistence-features)
- [Security Features](#security-features)
- [Validation and Exception Handling](#validation-and-exception-handling)
- [Architecture and Refactoring](#architecture-and-refactoring)
- [Docker and Deployment](#docker-and-deployment)
- [Testing Infrastructure and Automated Tests](#testing-infrastructure-and-automated-tests)
- [Logging and Auditing](#logging-and-auditing)
- [Graphical User Interface (JavaFX)](#graphical-user-interface-javafx)
- [CI/CD and Automation](#cicd-and-automation)

---

<a id="overview"></a>

## Overview

The Academic System allows professors and administrators to manage classes and assessments through a command line interface and an evolving JavaFX graphical interface.

The project evolved incrementally through Agile-inspired user stories. Each user story introduces or refines a specific feature, architectural decision, object-oriented concept, persistence mechanism, security concern, testing practice, or deployment capability.

The system currently supports:

- Academic class registration
- Assessment registration
- TXT, XML, and JSON persistence
- Configurable persistence strategies
- Authentication and authorization
- Role-Based Access Control (RBAC)
- Session management and logout
- Role-specific command line menus
- Domain validation
- Exception handling
- Report generation
- Application logging and audit logging
- Automated tests with JUnit Jupiter and Mockito
- Dockerized execution
- JavaFX screens
- GitHub Actions workflows
- Pull request validation and branch protection

[↑ Back to top](#top)


<a id="project-goals"></a>

## Project Goals

The main educational goals of the project are:

- Practice object-oriented programming concepts
- Exercise SOLID principles
- Apply software engineering concepts incrementally
- Simulate Agile software evolution through user stories
- Practice persistence strategies
- Explore layered architecture concepts
- Implement authentication and authorization mechanisms
- Apply validation and exception handling techniques
- Improve maintainability through refactoring
- Develop automated testing skills
- Explore application logging and auditing practices
- Explore deployment and containerization strategies
- Introduce GUI development with JavaFX
- Introduce CI/CD automation with GitHub Actions

[↑ Back to top](#top)


<a id="main-concepts-practiced"></a>

## Main Concepts Practiced

- Abstraction
- Inheritance
- Polymorphism
- Encapsulation
- Association between objects
- Object identity
- Equality with equals and hashCode
- Singleton Pattern
- Repository Pattern
- Strategy Pattern
- Authentication and Authorization
- Role-Based Access Control (RBAC)
- Session lifecycle management
- Exception hierarchies
- Jakarta Bean Validation
- MVC-inspired separation
- Controller layer
- Layered architecture
- XML persistence
- JSON persistence
- TXT persistence
- Report generation
- Command line interaction
- Dynamic navigation flow
- Role-based menu rendering
- JavaFX GUI development
- Lombok
- Automated testing
- JUnit Jupiter
- Mockito
- Application logging
- Audit logging
- Docker
- GitHub Actions
- CI/CD automation

[↑ Back to top](#top)


<a id="technologies"></a>

## Technologies

| Technology | Purpose |
|------------|---------|
| Java SE 25 | Main programming language |
| Maven | Dependency management and build automation |
| Lombok | Boilerplate code reduction |
| Jakarta Bean Validation | Domain validation |
| JUnit Jupiter | Automated unit testing |
| Mockito | Mocking framework for tests |
| SLF4J | Logging API |
| Logback | Logging implementation |
| XML | Structured persistence |
| JSON | Lightweight persistence |
| TXT files | Simple file-based persistence |
| JavaFX | Graphical user interface |
| Docker | Containerized application delivery |
| GitHub Actions | CI/CD automation |

[↑ Back to top](#top)


<a id="architecture"></a>

## Architecture

The system follows a layered architecture that promotes separation of concerns, maintainability, and extensibility.

The main architectural components are:

- Domain Model Layer
- Persistence Layer
- Security Layer
- Validation Layer
- Service Layer
- Controller Layer
- User Interface Layer
- Logging Layer
- Testing Infrastructure
- CI/CD Automation Layer

This organization isolates business rules from persistence, security, user interaction, and infrastructure concerns. The result is an application that can evolve incrementally through user stories without concentrating unrelated responsibilities in a single class.

[↑ Back to top](#top)


<a id="security-model"></a>

## Security Model

The system implements Role-Based Access Control (RBAC), session-based authentication, authorization checks, and security auditing through application logging.

### Authentication Lifecycle

1. User login
2. Credential validation
3. Role identification
4. Session creation
5. Role-specific menu or screen generation
6. Authorization checks
7. Authorized operations
8. Logout
9. Session termination

### Supported Roles

| Role | Main Permissions |
|------|------------------|
| ADMIN | Register classes, save academic data, configure persistence, generate administrative reports, access administrative operations |
| PROFESSOR | Register assessments, generate academic reports, visualize academic data |

### Security Auditing

The system records security-related events through the logging infrastructure, including successful authentication attempts, failed authentication attempts, logout operations, authorization failures, and protected operation access attempts.

[↑ Back to top](#top)


<a id="repository-structure"></a>

## Repository Structure

```text
.
├── .github
│   └── workflows
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── org.example.academic.system
│   │   │   ├── org.example.academic.system.controller
│   │   │   ├── org.example.academic.system.exception
│   │   │   ├── org.example.academic.system.model
│   │   │   ├── org.example.academic.system.report
│   │   │   ├── org.example.academic.system.repository
│   │   │   ├── org.example.academic.system.security
│   │   │   ├── org.example.academic.system.service
│   │   │   ├── org.example.academic.system.validation
│   │   │   └── org.example.academic.system.view
│   │   └── resources
│   │       └── logback.xml
│   └── test
│       └── java
│           └── org.example.academic.system
├── logs
├── Dockerfile
├── pom.xml
└── README.md
```

The project follows the standard Maven directory layout, separating production code, test code, configuration files, and runtime artifacts.

[↑ Back to top](#top)


<a id="how-to-run"></a>

## How to Run

### Requirements

- Java 25 or later
- Maven 3.9 or later
- Docker, optional

### Clone Repository

```bash
git clone https://github.com/gabrielhsp-sys/academic-system.git
cd academic-system
```

### Build Project

```bash
mvn clean install
```

This command compiles the application and executes the automated tests.

### Run Application from Maven

```bash
mvn exec:java
```

### Package Application

```bash
mvn clean package
```

### Run Packaged JAR

```bash
java -jar target/AcademicSystem-1.0-SNAPSHOT.jar
```

### Run with Docker

```bash
docker build -t academic-system .
docker run -it academic-system
```

[↑ Back to top](#top)


<a id="domain-model"></a>

## Domain Model

The system models the main entities involved in academic assessment management:

- Academic classes
- Assessments
- Exams
- Practical assignments
- Seminars
- Users
- Roles

The assessment hierarchy uses inheritance and polymorphism to represent different assessment types while preserving common attributes and behavior. Each assessment contains value, weight, and type-specific attributes.

Users are associated with roles that determine the operations they are authorized to perform within the system. The domain model also defines object identity and equality rules for identifiable entities, enabling consistent behavior when working with Java collections and repository operations.

[↑ Back to top](#top)


<a id="persistence-evolution"></a>

## Persistence Evolution

| Version | Persistence Type |
|---------|------------------|
| v1 | Console only |
| v2 | TXT |
| v3 | XML |
| v4 | JSON |

The persistence layer uses repository abstractions to decouple business logic from storage concerns. TXT, XML, and JSON implementations can be exchanged through a configurable persistence strategy.

[↑ Back to top](#top)


<a id="reporting-features"></a>

## Reporting Features

The system supports administrative and academic reports that demonstrate object-oriented design, aggregation of domain information, and separation between business logic and presentation concerns.

Available reports include:

- Class assessment summary reports
- Assessment weight validation reports
- Persistence configuration reports

[↑ Back to top](#top)


<a id="branch-protection"></a>

## Branch Protection

The `main` branch is protected.

Changes must be submitted through pull requests. Direct pushes to `main` are not allowed. Before a pull request can be merged, the GitHub Actions validation workflow must complete successfully. Pull requests with compilation errors or failing tests cannot be merged.

[↑ Back to top](#top)


<a id="user-story-roadmap"></a>

## User Story Roadmap


### Academic Features

| ID | User Story | Status |
|----|------------|--------|
| [US-2361](#us-2361) | Register assessments in classes | ✅ |
| [US-2363](#us-2363) | Register classes through keyboard input | ✅ |
| [US-2364](#us-2364) | Manage academic system through command line menu | ✅ |
| [US-2375](#us-2375) | Generate class assessment summary report | ✅ |
| [US-2376](#us-2376) | Generate assessment weight report | ✅ |

### Persistence Features

| ID | User Story | Status |
|----|------------|--------|
| [TUS-2362](#tus-2362) | Persist class assessments to TXT file | ✅ |
| [US-2372](#us-2372) | Configure persistence type as administrator | ✅ |
| [US-2373](#us-2373) | Save academic data to XML file | ✅ |
| [US-2374](#us-2374) | Save academic data to JSON file | ✅ |
| [US-2377](#us-2377) | Generate persistence configuration report | ✅ |

### Security Features

| ID | User Story | Status |
|----|------------|--------|
| [US-2366](#us-2366) | Authenticate users and authorize actions based on roles | ✅ |
| [US-2369](#us-2369) | Handle authentication and authorization errors with custom exceptions | ✅ |
| [US-2378](#us-2378) | Role-based dynamic menu rendering | ✅ |
| [US-2379](#us-2379) | Logout | ✅ |
| [US-2380](#us-2380) | Display role-specific sequential menus | ✅ |

### Validation and Exception Handling

| ID | User Story | Status |
|----|------------|--------|
| [US-2367](#us-2367) | Handle academic domain errors with custom exceptions | ✅ |
| [US-2368](#us-2368) | Handle keyboard input errors with custom exceptions | ✅ |
| [TUS-2371](#tus-2371) | Validate academic domain objects using Jakarta Bean Validation | ✅ |

### Architecture and Refactoring

| ID | User Story | Status |
|----|------------|--------|
| [US-0000](#us-0000) | Start academic system | ✅ |
| [TUS-](#tus-) | Refactor domain model using Lombok | ✅ |
| [TUS-2370](#tus-2370) | Refactor menu operations into AcademicSystemController | ✅ |
| [TUS-2382](#tus-2382) | Define equality for identifiable domain objects | ✅ |
| [TUS-2396](#tus-2396) | Introduce ClassService | ✅ |
| [TUS-2397](#tus-2397) | Introduce AssessmentService | ✅ |
| [TUS-2398](#tus-2398) | Introduce PersistenceService | ✅ |
| [TUS-2399](#tus-2399) | Introduce ReportService | ✅ |
| [TUS-2400](#tus-2400) | Simplify AcademicSystemController | ✅ |
| [TUS-2414](#tus-2414) | Introduce AuthenticationController for JavaFX login | ✅ |

### Docker and Deployment

| ID | User Story | Status |
|----|------------|--------|
| [TUS-2381](#tus-2381) | Deliver academic system with Docker | ✅ |

### Testing Infrastructure and Automated Tests

| ID | User Story | Status |
|----|------------|--------|
| [TUS-2383](#tus-2383) | Configure automated testing infrastructure | ✅ |
| [TUS-2384](#tus-2384) | Test identifiable domain object equality | ✅ |
| [TUS-2385](#tus-2385) | Test academic domain validation | ✅ |
| [US-2386](#us-2386) | Test authentication behavior | ✅ |
| [US-2387](#us-2387) | Test authorization behavior | ✅ |
| [US-2388](#us-2388) | Test report generation | ✅ |
| [US-2389](#us-2389) | Test persistence repositories | ✅ |
| [TUS-2395](#tus-2395) | Verify logging infrastructure behavior | ✅ |
| [TUS-2401](#tus-2401) | Test ClassService behavior | ✅ |
| [TUS-2402](#tus-2402) | Test AssessmentService behavior | ✅ |
| [TUS-2403](#tus-2403) | Test PersistenceService behavior | ✅ |
| [TUS-2404](#tus-2404) | Test ReportService behavior | ✅ |
| [TUS-2405](#tus-2405) | Test AcademicSystemController delegation behavior | ✅ |

### Logging and Auditing

| ID | User Story | Status |
|----|------------|--------|
| [TUS-2390](#tus-2390) | Configure application logging infrastructure | ✅ |
| [TUS-2391](#tus-2391) | Log authentication and logout events | ✅ |
| [TUS-2392](#tus-2392) | Log authorization failures | ✅ |
| [TUS-2393](#tus-2393) | Log persistence operations | ✅ |
| [TUS-2394](#tus-2394) | Log report generation | ✅ |

### Graphical User Interface (JavaFX)

| ID | User Story | Status |
|----|------------|--------|
| [TUS-2406](#tus-2406) | Configure JavaFX application infrastructure | ✅ |
| [TUS-2407](#tus-2407) | Create JavaFX login screen | ✅ |
| [TUS-2408](#tus-2408) | Create JavaFX role-based main screen | ✅ |
| [TUS-2409](#tus-2409) | Create JavaFX class registration screen | ✅ |
| [TUS-2410](#tus-2410) | Create JavaFX assessment registration screen | ✅ |
| [TUS-2411](#tus-2411) | Create JavaFX report screen | ✅ |
| [TUS-2412](#tus-2412) | Create JavaFX persistence configuration screen | ✅ |
| [TUS-2413](#tus-2413) | Create JavaFX class and assessment visualization screen | ✅ |

### CI/CD and Automation

| ID | User Story | Status |
|----|------------|--------|
| [TUS-2415](#tus-2415) | Configure CI pipeline with GitHub Actions | ✅ |
| [TUS-2416](#tus-2416) | Generate test coverage reports | ✅ |
| [TUS-2417](#tus-2417) | Publish Docker image automatically | ✅ |
| [TUS-2418](#tus-2418) | Configure pull request validation workflow | ✅ |
| [TUS-2419](#tus-2419) | Configure release workflow | ✅ |
| [TUS-2420](#tus-2420) | Configure branch protection for pull requests | ✅ |

[↑ Back to top](#top)


<a id="implemented-user-stories"></a>

## Implemented User Stories

This section contains the complete user-story catalog. Every user story includes a description and acceptance criteria.


### [Academic Features](#academic-features)

- [US-2361 - Register assessments in classes](#us-2361)
- [US-2363 - Register classes through keyboard input](#us-2363)
- [US-2364 - Manage academic system through command line menu](#us-2364)
- [US-2375 - Generate class assessment summary report](#us-2375)
- [US-2376 - Generate assessment weight report](#us-2376)

### [Persistence Features](#persistence-features)

- [TUS-2362 - Persist class assessments to TXT file](#tus-2362)
- [US-2372 - Configure persistence type as administrator](#us-2372)
- [US-2373 - Save academic data to XML file](#us-2373)
- [US-2374 - Save academic data to JSON file](#us-2374)
- [US-2377 - Generate persistence configuration report](#us-2377)

### [Security Features](#security-features)

- [US-2366 - Authenticate users and authorize actions based on roles](#us-2366)
- [US-2369 - Handle authentication and authorization errors with custom exceptions](#us-2369)
- [US-2378 - Role-based dynamic menu rendering](#us-2378)
- [US-2379 - Logout](#us-2379)
- [US-2380 - Display role-specific sequential menus](#us-2380)

### [Validation and Exception Handling](#validation-and-exception-handling)

- [US-2367 - Handle academic domain errors with custom exceptions](#us-2367)
- [US-2368 - Handle keyboard input errors with custom exceptions](#us-2368)
- [TUS-2371 - Validate academic domain objects using Jakarta Bean Validation](#tus-2371)

### [Architecture and Refactoring](#architecture-and-refactoring)

- [US-000O - Start academic system](#us-0000)
- [TUS- - Refactor domain model using Lombok](#tus-)
- [TUS-2370 - Refactor menu operations into AcademicSystemController](#tus-2370)
- [TUS-2382 - Define equality for identifiable domain objects](#tus-2382)
- [TUS-2396 - Introduce ClassService](#tus-2396)
- [TUS-2397 - Introduce AssessmentService](#tus-2397)
- [TUS-2398 - Introduce PersistenceService](#tus-2398)
- [TUS-2399 - Introduce ReportService](#tus-2399)
- [TUS-2400 - Simplify AcademicSystemController](#tus-2400)
- [TUS-2414 - Introduce AuthenticationController for JavaFX login](#tus-2414)

### [Docker and Deployment](#docker-and-deployment)

- [TUS-2381 - Deliver academic system with Docker](#tus-2381)

### [Testing Infrastructure and Automated Tests](#testing-infrastructure-and-automated-tests)

- [TUS-2383 - Configure automated testing infrastructure](#tus-2383)
- [TUS-2384 - Test identifiable domain object equality](#tus-2384)
- [TUS-2385 - Test academic domain validation](#tus-2385)
- [US-2386 - Test authentication behavior](#us-2386)
- [US-2387 - Test authorization behavior](#us-2387)
- [US-2388 - Test report generation](#us-2388)
- [US-2389 - Test persistence repositories](#us-2389)
- [TUS-2395 - Verify logging infrastructure behavior](#tus-2395)
- [TUS-2401 - Test ClassService behavior](#tus-2401)
- [TUS-2402 - Test AssessmentService behavior](#tus-2402)
- [TUS-2403 - Test PersistenceService behavior](#tus-2403)
- [TUS-2404 - Test ReportService behavior](#tus-2404)
- [TUS-2405 - Test AcademicSystemController delegation behavior](#tus-2405)

### [Logging and Auditing](#logging-and-auditing)

- [TUS-2390 - Configure application logging infrastructure](#tus-2390)
- [TUS-2391 - Log authentication and logout events](#tus-2391)
- [TUS-2392 - Log authorization failures](#tus-2392)
- [TUS-2393 - Log persistence operations](#tus-2393)
- [TUS-2394 - Log report generation](#tus-2394)

### [Graphical User Interface (JavaFX)](#graphical-user-interface-javafx)

- [TUS-2406 - Configure JavaFX application infrastructure](#tus-2406)
- [TUS-2407 - Create JavaFX login screen](#tus-2407)
- [TUS-2408 - Create JavaFX role-based main screen](#tus-2408)
- [TUS-2409 - Create JavaFX class registration screen](#tus-2409)
- [TUS-2410 - Create JavaFX assessment registration screen](#tus-2410)
- [TUS-2411 - Create JavaFX report screen](#tus-2411)
- [TUS-2412 - Create JavaFX persistence configuration screen](#tus-2412)
- [TUS-2413 - Create JavaFX class and assessment visualization screen](#tus-2413)

### [CI/CD and Automation](#cicd-and-automation)

- [TUS-2415 - Configure CI pipeline with GitHub Actions](#tus-2415)
- [TUS-2416 - Generate test coverage reports](#tus-2416)
- [TUS-2417 - Publish Docker image automatically](#tus-2417)
- [TUS-2418 - Configure pull request validation workflow](#tus-2418)
- [TUS-2419 - Configure release workflow](#tus-2419)
- [TUS-2420 - Configure branch protection for pull requests](#tus-2420)

[↑ Back to top](#top)


---

<a id="academic-features"></a>

## Academic Features

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2361"></a>

### US-2361 - Registering Assessments in Classes 

As a professor, I want to register assessments in existing classes, so that the academic system can manage the evaluation activities associated with each class.

#### Acceptance Criteria 

AC1: Given an existing class,
when a valid assessment is registered,
then the assessment must be added to the selected class.

AC2: Given a valid assessment,
when the assessment type is selected,
then the system must create the corresponding assessment object:
- Exam
- PracticalAssignment
- Seminar
- Assignment

AC3: Given a valid assessment,
when it is registered,
then its value and weight must be stored in the selected class.

AC4: Given a nonexistent class code,
when an assessment registration is attempted,
then the assessment must not be registered.

AC5: Given an invalid assessment type,
when an assessment registration is attempted,
then the assessment must not be registered.

AC6: Given invalid assessment data,
when an assessment registration is attempted,
then the system must reject the operation by throwing an AcademicSystemException.

AC7: Given a registered assessment,
when the class information is displayed,
then the assessment must appear in the class assessment list.

AC8: Given a user without sufficient privileges,
when an assessment registration is attempted,
then the system must deny the operation according to the configured authorization rules.


[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2363"></a>

### US-2363 - Register classes through keyboard input 

#### Description

As an professor, I want to register classes through keyboard input, so that academic classes can be managed within the academic system.

#### Acceptance Criteria

AC1: Given an authenticated ADMIN user,
when a valid class code and title are provided,
then the class must be registered successfully.

AC2: Given a valid class registration,
when the operation completes,
then the class must be stored in the AcademicSystem.

AC3: Given invalid class data,
when a registration is attempted,
then the system must reject the operation by throwing an AcademicSystemException.

AC4: Given a class registration request,
when the operation is executed,
then the class must be validated according to the configured domain validation rules.

AC5: Given a user without administrator privileges,
when a class registration is attempted,
then the system must deny the operation according to the configured authorization rules.

AC6: Given a successfully registered class,
when the classes are displayed,
then the new class must appear in the class list.

AC7: Given a class registration operation,
when it is executed,
then the operation must be delegated through the application controller and service layers.

AC8: Given a successful or failed registration attempt,
when logging is enabled,
then the corresponding audit information must be recorded.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2364"></a>

### US-2364 - Manage academic system through command line menu

#### Description

As a system user, I want to interact with the academic system through a command line menu, so that I can execute the operations available to my authenticated role.

#### Acceptance Criteria

AC1: Given an authenticated user,
when the application starts,
then the system must display a command line menu.

AC2: Given an authenticated ADMIN user,
when the menu is displayed,
then administrator options must be available according to the ADMIN menu.

AC3: Given an authenticated PROFESSOR user,
when the menu is displayed,
then only professor-related options must be available according to the PROFESSOR menu.

AC4: Given a valid menu option,
when the user selects it,
then the system must execute the corresponding operation.

AC5: Given an invalid menu option,
when the user selects it,
then the system must display an error message without terminating unexpectedly.

AC6: Given invalid keyboard input,
when the user enters it,
then the system must handle the input error without terminating unexpectedly.

AC7: Given a user selects logout,
when the operation is executed,
then the system must return to the login screen.

AC8: Given a user selects exit,
when the operation is executed,
then the application must terminate.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2375"></a>

### US-2375 - Generate class assessment summary report

#### Description

As a user, I want to generate a class assessment summary report, so that I can view the assessments associated with each registered class.

#### Acceptance Criteria

AC1: Given one or more registered classes,
when the class assessment summary report is generated,
then the report must list all registered classes.

AC2: Given a registered class with assessments,
when the report is generated,
then the report must display:
- Class code
- Class title
- Assessment type
- Assessment value
- Assessment weight

AC3: Given a registered class without assessments,
when the report is generated,
then the class must still appear in the report.

AC4: Given no registered classes,
when the report is generated,
then the report must be generated successfully without errors.

AC5: Given a report generation request,
when the operation is executed,
then the report generation logic must be delegated to ReportService.

AC6: Given an authenticated user,
when the report is generated,
then the operation must be logged for audit purposes, including the user role.

AC7: Given a report generation request,
when the operation completes,
then existing academic data must not be modified.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2376"></a>

### US-2376 - Generate assessment weight report

#### Description

As a user, I want to generate an assessment weight report, so that I can verify the total assessment weight configured for each class.

#### Acceptance Criteria

AC1: Given one or more registered classes,
when the assessment weight report is generated,
then the report must list each registered class.

AC2: Given a class with registered assessments,
when the report is generated,
then the system must calculate and display the total assessment weight for that class.

AC3: Given a class whose total assessment weight is equal to 1.0,
when the report is generated,
then the report must indicate that the assessment composition is valid.

AC4: Given a class whose total assessment weight is different from 1.0,
when the report is generated,
then the report must indicate that the assessment composition is invalid.

AC5: Given a class without assessments,
when the report is generated,
then the total weight must be displayed as 0.0.

AC6: Given a report generation request,
when the operation is executed,
then the report generation logic must be delegated to ReportService.

AC7: Given an authenticated user,
when the report is generated,
then the operation must be logged for audit purposes, including the user role.

AC8: Given a report generation request,
when the operation completes,
then existing academic data must not be modified.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


---

<a id="persistence-features"></a>

## Persistence Features

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2362"></a>

### TUS-2362 - Persist class assessments to TXT file

#### Description

As a developer, I want class assessments to be persisted into a TXT file, so that academic data can be stored outside the application memory in a simple readable format.

#### Acceptance Criteria

AC1: Given registered classes in the academic system,
when TXT persistence is executed,
then the system must generate a TXT file.

AC2: Given a generated TXT file,
when its content is inspected,
then it must contain registered class data.

AC3: Given a registered class with assessments,
when the TXT file is generated,
then the file must contain the class assessments.

AC4: The TXT file must contain at least:
- class code,
- class title,
- assessment type,
- assessment value,
- assessment weight.

AC5: TXT persistence must be implemented through the repository abstraction.

AC6: TXT persistence must not change the academic domain model behavior.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2372"></a>

### US-2372 - Configure persistence type as administrator

#### Description

As an administrator, I want to configure the persistence type used by the academic system, so that academic data can be saved using different persistence formats.

#### Acceptance Criteria

AC1: Given an authenticated ADMIN user,
when a persistence type is selected,
then the system must update the active persistence configuration.

AC2: The system must support the following persistence types:
- TXT
- XML
- JSON

AC3: Given a persistence type configuration request,
when the operation is executed,
then the persistence configuration must be delegated to PersistenceService.

AC4: Given a non-administrator user,
when a persistence type configuration is attempted,
then the system must deny the operation according to the configured authorization rules.

AC5: Given a persistence type has been configured,
when a save operation is executed,
then the selected persistence mechanism must be used.

AC6: Given a persistence type configuration request,
when the operation completes,
then existing academic data must remain unchanged.

AC7: Given a successful configuration change,
when logging is enabled,
then the operation must be recorded for audit purposes.

AC8: Given the persistence configuration report is generated,
when the report is displayed,
then it must show the currently configured persistence type.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2373"></a>

### US-2373 - Save academic data to XML file

#### Description

As an administrator, I want the academic system to save classes and assessments into an XML file, so that academic data can be persisted in a structured and interoperable format.

#### Acceptance Criteria

AC1: Given that XML persistence is selected,
when the administrator saves academic data,
then the system must generate an XML file.

AC2: Given one or more registered classes,
when the XML file is generated,
then the file must contain all registered classes.

AC3: Given a registered class,
when the XML file is generated,
then each class must contain:
- code
- title

AC4: Given a registered class with assessments,
when the XML file is generated,
then each assessment must contain:
- type
- value
- weight

AC5: Given that XML persistence is configured,
when the save operation is executed,
then the operation must be delegated to PersistenceService and the XML repository implementation.

AC6: Given a successful XML save operation,
when logging is enabled,
then the operation must be recorded for audit purposes.

AC7: Given that XML persistence is selected,
when academic data is saved,
then the academic domain model must not be modified.

AC8: Existing TXT persistence functionality must continue working without changes.

AC9: Existing JSON persistence functionality must continue working without changes.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2374"></a>

### US-2374 - Save academic data to JSON file

#### Description

As an administrator, I want the academic system to save classes and assessments into a JSON file, so that academic data can be persisted in a structured format that is easy to exchange with other systems and applications.

#### Acceptance Criteria

AC1: Given that JSON persistence is selected,
when the administrator saves academic data,
then the system must generate a JSON file.

AC2: Given one or more registered classes,
when the JSON file is generated,
then the file must contain all registered classes.

AC3: Given a registered class,
when the JSON file is generated,
then each class must contain:
- code
- title

AC4: Given a registered class with assessments,
when the JSON file is generated,
then each assessment must contain:
- type
- value
- weight

AC5: Given that JSON persistence is configured,
when the save operation is executed,
then the operation must be delegated to PersistenceService and the JSON repository implementation.

AC6: Given a successful JSON save operation,
when logging is enabled,
then the operation must be recorded for audit purposes.

AC7: Given that JSON persistence is selected,
when academic data is saved,
then the academic domain model must not be modified.

AC8: Existing TXT persistence functionality must continue working without changes.

AC9: Existing XML persistence functionality must continue working without changes.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2377"></a>

### US-2377 - Generate persistence configuration report

#### Description

As an administrator, I want to generate a persistence configuration report, so that I can verify which persistence mechanism is currently configured in the academic system.

#### Acceptance Criteria

AC1: Given an authenticated ADMIN user,
when the persistence configuration report is generated,
then the report must display the currently configured persistence type.

AC2: Given a configured persistence type,
when the report is generated,
then the report must indicate one of the supported persistence types:
- TXT
- XML
- JSON

AC3: Given a report generation request,
when the operation is executed,
then the report generation logic must be delegated to ReportService.

AC4: Given a non-administrator user,
when the persistence configuration report is requested,
then the system must deny the operation according to the configured authorization rules.

AC5: Given a report generation request,
when the operation completes,
then existing academic data must not be modified.

AC6: Given an authenticated administrator,
when the report is generated,
then the operation must be logged for audit purposes, including the user role.

AC7: Given that the persistence type is changed,
when the report is generated afterwards,
then the report must reflect the new persistence configuration.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


---

<a id="security-features"></a>

## Security Features

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2366"></a>

### US-2366 - Authenticate users and authorize actions based on roles

#### Description

As a system user, I want to authenticate with my credentials and access only the operations allowed for my role, so that the academic system can protect restricted actions.

#### Acceptance Criteria

AC1: Given valid credentials,
when the user logs in,
then the system must authenticate the user successfully.

AC2: Given invalid credentials,
when the user tries to log in,
then the system must reject authentication.

AC3: Given an authenticated ADMIN user,
when an administrator-only operation is requested,
then the system must allow the operation.

AC4: Given an authenticated PROFESSOR user,
when an administrator-only operation is requested,
then the system must deny the operation.

AC5: Authentication data must be loaded from the configured TXT user repository.

AC6: Passwords must not be logged.

AC7: Authentication and authorization errors must be handled using security exceptions.

AC8: Authentication and authorization logic must remain separated from academic domain logic.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2369"></a>

### US-2369 - Handle authentication and authorization errors with custom exceptions

#### Description

As a developer, I want authentication and authorization failures to use specific custom exceptions, so that security errors are clearly separated from academic domain errors.

#### Acceptance Criteria

AC1: Given invalid login credentials,
when authentication is executed,
then the system must throw AuthenticationException.

AC2: Given an authenticated user without the required role,
when a protected operation is requested,
then the system must throw AuthorizationException.

AC3: Security exceptions must inherit from a common security exception superclass.

AC4: Security exceptions must remain separated from academic domain exceptions.

AC5: Security exceptions must provide readable error messages.

AC6: Main must catch security exceptions and display appropriate messages without exposing sensitive data.

AC7: Exception handling must not replace authorization checks.

AC8: Existing authentication and authorization behavior must remain unchanged for valid users.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2378"></a>

### US-2378 - Role-based dynamic menu rendering

#### Description

As a system user, I want the command line menu to display options according to my authenticated role, so that I only see operations that are relevant to my permissions.

#### Acceptance Criteria

AC1: Given an authenticated ADMIN user,
when the command line menu is displayed,
then the system must show administrator options.

AC2: Given an authenticated PROFESSOR user,
when the command line menu is displayed,
then the system must hide administrator-only options.

AC3: The PROFESSOR menu must not display options for:
- registering classes,
- saving data,
- configuring persistence type,
- generating the persistence configuration report.

AC4: Common operations must remain available to both supported roles when applicable.

AC5: Menu rendering must use the authenticated user role.

AC6: Authorization checks must remain active even if a hidden operation is somehow requested.

AC7: Existing menu behavior must remain unchanged for authorized operations.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2379"></a>

### US-2379 - Logout

#### Description

As a system user, I want to log out from the academic system, so that I can terminate my authenticated session and allow another user to log in.

#### Acceptance Criteria

AC1: Given an authenticated user,
when the logout option is selected,
then the current session must be terminated.

AC2: Given a logout operation,
when it completes,
then the system must return to the login screen.

AC3: Given a user logs out,
when a new login is performed,
then the new authenticated user must operate under their own role and permissions.

AC4: Given a logout operation,
when it is executed,
then the operation must be logged for audit purposes.

AC5: Given a logout operation,
when it completes,
then academic data already loaded in the system must remain unchanged.

AC6: Given an authenticated ADMIN user,
when logout is performed,
then administrator privileges must no longer be available until a new authentication occurs.

AC7: Given an authenticated PROFESSOR user,
when logout is performed,
then professor privileges must no longer be available until a new authentication occurs.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2380"></a>

### US-2380 - Display role-specific sequential menus

#### Description

As a system user, I want the command line menu options to be numbered sequentially according to my authenticated role, so that the menu is easier to read and use.

#### Acceptance Criteria

AC1: Given an authenticated ADMIN user,
when the menu is displayed,
then the system must show the ADMIN menu with options numbered sequentially from 1.

AC2: Given an authenticated PROFESSOR user,
when the menu is displayed,
then the system must show the PROFESSOR menu with options numbered sequentially from 1.

AC3: The same option number may represent different operations depending on the authenticated user role.

AC4: Given a selected option,
when the user confirms the option,
then the system must execute the operation according to the authenticated user role.

AC5: ADMIN-only operations must not appear in the PROFESSOR menu.

AC6: Authorization checks must remain active even with role-specific menu numbering.

AC7: Existing behavior must remain unchanged for authorized operations.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


---

<a id="validation-and-exception-handling"></a>

## Validation and Exception Handling

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2367"></a>

### US-2367 - Handle academic domain errors with custom exceptions

#### Description

As a developer, I want academic domain errors to be represented by custom exceptions, so that invalid academic operations can be handled clearly and separately from other application errors.

#### Acceptance Criteria

AC1: Given invalid class data,
when class registration is attempted,
then the system must throw a custom academic domain exception.

AC2: Given invalid assessment data,
when assessment registration is attempted,
then the system must throw a custom academic domain exception.

AC3: Academic domain exceptions must inherit from a common AcademicSystemException superclass.

AC4: Academic domain exceptions must provide readable error messages.

AC5: Academic domain exceptions must remain separated from keyboard input exceptions and security exceptions.

AC6: Main must catch academic domain exceptions and display appropriate messages.

AC7: Existing behavior must remain unchanged for valid academic operations.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2368"></a>

### US-2368 - Handle keyboard input errors with custom exceptions

#### Description

As a developer, I want invalid keyboard input to be handled through custom exceptions, so that user input errors can be managed consistently and separately from business and security errors.

#### Acceptance Criteria

AC1: Given invalid numeric input,
when the system expects a number,
then the system must throw a custom keyboard input exception.

AC2: Given invalid menu input,
when the user selects an unsupported option,
then the system must throw a custom keyboard input exception.

AC3: Keyboard input exceptions must inherit from a common KeyboardInputException superclass.

AC4: Keyboard input exceptions must provide readable error messages.

AC5: Keyboard input exceptions must remain separated from academic domain exceptions and security exceptions.

AC6: Main must catch keyboard input exceptions and display appropriate messages without terminating the application unexpectedly.

AC7: Given valid keyboard input,
when the operation is executed,
then the system behavior must remain unchanged.

AC8: Keyboard input errors must not modify academic data already stored in the system.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2371"></a>

### TUS-2371 - Validate academic domain objects using Jakarta Bean Validation

#### Description

As a developer, I want academic domain objects to be validated using Jakarta Bean Validation, so that validation rules are declared consistently in the domain model and checked before invalid objects are used.

#### Acceptance Criteria

AC1: Given a valid academic class,
when domain validation is executed,
then the class must be considered valid.

AC2: Given invalid academic class data,
when domain validation is executed,
then the system must reject the object.

AC3: Given a valid assessment,
when domain validation is executed,
then the assessment must be considered valid.

AC4: Given invalid assessment data,
when domain validation is executed,
then the system must reject the object.

AC5: Validation rules must be declared using Jakarta Bean Validation annotations.

AC6: Validation logic must be centralized in a reusable domain validator component.

AC7: Validation errors must be converted into academic domain exceptions.

AC8: Validation logic must remain separated from Main and from the user interface layer.

AC9: Existing behavior must remain unchanged for valid domain objects.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


---

<a id="architecture-and-refactoring"></a>

## Architecture and Refactoring

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="uc-000"></a>

### US-0000 - Startup Academic System

#### Description

As a professor, I want the academic system to initialize correctly when the application starts, so that I can interact with the system and manage classes and assessments.

#### Acceptance Criteria

- Given that the application is executed,
- when the startup process begins,
- then the system must initialize the AcademicSystem Singleton instance.

- The startup process must prepare the application environment for execution.

- The system must allow future initialization of repositories, persisted data, and user interaction components.

- After startup, the system must be ready to register and display classes and assessments.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2365"></a>

### TUS-2365 - Refactor domain model using Lombok

#### Description

As a developer, I want to use the Lombok library in the academic system, so that I can reduce boilerplate code and improve maintainability of the domain model classes.

#### Acceptance Criteria

- Given that the academic system project uses Maven,
- when Lombok is added to the project,
- then the project must compile successfully.

- Domain model classes must use Lombok annotations to automatically generate:
  - getters,
  - setters,
  - constructors,
  - equals,
  - hashCode,
  - and toString methods when applicable.

- Existing system behavior must remain unchanged after the refactor.

- Boilerplate methods manually implemented in model classes should be removed whenever replaced by Lombok annotations.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2370"></a>

### TUS-2370 - Refactor menu operations into AcademicSystemController 

#### Description

As a developer, I want menu operations to be moved into AcademicSystemController, so that Main remains focused on application flow and input/output coordination.

#### Acceptance Criteria

- Given that the academic system application starts,
- when Main initializes the application,
- then Main must delegate academic operations to AcademicSystemController.

- Main must remain responsible only for:
  - startup flow,
  - authentication,
  - menu visualization,
  - and menu navigation.

- AcademicSystemController must become responsible for:
  - registering classes,
  - registering assessments,
  - saving data to TXT files,
  - and searching classes by code.

- Existing system behavior must remain unchanged after the refactor.

- Exceptions thrown during academic operations must continue being handled correctly.

- The controller layer must remain separated from:
  - the domain model,
  - the security layer,
  - and the keyboard input layer.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2382"></a>

### TUS-2382 - Define equality for identifiable domain objects

#### Description

As a developer, I want identifiable domain objects to override equals and hashCode, so that objects representing the same real-world entity can be compared correctly in collections.

#### Acceptance Criteria

- Two Class objects with the same code must be considered equal.
- Two User objects with the same username must be considered equal.
- Equal objects must produce the same hashCode.
- The equality rules must not depend on mutable descriptive fields such as class title.
- The implementation must work correctly with Java collections such as HashSet.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2396"></a>

### TUS-2396 - Introduce ClassService

#### Description

As a developer, I want class-related business logic to be moved from AcademicSystemController to ClassService, so that controllers remain focused on request handling.

#### Acceptance Criteria

- A ClassService class must be created.
- Class registration logic must be moved to ClassService.
- AcademicSystemController must delegate class registration to ClassService.
- Existing behavior must remain unchanged.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2397"></a>

### TUS-2397 - Introduce AssessmentService

#### Description

As a developer, I want assessment-related business logic to be moved from AcademicSystemController to AssessmentService, so that assessment management becomes independent from controllers and easier to reuse in future interfaces.

#### Acceptance Criteria

AC1: An AssessmentService class must be created.

AC2: Assessment registration logic must be moved from AcademicSystemController to AssessmentService.

AC3: AssessmentService must be responsible for:
- finding the target class,
- creating the correct assessment type,
- validating the assessment,
- and adding the assessment to the selected class.

AC4: AcademicSystemController must delegate assessment registration to AssessmentService.

AC5: Existing behavior must remain unchanged for valid assessment registration.

AC6: Invalid assessment types must not add assessments.

AC7: Nonexistent class codes must not add assessments.

AC8: AssessmentService must remain independent from keyboard input and menu logic.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2398"></a>

### TUS-2398 - Introduce PersistenceService

#### Description

As a developer, I want persistence-related business logic to be moved from AcademicSystemController to PersistenceService, so that persistence concerns are isolated.

#### Acceptance Criteria

- A PersistenceService class must be created.
- Save operations must be moved to PersistenceService.
- Persistence type configuration must be moved to PersistenceService.
- Existing behavior must remain unchanged.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2399"></a>

### TUS-2399 - Introduce ReportService

#### Description

As a developer, I want report generation logic to be moved from AcademicSystemController to ReportService, so that reporting concerns become independent from controllers.

#### Acceptance Criteria

- A ReportService class must be created.
- Report generation logic must be moved to ReportService.
- Existing behavior must remain unchanged.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2400"></a>

### TUS-2400 - Simplify AcademicSystemController

#### Description

As a developer, I want AcademicSystemController to delegate responsibilities to services, so that it acts only as a coordination layer.

#### Acceptance Criteria

- AcademicSystemController must delegate operations to services.
- Business logic must not remain duplicated in the controller.
- Existing behavior must remain unchanged.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2414"></a>

### TUS-2414 - Introduce AuthenticationController for JavaFX login

#### Description

As a developer, I want authentication requests from the JavaFX user interface to be handled through an AuthenticationController, so that the GUI remains decoupled from service implementations and follows the same architectural pattern used throughout the application.

#### Acceptance Criteria

AC1: An AuthenticationController class must be created.

AC2: AuthenticationController must delegate authentication requests to AuthenticationService.

AC3: JavaFXMain must no longer directly depend on AuthenticationService.

AC4: JavaFXMain must use AuthenticationController to authenticate users.

AC5: AuthenticationController must return the authenticated User upon successful authentication.

AC6: AuthenticationController must propagate authentication exceptions without replacing existing exception handling behavior.

AC7: Existing command line authentication behavior must remain unchanged.

AC8: Existing authentication automated tests must continue passing.

AC9: The GUI layer must remain free of authentication business logic.

AC10: AuthenticationController must be reusable by future JavaFX screens and other presentation layers.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


---

<a id="docker-and-deployment"></a>

## Docker and Deployment

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2381"></a>

### TUS-2381 - Deliver academic system with Docker

#### Description

As a developer, I want to package the academic system using Docker, so that the application can be built and executed in a consistent environment without requiring Java and Maven to be installed directly on the host machine.

#### Acceptance Criteria

- Given that Docker is installed,
- when the user builds the Docker image,
- then the academic system image must be created successfully.


- Given that the Docker image exists,
- when the user runs the container,
- then the academic system must start through the command line interface.

- The Docker container must support interactive keyboard input.

- The application must be built using Maven inside the Docker image.

- The Docker setup must not change the academic system business logic.

- The README must include instructions to build and run the application with Docker.
  

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


---

<a id="testing-infrastructure-and-automated-tests"></a>

## Testing Infrastructure and Automated Tests

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2383"></a>

### TUS-2383 - Configure automated testing infrastructure

#### Description

As a developer, I want to configure JUnit Jupiter and Mockito in the project, so that automated unit tests can be implemented consistently.

#### Acceptance Criteria

- The project must support JUnit Jupiter tests.
- The project must support Mockito-based tests.
- Maven must execute automated tests successfully.
- The project must contain a simple passing test example.
- The test infrastructure must work both locally and inside Docker builds.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2384"></a>

### TUS-2384 - Test identifiable domain object equality

#### Description

As a developer, I want automated tests for domain object equality, so that equals and hashCode behavior remains correct.

#### Acceptance Criteria

- Two Class objects with the same code must be equal.
- Two Class objects with the same code must have the same hashCode.
- Two User objects with the same username must be equal.
- Two User objects with the same username must have the same hashCode.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2385"></a>

### TUS-2385 - Test academic domain validation

#### Description

As a developer, I want automated tests for academic domain validation, so that invalid classes and assessments are rejected consistently.

#### Acceptance Criteria

- A valid Class must pass validation.
- A Class with blank code must fail validation.
- A Class with blank title must fail validation.
- An Assessment with invalid value must fail validation.
- An Assessment with invalid weight must fail validation.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2386"></a>

### US-2386 - Test authentication behavior

#### Description

As a developer, I want automated tests for authentication, so that users can log in only with valid credentials.

#### Acceptance Criteria

- Valid username and password must authenticate successfully.
- Invalid username must throw AuthenticationException.
- Invalid password must throw AuthenticationException.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2387"></a>

### US-2387 - Test authorization behavior

#### Description

As a developer, I want automated tests for role-based authorization, so that protected operations remain restricted.

#### Acceptance Criteria

- ADMIN must be authorized for administrator operations.
- PROFESSOR must not be authorized for administrator operations.
- Unauthorized access must throw AuthorizationException.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2388"></a>

### US-2388 - Test report generation

#### Description

As a developer, I want automated tests for report generation, so that reports correctly represent registered academic data. 

#### Acceptance Criteria

- Class assessment summary report must include class code, title, assessment type, value, and weight.
- Assessment weight report must calculate total weight correctly.
- Persistence configuration report must show the selected persistence type.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="us-2389"></a>

### US-2389 - Test persistence repositories

#### Description

As a developer, I want automated tests for TXT, XML, and JSON persistence, so that generated files contain academic data in the expected format.

#### Acceptance Criteria

- TXT repository must generate a TXT file.
- XML repository must generate an XML file.
- JSON repository must generate a JSON file.
- Generated files must contain class and assessment data.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2395"></a>

### TUS-2395 - Verify logging infrastructure behavior

#### Description

As a developer, I want automated tests to verify the logging infrastructure, so that application components can safely produce audit log messages without affecting business functionality.

#### Acceptance Criteria

- A logger instance can be created successfully.

- Log messages can be written without throwing exceptions.

- Logging infrastructure tests must not depend on specific log message contents.

- Logging infrastructure tests must not depend on a specific log destination (console or file).

- Existing business behavior must remain unchanged when logging is enabled.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2401"></a>

### TUS-2401 - Test ClassService behavior

#### Description

As a developer, I want automated tests for ClassService, so that class registration behavior remains correct after moving class-related logic out of the controller.

#### Acceptance Criteria

- ClassService must register a valid class.
- Registered class must be stored in AcademicSystem.
- Invalid class data must throw AcademicSystemException.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2402"></a>

### TUS-2402 - Test AssessmentService behavior

#### Description

As a developer, I want automated tests for AssessmentService, so that assessment registration behavior remains correct after moving assessment-related logic out of the controller.

#### Acceptance Criteria

- AssessmentService must register an assessment in an existing class.
- Registered assessment must be stored in the selected class.
- Invalid assessment type must not add an assessment.
- Nonexistent class code must not add an assessment.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2403"></a>

### TUS-2403 - Test PersistenceService behavior

#### Description

As a developer, I want automated tests for PersistenceService, so that saving data and changing persistence formats continue working after isolating persistence logic.

#### Acceptance Criteria

- PersistenceService must save data using the default TXT repository.
- PersistenceService must change persistence type to XML.
- PersistenceService must change persistence type to JSON.
- Saved files must contain class data.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2404"></a>

### TUS-2404 - Test ReportService behavior

#### Description

As a developer, I want automated tests for ReportService, so that report generation remains correct after moving reporting logic out of the controller.

#### Acceptance Criteria

- ReportService must generate class assessment summary report.
- ReportService must generate assessment weight report.
- ReportService must generate persistence configuration report.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2405"></a>

### TUS-2405 - Test AcademicSystemController delegation behavior

#### Description

As a developer, I want automated tests for AcademicSystemController, so that the controller correctly delegates operations to services while preserving authorization rules and existing behavior.

#### Acceptance Criteria

- Controller must register classes through service delegation.
- Controller must register assessments through service delegation.
- Controller must preserve authorization rules.
- Existing behavior must remain unchanged.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


---

<a id="logging-and-auditing"></a>

## Logging and Auditing

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2390"></a>

### TUS-2390 - Configure application logging infrastructure

#### Description

As a developer, I want to configure a logging framework in the academic system, so that application events can be recorded consistently.

#### Acceptance Criteria

- The project must include a logging framework.
- The application must be able to write log messages.
- Logs must include at least timestamp, level, class, and message.
- Logging configuration must be separated from business logic.
- Existing system behavior must remain unchanged.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2391"></a>

### TUS-2391 - Log authentication and logout events

#### Description

As a developer, I want authentication and logout events to be logged, so that user access can be audited.

#### Acceptance Criteria

- Successful login attempts must be logged.
- Failed login attempts must be logged.
- Logout events must be logged.
- Logs must include the username when available.
- Passwords must never be logged.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2392"></a>

### TUS-2392 - Log authorization failures

#### Description

As a developer, I want authorization failures to be logged, so that denied access attempts can be audited.

#### Acceptance Criteria

- Unauthorized access attempts must be logged.
- Logs must include the user role when available.
- Logs must include the attempted protected operation.
- Authorization logging must not replace exception handling.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2393"></a>

### TUS-2393 - Log persistence operations

#### Description

As a developer, I want persistence operations to be logged, so that data-saving actions can be audited.

#### Acceptance Criteria

- Saving data in TXT format must be logged.
- Saving data in XML format must be logged.
- Saving data in JSON format must be logged.
- Logs must include the selected persistence type.
- Persistence logging must remain separated from domain model classes.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2394"></a>

### TUS-2394 - Log report generation

#### Description

As a developer, I want report generation events to be logged, so that report access can be audited.

#### Acceptance Criteria

- Class assessment summary report generation must be logged.
- Assessment weight report generation must be logged.
- Persistence configuration report generation must be logged.
- Logs must include the authenticated user role.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


---

<a id="graphical-user-interface-javafx"></a>

## Graphical User Interface (JavaFX)

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2406"></a>

### TUS-2406 - Configure JavaFX application infrastructure

#### Description

As a developer, I want to configure JavaFX infrastructure for the academic system, so that a graphical user interface can be developed without changing the existing business logic.

#### Acceptance Criteria

AC1: JavaFX dependencies must be added to the Maven project.

AC2: A JavaFX Application entry point must be created.

AC3: The JavaFX application must start successfully.

AC4: The existing command line application must continue working.

AC5: The JavaFX layer must reuse the existing controllers and services.

AC6: The JavaFX layer must not contain academic business logic.

AC7: Existing automated tests must continue passing.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2407"></a>

### TUS-2407 - Create JavaFX login screen

#### Description

As a system user, I want to authenticate through a JavaFX login screen, so that I can access the academic system using a graphical interface.

#### Acceptance Criteria

AC1: The login screen must contain username and password fields.

AC2: The login screen must contain a login button.

AC3: Given valid credentials,
when the user submits the login form,
then authentication must succeed.

AC4: Given invalid credentials,
when the user submits the login form,
then an error message must be displayed.

AC5: Passwords must not be displayed as plain text.

AC6: Authentication must be delegated to the existing AuthenticationService.

AC7: After successful authentication, the role-based main screen must be displayed.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2408"></a>

### TUS-2408 - Create JavaFX role-based main screen

#### Description

As an authenticated user, I want to see a JavaFX main screen that displays operations according to my role, so that I can access only the features available to me.

#### Acceptance Criteria

AC1: Given an authenticated ADMIN user,
when the main screen is displayed,
then administrator options must be visible.

AC2: Given an authenticated PROFESSOR user,
when the main screen is displayed,
then administrator-only options must not be visible.

AC3: The main screen must provide access to all currently implemented operations.

AC4: Role-based visibility must not replace authorization checks.

AC5: The screen must provide a logout option.

AC6: Logout must return the user to the login screen.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2409"></a>

### TUS-2409 - Create JavaFX class registration screen

#### Description

As an administrator, I want to register classes through a JavaFX form, so that I can manage academic classes using a graphical interface.

#### Acceptance Criteria

AC1: The screen must provide fields for class code and class title.

AC2: Given valid class data,
when the administrator submits the form,
then the class must be registered successfully.

AC3: Given invalid class data,
when the administrator submits the form,
then validation errors must be displayed.

AC4: Class registration must be delegated to AcademicSystemController.

AC5: The GUI layer must not duplicate business logic.

AC6: Non-administrator users must not access this screen.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2410"></a>

### TUS-2410 - Create JavaFX assessment registration screen

#### Description

As a professor, I want to register assessments through a JavaFX form, so that I can manage assessments without using the command line interface.

#### Acceptance Criteria

AC1: The screen must allow selection of an existing class.

AC2: The screen must allow selection of the assessment type.

AC3: The screen must allow entry of assessment value and weight.

AC4: Given valid assessment data,
when the form is submitted,
then the assessment must be registered successfully.

AC5: Given invalid assessment data,
when the form is submitted,
then validation errors must be displayed.

AC6: Assessment registration must be delegated to AcademicSystemController.

AC7: The GUI layer must not duplicate business logic.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2411"></a>

### TUS-2411 - Create JavaFX report screen

#### Description

As a system user, I want to generate reports through a JavaFX interface, so that I can inspect academic information graphically.

#### Acceptance Criteria

AC1: The screen must allow generation of the class assessment summary report.

AC2: The screen must allow generation of the assessment weight report.

AC3: ADMIN users must also be able to generate the persistence configuration report.

AC4: PROFESSOR users must not access the persistence configuration report.

AC5: Generated reports must be displayed within the JavaFX application.

AC6: Report generation must be delegated to AcademicSystemController.

AC7: The GUI layer must not duplicate report generation logic.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2412"></a>

### TUS-2412 - Create JavaFX persistence configuration screen

#### Description

As an administrator, I want to configure the persistence type through a JavaFX interface, so that I can select how academic data will be saved.

#### Acceptance Criteria

AC1: The screen must allow selection of TXT persistence.

AC2: The screen must allow selection of XML persistence.

AC3: The screen must allow selection of JSON persistence.

AC4: Given a persistence type selection,
when the administrator confirms the operation,
then the persistence configuration must be updated.

AC5: Persistence configuration must be delegated to AcademicSystemController.

AC6: Non-administrator users must not access this screen.

AC7: Existing persistence behavior must remain unchanged.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2413"></a>

### TUS-2413 - Create JavaFX class and assessment visualization screen

#### Description

As a system user, I want to visualize registered classes and assessments through a JavaFX screen, so that I can inspect academic data without using the command line interface.

#### Acceptance Criteria

AC1: The screen must display registered classes.

AC2: The screen must display the assessments associated with each class.

AC3: Class code and title must be displayed.

AC4: Assessment type, value, and weight must be displayed.

AC5: Data visualization must be delegated to AcademicSystemController.

AC6: The GUI layer must not duplicate business logic.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


---

<a id="cicd-and-automation"></a>

## CI/CD and Automation

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2415"></a>

### TUS-2415 - Configure CI pipeline with GitHub Actions

#### Description

As a developer, I want to configure a GitHub Actions CI pipeline, so that the project is automatically built and tested whenever changes are pushed or submitted through pull requests.

#### Acceptance Criteria

AC1: A GitHub Actions workflow file must be created.

AC2: The workflow must run on push and pull request events.

AC3: The workflow must set up Java 25.

AC4: The workflow must build the project with Maven.

AC5: The workflow must execute automated tests.

AC6: The workflow must fail if compilation or tests fail.

AC7: The workflow must not change application business logic.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2416"></a>

### TUS-2416 - Generate test coverage reports

#### Description

As a developer, I want automated test coverage reports to be generated during the CI process, so that test quality can be monitored continuously.

#### Acceptance Criteria

AC1: The build process must generate a test coverage report.

AC2: The coverage report must include line coverage metrics.

AC3: The coverage report must include branch coverage metrics when supported.

AC4: Coverage reports must be generated automatically during CI execution.

AC5: Coverage reports must be accessible from build artifacts.

AC6: Existing automated tests must continue working.

AC7: Existing build behavior must remain unchanged.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2417"></a>

### TUS-2417 - Publish Docker image automatically

#### Description

As a developer, I want Docker images to be published automatically through GitHub Actions, so that application artifacts can be distributed consistently.

#### Acceptance Criteria

AC1: A GitHub Actions workflow must build the Docker image automatically.

AC2: The Docker image must be generated only when the build and tests succeed.

AC3: The workflow must publish the image to a container registry.

AC4: Published images must be versioned using Git tags or commit identifiers.

AC5: The workflow must execute without requiring manual image creation.

AC6: Existing Docker functionality must remain unchanged.

AC7: Published images must be available for later deployment.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2418"></a>

### TUS-2418 - Configure pull request validation workflow

#### Description

As a developer, I want pull requests to be automatically validated through GitHub Actions, so that code quality problems can be detected before changes are merged.

#### Acceptance Criteria

AC1: A GitHub Actions workflow must execute when a pull request is created or updated.

AC2: The workflow must build the project successfully.

AC3: The workflow must execute all automated tests.

AC4: The workflow must fail when compilation errors occur.

AC5: The workflow must fail when any automated test fails.

AC6: Pull request validation must execute automatically without manual intervention.

AC7: Existing development workflows must remain unchanged.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2419"></a>

### TUS-2419 - Configure release workflow

#### Description

As a developer, I want a GitHub Actions release workflow, so that application releases can be created and distributed automatically.

#### Acceptance Criteria

AC1: A GitHub Actions workflow must execute when a release tag is created.

AC2: The workflow must build the project successfully.

AC3: The workflow must execute all automated tests before creating the release.

AC4: The workflow must generate release artifacts automatically.

AC5: The workflow must publish the release artifacts to GitHub Releases.

AC6: Release generation must not require manual packaging steps.

AC7: Existing development and CI workflows must remain unchanged.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


<a id="tus-2420"></a>

### TUS-2420 - Configure branch protection for pull requests

#### Description

As a developer, I want branch protection rules to be enforced for pull requests, so that changes cannot be merged into the main branch unless they satisfy the project's quality requirements.

#### Acceptance Criteria

AC1: Branch protection must be configured for the main branch. 

AC2: Direct pushes to the protected branch must be prohibited. 

AC3: Changes to the protected branch must be performed through pull requests. 

AC4: Pull requests must require successful completion of the CI workflow before merging. 

AC5: Pull requests with failing builds must not be mergeable. 

AC6: Pull requests with failing automated tests must not be mergeable. 

AC7: Branch protection settings must be managed through GitHub repository configuration. 

AC8: Existing development workflows must remain unchanged except for the introduction of merge validation requirements. 

AC9: The protected branch must remain deployable after every successful merge. 

AC10: Documentation must describe the branch protection rules adopted by the project.

[↑ Back to top](#top) | [↑ Back to Implemented User Stories](#implemented-user-stories)


---

<a id="future-improvements"></a>

## Future Improvements

Possible future improvements include:

- REST API
- Database persistence
- Integration testing
- Spring Boot migration
- Hibernate/JPA persistence
- Web frontend
- Advanced role management
- Student management
- Grade calculation and final grade reports
- User management interface
- Password encryption and secure credential storage
- Export reports to PDF
- Export reports to Excel
- Multi-user persistence support
- Cloud deployment

[↑ Back to top](#top)


<a id="educational-purpose"></a>

## Educational Purpose

This project was developed for educational purposes to support learning in:

- Object-Oriented Programming
- Software Engineering
- Agile Development
- SOLID Principles
- Refactoring
- Software Architecture
- Layered Architecture
- Repository Pattern
- Persistence Strategies
- Validation
- Authentication and Authorization
- Security
- Logging and Auditing
- Automated Testing
- Containerization
- Deployment
- Exception Handling
- GUI Development
- CI/CD Automation

[↑ Back to top](#top)


<a id="license"></a>

## License

This repository is intended for academic and educational use.

[↑ Back to top](#top)
