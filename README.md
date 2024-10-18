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
5. [WebSocket Commands](#websocket-commands)
6. [Running the Project](#running-the-project)


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
