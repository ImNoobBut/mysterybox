# Mystery Box Marketplace Application

This is a **Spring Boot** application that provides a marketplace for purchasing mystery boxes containing treasures. Users can register, log in, and purchase mystery boxes, and their credits will be deducted accordingly. The application supports referral codes, JWT-based authentication, and integrates with a MySQL database.

## Features
- **User Registration** with optional referral codes
- **JWT Authentication** for secure login and session management
- **Mystery Box Purchase System** with random treasures
- **Referral System**: Users can refer others and earn rewards
- **WebSocket** support for real-time updates on available treasure stock
- **MySQL Integration** for persistent data storage

## Table of Contents
1. [Technologies Used](#technologies-used)
2. [Getting Started](#getting-started)
3. [Database Schema](#database-schema)
4. [API Endpoints](#api-endpoints)
5. [Running the Project](#running-the-project)


## Technologies Used
- **Java 23**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **Spring Security with JWT**
- **MySQL** (Free MySQL Hosting)
- **Hibernate ORM**
- **Maven** for dependency management

## Getting Started

### Prerequisites

Before you can run the project, make sure you have the following installed:

- Java 17 or later
- Maven
- MySQL Database
## Data Schema
```bash
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    referral_code VARCHAR(10) UNIQUE NOT NULL,
    referrer_id BIGINT,
    credits DECIMAL(10,2) DEFAULT 1000.00,
    member_level INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (referrer_id) REFERENCES users(id)
);

CREATE TABLE treasure_types (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    initial_quantity INT DEFAULT 100,
    remaining_quantity INT DEFAULT 100,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE mystery_purchases (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    treasure_type_id BIGINT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    purchased_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (treasure_type_id) REFERENCES treasure_types(id)
);

CREATE TABLE credit_transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    transaction_type ENUM('PURCHASE', 'REFUND', 'BONUS', 'INITIAL') NOT NULL,
    reference_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```
## API Endpoints
### Authentication
- POST /api/v1/auth/register: Register a new user.
- POST /api/v1/auth/login: Log in and receive a JWT token.
### User
- GET /api/v1/users/balance: Get the user's current balance and purchase history.
### Marketplace
- GET /api/v1/market/inventory: Get the list of available treasures and their quantities.
- POST /api/v1/market/purchase: Purchase a mystery box.
### Referral
- GET /api/v1/referral/validate: Validate a referral code.

### Installation Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/ImNoobBut/mysterybox.git
   cd mysterybox

2. Install the required dependencies:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
