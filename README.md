# ReqRes REST Client – Spring Boot Demo

This project is a simple Spring Boot application that connects to the public [ReqRes API](https://reqres.in/).  
It demonstrates how to build a REST client with **Spring Boot 3** to perform `GET`, `POST`, and `DELETE` requests.

---

## 🚀 Features

-  **Get all users** from ReqRes 📋
-  **Create a new user** (POST)
-  **Delete a user by ID** (DELETE)

---

## 🛠️ Tech Stack
- Java 21
- Spring Boot 3.x
- Spring Web (RestClient)
- Maven
 
---

## 🏁 Endpoints (local API)

The project exposes its own API that proxies requests to `https://reqres.in/api`.

### Get all users
> GET http://localhost:8080/api/users

### Create a new user
> POST http://localhost:8080/api/users  
Content-Type: application/json  
```json
{  
    "name": "Rebecca",  
    "job": "Photographer"  
}
``` 


Example Response:
```json
{
  "name": "Rebecca",
  "job": "Photographer",
  "id": "123",
  "createdAt": "2025-09-19T12:34:56.789Z"
}
```

### Delete a user by ID
> DELETE http://localhost:8080/api/users/{id}

--- 

## ▶️ Installation & Run
- Clone the repository
> git clone <repo-url>

## 🔧 Build the project
> mvn clean install

## 👢 Start Spring Boot
> mvn spring-boot:run

Test the endpoints using Postman or cURL.

### 💬 Note
This project uses the free ReqRes API, which is a mock API for testing REST clients.
All changes (like creating or deleting users) are simulated only and not persisted.
