# API Testing Framework (Java + RestAssured)

This project is a lightweight but fully functional API testing framework built in Java using RestAssured and JUnit 5.  
It tests a real public API — **Restful Booker** — and demonstrates clean automation architecture without Selenium.

The goal of this framework is to showcase backend/API testing skills commonly required in SDET and QA Automation roles.

---

## 🚀 Features

- Java 17 + Maven project
- Clean and modular structure
- Custom `ApiClient` for HTTP interactions
- `AuthService` to encapsulate authentication logic
- POJO models (`Booking`, `BookingDates`)
- CRUD tests against a real API:
  - **Login**
  - **Create Booking**
  - **Update Booking**
  - **Delete Booking**
- No UI/Selenium — pure API automation
- Ready to extend with reporting (Allure), schema validation, parallel testing, etc.

---

## 🗂 Project Structure

```
src/
├── main
│   └── java
│       └── framework
│           ├── auth/          → Authentication service
│           ├── config/        → Environment configuration
│           ├── http/          → ApiClient (GET/POST/PUT/DELETE)
│           └── models/        → Booking and BookingDates POJOs
└── test
    └── java
        └── tests
            ├── login/         → Login test
            └── booking/       → Create, Update, Delete tests
```

---

## 🧪 How to run the tests

### Requirements
- Java **17**
- Maven **3.8+**

### Run the whole suite

```bash
mvn clean test
```

The framework will:

- Authenticate against Restful Booker  
- Create a booking  
- Update the booking  
- Delete the booking  
- Validate that the deleted booking no longer exists  

---

## 📐 Configuration & Environments

The framework supports **multi-environment configuration**, allowing you to switch between different setups (e.g., local, CI, dev, prod) without modifying the source code.

Configuration values come from three sources, in order of precedence:

1. **Environment variables** (highest priority)
2. **Environment-specific properties file** (`config-<env>.properties`)
3. **Base properties file** (`config.properties`)

---

### 🔧 Base Configuration

Common defaults are stored in:

```
src/main/resources/config.properties
```

Example:

```
baseUrl=https://restful-booker.herokuapp.com
username=admin
password=password123
```

---

### 🌍 Environment Selection (API_ENV)

The active environment is selected using:

```
API_ENV=<env>
```

If not provided, the framework defaults to:

```
API_ENV=local
```

For each environment, a corresponding file must exist:

```
src/main/resources/config-local.properties
src/main/resources/config-ci.properties
src/main/resources/config-dev.properties
src/main/resources/config-prod.properties
```

Example config-ci.properties:

```
baseUrl=https://restful-booker.herokuapp.com
```

---

### ⚙️ Overriding with Environment Variables

Any configuration key can be overridden using environment variables:

| Property key | Environment variable |
| ------------ | -------------------- |
| baseUrl      | `API_BASE_URL`       |
| username     | `API_USERNAME`       |
| password     | `API_PASSWORD`       |

Env vars always take precedence over property files.

Example (Linux / Git Bash):

```
export API_ENV=ci
export API_BASE_URL=https://restful-booker.herokuapp.com
export API_USERNAME=admin
export API_PASSWORD=password123
```

Windows PowerShell equivalent:

```
$env:API_ENV="ci"
$env:API_BASE_URL="https://restful-booker.herokuapp.com"
```

---

### 🔎 Resolution Order

When the framework needs a configuration value:

1. Check if the corresponding environment variable exists
2. If not, check config-<env>.properties
3. If not, fall back to config.properties
4. If missing in all sources → fail with a clear exception

Example for the `baseUrl`:

```
API_BASE_URL  →  config-ci.properties  →  config.properties
```

---

### 🧪 Verifying Environment Selection

You can check which environment is currently active:

```java
Environment.getActiveEnvironment();
```

Useful for debugging or CI logging.

---

### 🚀 Example Usage

Run tests using local configuration:

```
mvn clean test
```

Run tests using CI configuration with environment overrides:

```
export API_ENV=ci
export API_USERNAME=ci-user
export API_PASSWORD=ci-pass
mvn clean test
```

---

## 🛠 Technologies Used

- **Java 17**  
- **JUnit 5**  
- **RestAssured 5**  
- **Jackson Databind**  
- **Maven**  

---

## 📌 Test Scenarios Implemented

### 🔐 Authentication  
**POST /auth**  
Returns a token used for authenticated operations.

---

### 📝 Create Booking  
**POST /booking**  
Creates a reservation and validates returned fields.

---

### ✏️ Update Booking  
**PUT /booking/{id}**  
Updates reservation fields using an authenticated request.

---

### 🗑 Delete Booking  
**DELETE /booking/{id}**  
Deletes a booking and verifies that it no longer exists (`GET → 404`).

---

## 📦 Future Improvements

- Allure HTML reporting  
- JSON Schema validation  
- Parallel test execution  
- Test data builder for bookings  

---

## 📄 License

This project is open-source and distributed under the MIT License.  
Copyright © 2025–present Néstor Pavón Puro.  
For more information, see the [LICENSE](./LICENSE) file.

---

If you're reviewing this repository as part of a job application:  
**Thanks for taking the time! 😊**  
This framework is intentionally simple, clean, and SDET-friendly.

---
