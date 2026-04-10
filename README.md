# Yandex.Disk API Automation Testing (Fintech Internship)

This repository contains an automated testing suite for the Yandex.Disk REST API, developed as part of the Yandex Fintech Internship recruitment process. The project focuses on robust API validation using **Java** and **RestAssured**.

## 🚀 Project Overview
The project implements a complete testing cycle for cloud resources using the following HTTP methods:
* **GET**: Retrieve disk capacity information and resource metadata.
* **PUT**: Create new directories and manage resource publication (public access).
* **POST**: Handle resource operations including Copy and Move (Rename).
* **DELETE**: Remove resources, unpublish items, and clear the trash bin.

## 🏗 Architecture & Best Practices
* **Constant Management**: All API endpoints are centralized in `Endpoints.java` to ensure maintainability and prevent hardcoding.
* **Request Specifications**: Shared configurations (Base URL, Auth Headers, Logging) are managed via `RequestSpecBuilder` for code reusability.
* **Data Isolation**: Each test generates unique resource paths using `System.currentTimeMillis()` to ensure independent test execution.
* **Security**: Sensitive data (OAuth tokens) are handled via environment variables to prevent security vulnerabilities.

## 🛠 Tech Stack
* **Java 17**
* **JUnit 5** (Test Runner)
* **RestAssured** (API Testing Library)
* **Maven** (Build Tool)

## 🔒 Security & Authorization
To run these tests, you must provide a valid Yandex.Disk OAuth token. For security reasons, the token is **not hardcoded** in the repository.

### Setting up the Environment
Set your token as an environment variable before running the tests:
- **Linux/macOS**: `export YANDEX_TOKEN="your_oauth_token"`
- **Windows (CMD)**: `set YANDEX_TOKEN="your_oauth_token"`
- **Windows (PowerShell)**: `$env:YANDEX_TOKEN="your_oauth_token"`

## 🏃 Running the Tests
```bash
# Clone the repository
git clone https://github.com/Hattaeck/yandex-disk-api-tests

# Run tests
mvn test
