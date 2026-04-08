# Yandex.Disk API Automation Testing (Fintech Internship)

This repository contains a sample automation testing project for the Yandex.Disk REST API, developed as part of the Yandex Fintech Internship recruitment process.

## Project Overview
The project implements automated tests for core Yandex.Disk resources using the following HTTP methods:
* **GET**: Retrieve disk information.
* **PUT**: Create a new folder.
* **POST**: Copy resources (rename/copy).
* **DELETE**: Remove resources.

## Tech Stack
* **Java 17**
* **JUnit 5** (Test Runner)
* **RestAssured** (API Testing Library)
* **Maven** (Build Tool)

## How to Run
1. Clone the repository:
   ```bash
   git clone [your-repository-link]
2. Open the project in your IDE (IntelliJ IDEA recommended).
3. To run the tests via command line, use:
   mvn test
4. Important Note (Authorization)
   To ensure security, the OAuth token is not hardcoded in this repository. To run the tests successfully:
   Obtain your OAuth token via Yandex.Disk Polygon.

Open the src/test/java/ru/yandex/disk/tests/DiskApiTest.java file.

Replace the placeholder with your actual token in the myToken variable:
private final String myToken = "FOR YANDEX TEAM OPEN READ ME";