# 📦 Fruit Order API - MongoDB

REST API for managing fruit orders using MongoDB with embedded documents.

**Level 3** | Spring Boot 3.x + MongoDB + Docker | TDD Outside-In

---

## 🎯 Overview

API to manage fruit orders with:
- Client name
- Delivery date (minimum tomorrow)
- List of fruit items with quantities

Orders stored as MongoDB documents with embedded items.

---

## 🌐 Endpoints

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| POST | `/orders` | Create order | 201 |
| GET | `/orders` | Get all orders | 200 |
| GET | `/orders/{id}` | Get order by ID | 200/404 |
| PUT | `/orders/{id}` | Update order | 200/404 |
| DELETE | `/orders/{id}` | Delete order | 204/404 |
| GET | `/actuator/health` | Health check | 200 |

---

## 📝 Data Model

```json
{
  "id": "675ec8f9a1234567890abcde",
  "clientName": "John Doe",
  "deliveryDate": "2025-12-17",
  "items": [
    {
      "fruitName": "Apple",
      "quantityInKilos": 5
    }
  ]
}
```
---

## With Docker Compose

# Start services
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop services
docker-compose down

---

## Local Development

# Run tests
./mvnw test

# Run application (MongoDB required)
./mvnw spring-boot:run

---

## 🏗️ Architecture

controllers/    → REST endpoints
services/       → Business logic
repository/     → MongoDB access
model/          → Entities (Order, OrderItem)
dto/            → Data transfer objects
exception/      → Custom exceptions & handler
mapper/         → Conversion between DTOs and entities

---

## 🔧 Technologies

Spring Boot 3.x

Spring Data MongoDB

Bean Validation (JSR-380)

Spring Boot Actuator

Lombok

Testcontainers (for real MongoDB tests)

JUnit 5 + Mockito

Docker + Docker Compose

Maven

---

## ⚙️ Configuration

# Environment Variables

MONGODB_URI=mongodb://localhost:27017/fruit_orders
MONGODB_DATABASE=fruit_orders
SERVER_PORT=8080
LOG_LEVEL=INFO

---

## 📊 Project Structure

src/
├── main/java/cat/itacademy/s04/t02/n03/fruit/  
│   ├── controller/  
│   ├── dto/  
│   ├── exception/  
│   ├── mapper/  
│   ├── model/  
│   ├── repository/  
│   ├── service/  
│   └── FruitOrderApiApplication.java  
└── test/java/cat/itacademy/s04/t02/n03/fruit/  
    ├── controller/   
    ├── service/  
    └── FruitOrderApiApplicationTests.java   
Dockerfile
docker-compose.yml
mvnw
.gitattributes
.gitignore

---

## 📚 Requirements

Java 21 (LTS)

Maven 3.8+

Docker 20.10+

MongoDB 7.0 (or Docker)





