#  Tour Booking System

A production-ready **REST API** built with **Spring Boot** for managing tour bookings, user authentication, and Stripe payment integration. Designed with a clean layered architecture — Controller → Service → Repository.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen?style=flat-square&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.x-blue?style=flat-square&logo=mysql)
![Stripe](https://img.shields.io/badge/Stripe-Payment-blueviolet?style=flat-square&logo=stripe)
![JWT](https://img.shields.io/badge/Auth-JWT-yellow?style=flat-square&logo=jsonwebtokens)
![Swagger](https://img.shields.io/badge/Docs-Swagger%20UI-85EA2D?style=flat-square&logo=swagger)
![License](https://img.shields.io/badge/License-MIT-lightgrey?style=flat-square)

---
## 🚀 Highlights

- Built scalable REST API with Spring Boot and layered architecture
- Implemented JWT-based authentication and role-based access control
- Integrated Stripe Checkout with webhook handling for real-world payments
- Designed normalized relational database with proper entity relationships
## 📋 Table of Contents

- [About](#about)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Environment Variables](#environment-variables)
- [API Endpoints](#api-endpoints)
- [Payment Flow](#payment-flow)
- [Error Handling](#error-handling)
- [Future Improvements](#future-improvements)

---

<img width="1364" height="643" alt="image" src="https://github.com/user-attachments/assets/1c3cc524-23a8-49e4-8988-e02763a02392" />

## 🖼️ System Overview

###Schema

<img width="793" height="548" alt="image" src="https://github.com/user-attachments/assets/05a6caee-e2d3-4698-b591-9d272e0c071e" />

### 🧩 Database Design (ER Diagram)

The database is designed using a relational model where core entities such as Users, Tours, and Bookings are connected using foreign key relationships.

### 🔗 Entity Relationships

1. **User → Booking (1 : Many)**
   - One user can have multiple bookings
   - Each booking belongs to exactly one user

2. **Tour → Booking (1 : Many)**
   - One tour can be booked multiple times by different users
   - Each booking is associated with one tour

3. **Tour → Location (Many : 1)**
   - Multiple tours can belong to the same location
   - Each tour is linked to one location

4. **Tour → Lodging (Many : 1)**
   - Multiple tours can use the same lodging
   - Each tour has one lodging

5. **Tour → Transport (Many : 1)**
   - Multiple tours can use the same transport
   - Each tour has one transport

6. **Tour → Meals (1 : Many)**
   - A tour can have multiple meal options

7. **Tour → Activities (1 : Many)**
   - A tour can include multiple activities

8. **Tour → Images (1 : Many)**
   - A tour can have multiple images

---

### 🧠 Design Highlights

- The schema follows **normalization principles** to avoid data duplication
- Relationships are maintained using **foreign keys**
- Separation of entities ensures **scalability and flexibility**
- Booking acts as a **bridge entity** between User and Tour


## About

Tour Booking System is a backend REST API that allows customers to browse tours, make bookings, and complete payments securely via Stripe Checkout. Admins can manage tours, locations, lodgings, and transports. JWT-based authentication secures all protected routes with role-based access control (`ROLE_ADMIN` / `ROLE_CUSTOMER`).

---

## ✨ Features

- 🔐 **JWT Authentication** — Register, login, and secure all routes with stateless JWT tokens
- 🗺️ **Tour Management** — Full CRUD for tours with location, lodging, and transport linking
- 🔍 **Tour Search & Filter** — Search by country and filter by price range
- 📅 **Booking Management** — Customers can book tours; admins can view all bookings
- 💳 **Stripe Payment Integration** — Stripe Checkout sessions with webhook handling
- 🏨 **Lodging & Transport Management** — Admin CRUD for lodgings and transports
- 📍 **Location Management** — Admin CRUD for tour locations
- 🛡️ **Role-Based Access Control** — Separate routes for `ROLE_ADMIN` and `ROLE_CUSTOMER`
- ✅ **Input Validation** — `@Valid` on all request DTOs with detailed error messages
- 🚨 **Global Exception Handling** — Centralized error responses with proper HTTP status codes
- 📖 **Swagger / OpenAPI Docs** — All endpoints documented and testable via Swagger UI

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.5 |
| Authentication | Spring Security + JWT (jjwt) |
| Database | MySQL 8.x + Spring Data JPA (Hibernate) |
| Payment | Stripe Java SDK |
| API Docs | Swagger / SpringDoc OpenAPI |
| Build Tool | Maven |
| Server | Embedded Tomcat (port `8418`) |
| Utilities | Lombok, Jakarta Validation |

---

## 📁 Project Structure

```
src/main/java/com/dhruv/tourBookingApplication/
│
├── config/                  # Security config, JWT filter
├── dto/
│   ├── request/             # LoginDto, UserRegisterDto, TourRequestDto,
│   │                          BookingRequestDto, LocationRequestDto,
│   │                          LodgingRequestDto, TransportRequestDto
│   └── response/            # JwtResponseDto, UserResponseDto, TourDetailResponseDto,
│                              TourSummaryResponseDto, BookingResponseDto,
│                              LocationResponseDto, LodgingResponseDto, TransportResponseDto
├── entities/                # User, Tour, Booking, Location, Lodging, Transport
├── enums/                   # Role (ROLE_ADMIN, ROLE_CUSTOMER), PaymentStatus (PENDING, PAID)
├── exception/               # GlobalExceptionHandler + custom exceptions
├── mapper/                  # Entity <-> DTO converters
├── repo/                    # Spring Data JPA repositories
├── rest/                    # REST Controllers (API layer)
├── service/
│   ├── impl/                # Business logic implementations
│   └── interfaces/          # Service interfaces
└── TourBookingApplication.java
```

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8+
- Stripe CLI (for local webhook testing)

### 1. Clone the repository

```bash
git clone https://github.com/DHRUV18ux/springboot-tourbooking-system.git
cd springboot-tourbooking-system
```

### 2. Set up the database

```sql
CREATE DATABASE tourBookingDB;
```

### 3. Configure environment

Copy the example config:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Create a `.env` file in the project root (see [Environment Variables](#environment-variables)).

### 4. Run the application

```bash
mvn spring-boot:run
```

API base URL:
```
http://localhost:8418/TourBookingApp
```

Swagger UI:
```
http://localhost:8418/TourBookingApp/swagger-ui/index.html
```

### 5. Start Stripe webhook listener (local testing only)

```bash
stripe listen --forward-to localhost:8418/TourBookingApp/api/webhook
```

Copy the `whsec_...` key and update `STRIPE_WEBHOOK_SECRET` in your `.env`.

---

## 🔑 Environment Variables

Create a `.env` file in the project root:

```env
DB_PASSWORD=your_mysql_password
STRIPE_SECRET=sk_test_your_stripe_key
STRIPE_WEBHOOK_SECRET=whsec_from_stripe_cli
JWT_SECRET=your_base64_encoded_jwt_secret
```

> ⚠️ Never commit your `.env` file. It is already listed in `.gitignore`.

---

## 📡 API Endpoints

> All protected endpoints require the header:
> ```
> Authorization: Bearer <your_jwt_token>
> ```

---
## 🔐 Authentication & Authorization Flow
This section explains how users authenticate and how access to resources is controlled using JWT and role-based authorization.
1. User Registration / Login
   - A new user registers using /api/auth/register
   - Existing user logs in via /api/auth/login

2. JWT Token Generation
   - On successful login, the server returns a JWT token
   - This token must be included in all protected API requests

3. Accessing Protected Routes
   - Client sends:
     Authorization: Bearer <JWT_TOKEN>

### 4. Role-Based Access Control

   **👤 CUSTOMER (ROLE_CUSTOMER):**
- View all tours
- View tour details
- Search & filter tours
- Book a tour
- View own bookings

  **👨‍💼 ADMIN (ROLE_ADMIN):**
- Create, update, delete tours
- Manage locations, lodgings, transports
- View all bookings
- View all users
- 
5. Access Restriction
   - Customers CANNOT access admin endpoints
   - Admin-only endpoints are protected using role checks

6. Logout
   - Since JWT is stateless, logout is handled on the client side by removing the stored token.
   - In short, authentication is handled using JWT, and authorization is enforced using role-based access control via Spring Security.

### 🔓 Auth — `/api/auth`

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/api/auth/register` | Register a new customer account | ❌ |
| `POST` | `/api/auth/login` | Login and receive a JWT token | ❌ |

**Register Request Body:**
```json
{
  "name": "Dhruv",
  "email": "dhruv@example.com",
  "password": "secret123",
  "contactNo": "9876543210"
}
```

**Login Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "email": "dhruv@example.com",
  "name": "Dhruv",
  "role": "ROLE_CUSTOMER"
}
```

---

### 🗺️ Tours — `/api/tours` (Public)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `GET` | `/api/tours` | Get all tours (summary list) | ❌ |
| `GET` | `/api/tours/{id}` | Get full tour details by ID | ❌ |
| `GET` | `/api/tours/search?country=India` | Search tours by country | ❌ |
| `GET` | `/api/tours/search/price?minPrice=5000&maxPrice=20000` | Filter tours by price range | ❌ |

---

### 🗺️ Tour Management — `/api/admin/tours` (Admin Only)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/api/admin/tours` | Create a new tour | ✅ Admin |
| `PUT` | `/api/admin/tours/{id}` | Update an existing tour | ✅ Admin |
| `DELETE` | `/api/admin/tours/{id}` | Delete a tour | ✅ Admin |

**Tour Request Body:**
```json
{
  "tourName": "Rajasthan Desert Safari",
  "tourDescription": "Experience the golden sands of Rajasthan...",
  "tourGuide": "Ravi Sharma",
  "startDate": "2025-12-01",
  "endDate": "2025-12-07",
  "country": "India",
  "price": 15000.0,
  "ticketAvailable": 20,
  "meals": ["Breakfast", "Dinner"],
  "activities": ["Camel Ride", "Cultural Show"],
  "tourImages": ["https://example.com/image1.jpg"],
  "locationId": 1,
  "lodgingId": 1,
  "transportId": 1
}
```

---

### 📅 Bookings — `/api/bookings` (Customer)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/api/bookings` | Create a booking — returns Stripe checkout URL | ✅ Customer |
| `GET` | `/api/bookings/my` | Get all bookings of the logged-in customer | ✅ Customer |

**Booking Request Body:**
```json
{
  "tourId": 1,
  "numberOfTickets": 2
}
```

**Booking Response:**
```json
{
  "id": 10,
  "tourName": "Rajasthan Desert Safari",
  "numberOfTickets": 2,
  "totalPrice": 30000.0,
  "paymentStatus": "PENDING",
  "bookingDate": "2025-11-20",
  "stripeSessionUrl": "https://checkout.stripe.com/pay/cs_test_..."
}
```

---

### 📅 Booking Management — Admin

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `GET` | `/api/admin/bookings` | Get all bookings in the system | ✅ Admin |
| `GET` | `/api/admin/bookings/tour/{tourId}` | Get all bookings for a specific tour | ✅ Admin |

---

### 💳 Stripe Webhook — `/api/webhook`

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/api/webhook` | Receives Stripe payment events (called by Stripe automatically) | ❌ |

---

### 📍 Location Management — `/api/admin/locations` (Admin)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/api/admin/locations` | Add a new location | ✅ Admin |
| `GET` | `/api/admin/locations` | Get all locations | ✅ Admin |
| `GET` | `/api/admin/locations/{id}` | Get location by ID | ✅ Admin |
| `PUT` | `/api/admin/locations/{id}` | Update a location | ✅ Admin |
| `DELETE` | `/api/admin/locations/{id}` | Delete a location | ✅ Admin |

---

### 🏨 Lodging Management — `/api/admin/lodgings` (Admin)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/api/admin/lodgings` | Add a new lodging | ✅ Admin |
| `GET` | `/api/admin/lodgings` | Get all lodgings | ✅ Admin |
| `GET` | `/api/admin/lodgings/{id}` | Get lodging by ID | ✅ Admin |
| `PUT` | `/api/admin/lodgings/{id}` | Update a lodging | ✅ Admin |
| `DELETE` | `/api/admin/lodgings/{id}` | Delete a lodging | ✅ Admin |

---

### 🚌 Transport Management — `/api/admin/transports` (Admin)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/api/admin/transports` | Add a new transport | ✅ Admin |
| `GET` | `/api/admin/transports` | Get all transports | ✅ Admin |
| `GET` | `/api/admin/transports/{id}` | Get transport by ID | ✅ Admin |
| `PUT` | `/api/admin/transports/{id}` | Update a transport | ✅ Admin |
| `DELETE` | `/api/admin/transports/{id}` | Delete a transport | ✅ Admin |

---

### 👤 User Management — `/api/admin/users` (Admin)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `GET` | `/api/admin/users/all` | Get all registered users | ✅ Admin |
| `GET` | `/api/admin/users/{email}` | Get a user by email | ✅ Admin |

---

## 💳 Payment Flow

```
1. Customer  →  POST /api/bookings
             →  Booking created with status: PENDING
             →  Stripe Checkout Session created
             →  stripeSessionUrl returned in response

2. Customer  →  Opens stripeSessionUrl in browser
             →  Completes payment on Stripe

3. Stripe    →  POST /api/webhook  (automatic)
             →  Signature verified with STRIPE_WEBHOOK_SECRET
             →  checkout.session.completed event processed
             →  Booking status updated to: PAID
             →  Tour ticketAvailable decremented
             →  Tour ticketsSold incremented
```

---

## 🚨 Error Handling

All errors return a consistent JSON format:

```json
{
  "status": 404,
  "message": "Tour with the entered id : 5 does not present",
  "timeStamp": "2025-11-20T10:30:00"
}
```

| Status | Scenario |
|--------|----------|
| `400` | Validation errors, invalid tour dates, insufficient tickets |
| `401` | Missing or invalid JWT token |
| `402` | Stripe payment session creation failed |
| `403` | Access denied (wrong role) |
| `404` | Tour / Booking / User / Location / Lodging / Transport not found |
| `409` | User already exists with same email or contact number |
| `500` | Unexpected internal server error |

---

## 🔮 Future Improvements

- [ ] Booking cancellation with Stripe refund support
- [ ] Email notifications on booking confirmation (JavaMailSender / SendGrid)
- [ ] Tour image upload via AWS S3
- [ ] Pagination and sorting for tour listings (`Pageable`)
- [ ] Admin dashboard with revenue and booking analytics
- [ ] Tour ratings and reviews by customers
- [ ] Unit and integration tests (JUnit 5 + Mockito)
- [ ] Deploy to AWS / Railway / Render
- [ ] Add `@Transactional` on service write operations
- [ ] Replace for-loops in services with Java Streams

---

## 👨‍💻 Author

**Dhruv** — [@DHRUV18ux](https://github.com/DHRUV18ux)

---

## 📄 License

This project is licensed under the MIT License.
