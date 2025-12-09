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

src/
├── main/java/framework
│ ├── auth/ → Authentication service
│ ├── config/ → Environment configuration
│ ├── http/ → ApiClient (GET/POST/PUT/DELETE)
│ └── models/ → Booking and BookingDates POJOs
└── test/java/tests
├── login/ → Login test
└── booking/ → Create, Update, Delete tests

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
- Config via `.properties` or `.env`

---

## 📄 License

MIT License

---

If you're reviewing this repository as part of a job application:  
**Thanks for taking the time! 😊**  
This framework is intentionally simple, clean, and SDET-friendly.

---
