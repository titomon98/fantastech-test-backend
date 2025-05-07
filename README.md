# SMS Project

## Overview

This project provides a RESTFUL API for handling SMS requests. The API splits long messages into multiple parts and 
"sends" them to a specified phone number. The main logic is divided between the controller and service, with validation
and response handling to ensure a clear message delivery.

## Requirements

- Java 17 or later
- Maven

## Steps to Start the Application

### 1. Clone the Repository

Run the following command to clone the repository:

```bash
git clone https://github.com/titomon98/fantastech-test-backend.git
```

### 2. Move into the project directory

```bash
cd fantastech-test-backend
```

### 3. Build the application using maven

```bash
mvn clean install
```

### 4. Start the Application with Spring Boot

```bash
mvn spring-boot:run
```

### 5. Send a request from any client, like postman or thunder. Here's an example

```
curl --location 'http://localhost:8080/api/sms' \
--header 'Content-Type: application/json' \
--data '{
  "number": "12345678",
  "message": "Lorem ipsum dolor sit amet consectetur adipiscing elit. Blandit quis suspendisse aliquet nisi sodales consequat magna. Sem placerat in id cursus mi pretium tellus. Finibus facilisis dapibus etiam interdum tortor ligula congue. Sed diam urna tempor pulvinar vivamus fringilla lacus. Porta elementum a enim euismod quam justo lectus. Nisl malesuada lacinia integer nunc posuere ut hendrerit. Imperdiet mollis nullam volutpat porttitor ullamcorper rutrum gravida. Ad litora torquent per conubia nostra inceptos himenaeos. Ornare sagittis vehicula praesent dui felis venenatis ultrices."
}
'
```

## Features

- **Message Validation**: The system ensures that the phone number and message adhere to certain criteria, such as length and format.
- **Message Splitting**: Long messages are automatically split into smaller parts if they exceed the maximum allowed length.
- **Response Handling**: A structured response is returned, including details of the split messages, status code, and success message.
- **Error Handling**: The system handles different error cases such as missing fields or message length exceeding the allowed limit.

---

## Project Structure

### 1. **Controller:**

The `SmsRegistrationController` is responsible for receiving SMS requests and delegating the logic to the service.

### 2. **Service:**

The `SmsRegistrationService` is responsible for the logic of the application.

---

## What can I improve with more time

### 1. **Architecture:**

Implement a better layered architecture so this microservice can log all the SMS in a database (if needed) and return 
a record of the SMS.

### 2. **Packages and dependencies:**

Implement packages like Lombok that reduce the code needed

---

## Answers to the design/analytical questions below

### 1. **Architecture:**

Use a layered architecture inside this "microservice" to avoid wasting time creating the project.

### 2. **API Design:**

I decided that the API should have an endpoint structure, simple request format and a response format for this example.
For now, I did not consider the versioning like /api/v1/sms because of time constraints.

### 3. **State Management:**

I decided to use a color palette and an explanation of the message to the user, so it is completely clear what is 
happening.

### 4. **Security:**

For now, I didn't consider any kind of security other than cors configuration.

### 5. **Scalability & Maintainability:**

I would implement other microservices like a broker and an error message handler to leave this microservice
only with the message split task.

### 6. **Time Constraints:**

I skipped many things like many other validations, login, tokens, a complete architecture inside the microservice
(that would include something like a model or a repository) and much other stuff.

---

## To see the frontend repository, please visit https://github.com/titomon98/fantastech-test-frontend