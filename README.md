# 📚 Virtual Bookstore - E-commerce Web Application

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Framework-Spring%20Boot%203-green)
![Thymeleaf](https://img.shields.io/badge/Frontend-Thymeleaf-blue)
![Status](https://img.shields.io/badge/Status-Completed-success)

> [cite_start]**University:** DIT University [cite: 11]  
> [cite_start]**Course:** Server-Side Engineering (CSF373) [cite: 9]  
> [cite_start]**Project Type:** Mini Project [cite: 8]

## 📖 Introduction

[cite_start]**The Virtual Bookstore** is a full-featured e-commerce web application developed using the Spring framework[cite: 25]. Traditional bookstores face limitations in accessibility and inventory. [cite_start]This project addresses those needs by creating a user-friendly virtual platform that provides both a wide selection and a curated, engaging shopping experience[cite: 22, 23].

[cite_start]The goal is to provide a seamless digital space for users to explore, search, select, and purchase books securely[cite: 26, 28].

## 🚀 Features

* [cite_start]**User Management:** Secure registration (with hashed passwords) and authentication using Spring Security[cite: 30, 42].
* [cite_start]**Book Catalog:** Public browsing of book listings with cover images, descriptions, and pricing[cite: 43].
* [cite_start]**Advanced Search:** Filter books by title, author, or genre[cite: 43].
* [cite_start]**Shopping Cart:** Dynamic cart management allowing users to add items, update quantities, and view real-time totals[cite: 44, 45].
* [cite_start]**Checkout Process:** A streamlined checkout simulation for confirming orders[cite: 46].
* [cite_start]**Recommendation System:** Suggests books based on genre preferences and popularity[cite: 1025].
* [cite_start]**Reviews & Ratings:** Users can leave ratings and textual reviews for books[cite: 776].

## 🛠️ Tech Stack

| Category | Technology |
| :--- | :--- |
| **Backend** | [cite_start]Spring Boot 3 [cite: 53] |
| **Web Framework** | [cite_start]Spring Web (MVC) [cite: 54] |
| **Security** | [cite_start]Spring Security [cite: 55] |
| **Database** | [cite_start]H2 (Dev) / PostgreSQL (Prod) [cite: 64, 65] |
| **ORM** | [cite_start]Spring Data JPA [cite: 56] |
| **Frontend** | [cite_start]Thymeleaf Template Engine [cite: 66] |
| **Build Tool** | [cite_start]Maven [cite: 67] |

## 🏗️ System Architecture

[cite_start]The application follows a maintainable **n-tier architecture**[cite: 37, 70]:

1.  [cite_start]**Controller Layer:** Handles HTTP requests and maps to views (e.g., `BookController`, `HomeController`)[cite: 71].
2.  [cite_start]**Service Layer:** Contains business logic and transaction management (e.g., `BookService`, `CartService`)[cite: 72].
3.  [cite_start]**Repository Layer:** Manages data persistence via Spring Data JPA interfaces[cite: 73].

### Database Entities
* [cite_start]**User:** Credentials and role management[cite: 76].
* [cite_start]**Book:** Inventory details (ISBN, price, stock)[cite: 77].
* [cite_start]**Order:** Transaction records and status[cite: 79].
* [cite_start]**Review:** User feedback and ratings[cite: 111].

## 📸 Screenshots

### 1. Home Page
*Featuring a hero section and featured books.*
![Home Page](./screenshots/home.png)
[cite_start]*(Place your screenshot of the Welcome page here)* [cite: 1911]

### 2. Book Catalog & Search
*Search functionality filtering by genre (e.g., Fiction).*
![Search Results](./screenshots/search.png)
[cite_start]*(Place your screenshot of the search results here)* [cite: 2029]

### 3. Book Details
*Detailed view with "Add to Cart" functionality and stock status.*
![Book Details](./screenshots/details.png)
[cite_start]*(Place your screenshot of The Hobbit/Harry Potter details here)* [cite: 2041]

### 4. User Authentication
*Secure Login and Registration forms.*
![Login Register](./screenshots/login.png)
[cite_start]*(Place your screenshot of the Login/Register forms here)* [cite: 2077]

## ⚙️ Installation & Usage

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/virtual-bookstore.git](https://github.com/your-username/virtual-bookstore.git)
    ```

2.  [cite_start]**Navigate to the project directory:** [cite: 1905]
    ```bash
    cd virtual-bookstore
    ```

3.  [cite_start]**Build and Run:** [cite: 1909]
    ```bash
    mvn clean spring-boot:run
    ```

4.  **Access the Application:**
    Open your web browser and go to:
    [cite_start]`http://localhost:8086` [cite: 1895]


---
*© 2024 Virtual Bookstore. [cite_start]All rights reserved.* [cite: 1488]