📚 Library Management System
🚀 Project Overview
Library Management System is a RESTful backend application developed using Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL.
The application is designed to automate library operations such as managing books, users, libraries, and addresses. It provides APIs for book borrowing and returning, searching books, assigning books to libraries, and managing user information.
The project follows a layered architecture and demonstrates enterprise-level backend development concepts such as DTO Mapping, Exception Handling, JPA Relationships, Swagger Documentation, and Generic API Responses.
________________________________________
🎯 Project Objectives
•	Automate library operations
•	Reduce manual record management
•	Manage books and users efficiently
•	Track borrowed and returned books
•	Provide searchable APIs
•	Demonstrate Spring Boot backend development concepts
________________________________________
✨ Features
Address Management
•	Create Address
•	Update Address
•	Delete Address
•	Find Address By ID
•	Display All Addresses
•	Partial Address Update
Book Management
•	Create Book
•	Update Book
•	Delete Book
•	Find Book By ID
•	Display All Books
•	Search Book By Author
•	Search Book By Title
•	Search Book By Author And Title
•	Partial Book Update
Library Management
•	Create Library
•	Update Library
•	Delete Library
•	Find Library By ID
•	Display All Libraries
•	Add Books To Library
•	Display Books Available In Library
•	Partial Library Update
User Management
•	Create User
•	Update User
•	Delete User
•	Find User By ID
•	Display All Users
•	Borrow Book
•	Return Book
•	Partial User Update
Exception Handling
•	Global Exception Handling
•	Custom Exception Classes
•	Structured Error Responses
API Documentation
•	Swagger/OpenAPI Integration
________________________________________
🛠️ Technology Stack
Backend
•	Java 17
•	Spring Boot
•	Spring MVC
•	Spring Data JPA
•	Hibernate ORM
Database
•	MySQL
Build Tool
•	Maven
API Documentation
•	Swagger/OpenAPI
Utilities
•	Lombok
•	ModelMapper
________________________________________
🏗️ Architecture
The application follows a layered architecture:
Controller Layer ↓ Service Layer ↓ Repository Layer ↓ Database Layer
Controller Layer
Handles HTTP Requests and Responses.
Service Layer
Contains business logic and validations.
Repository Layer
Performs database operations using Spring Data JPA.
Database Layer
Stores application data in MySQL.
________________________________________
📂 Project Structure
com.lms
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
├── serviceimplementation
├── until
└── LibraryManagementSystemApplication
________________________________________
🗄️ Database Design
Address Entity
Stores:
•	Address ID
•	House Number
•	Area
•	City
•	State
•	Country
•	Pincode
User Entity
Stores:
•	User ID
•	User Name
•	Email
•	Phone Number
Book Entity
Stores:
•	Book ID
•	Title
•	Author
•	Borrow Status
•	Borrowed Time
•	Return Time
Library Entity
Stores:
•	Library ID
•	Library Name
•	Phone Number
________________________________________
🔗 Entity Relationships
Library ↔ Address
One Library has One Address
@OneToOne
________________________________________
User ↔ Address
One User has One Address
@OneToOne
________________________________________
Library ↔ Books
One Library can contain Multiple Books
@OneToMany
________________________________________
Book ↔ User
Many Books can be Borrowed by One User
@ManyToOne
________________________________________
🔄 Application Workflow
Step 1: Create Address
↓
Step 2: Create Library using Address ID
↓
Step 3: Create Books
↓
Step 4: Add Books to Library
↓
Step 5: Create Users
↓
Step 6: Borrow Books
↓
Step 7: Return Books
________________________________________
📡 REST API Endpoints
Address APIs
POST /address
GET /address/{id}
GET /address
PUT /address
PATCH /address/{id}
DELETE /address/{id}
________________________________________
Book APIs
POST /book
GET /book/{id}
GET /book
GET /book/author/{author}
GET /book/title/{title}
GET /book/author/{author}/title/{title}
PUT /book
PATCH /book/{id}
DELETE /book/{id}
________________________________________
Library APIs
POST /library/{addressId}
GET /library/{id}
GET /library
PUT /library
PATCH /library/{id}
DELETE /library/{id}
POST /library/{libraryId}/book/{bookId}
GET /library/books/{libraryId}
________________________________________
User APIs
POST /user/{addressId}
GET /user/{id}
GET /user
PUT /user
PATCH /user/{id}
DELETE /user/{id}
POST /user/{userId}/borrow/{bookId}
POST /user/{userId}/return/{bookId}
________________________________________
⚠️ Exception Handling
Implemented Global Exception Handling using:
@ControllerAdvice
Custom Exceptions:
•	AddressIdNotFoundException
•	AddressNotFoundException
•	BookIdNotFoundException
•	BookNotFoundException
•	LibraryIdNotFoundException
•	LibraryNotFoundException
•	UserIdNotFoundException
•	UserNotFoundException
•	BookUnableToBorrowException
•	BookUnableToReturnException
•	BookUnableToAddLibraryException
________________________________________
📄 API Response Structure
All APIs return a generic response format:
{ “statusCode”: 200, “message”: “Success”, “data”: {} }
Benefits:
•	Consistent API Response
•	Better Frontend Integration
•	Easy Error Handling
________________________________________
📖 Swagger Documentation
Run the application and open:
http://localhost:8080/swagger-ui/index.html
Swagger helps:
•	Backend Developers
•	Frontend Developers
•	Test Engineers
to test APIs easily.
________________________________________
🎓 Concepts Learned
Through this project I gained hands-on experience in:
•	Core Java
•	OOP Concepts
•	Collections Framework
•	Exception Handling
•	Java Generics
•	SQL
•	MySQL
•	Hibernate
•	Spring Boot
•	Spring Data JPA
•	REST API Development
•	DTO Pattern
•	Repository Pattern
•	Service Layer Pattern
•	Layered Architecture
•	Dependency Injection
•	Swagger Documentation
•	ModelMapper
________________________________________
🔮 Future Enhancements
•	Spring Security
•	JWT Authentication
•	Role-Based Authorization
•	Pagination
•	Sorting
•	React Frontend
•	Docker Deployment
•	Cloud Deployment
________________________________________
📸 Project Screenshots
• Swagger Documentation
• Book Management APIs
• Library Management APIs
• User Management APIs
• Borrow Book Response
• Return Book Response
________________________________________
👨‍💻 Developer
Gunaseelan Murugesan
Java Full Stack Developer
📧 gunaseelan05221@gmail.com
📱 7418708712
📍 Dindigul, Tamil Nadu, India
Connect With Me
GitHub:
https://github.com/guna05221
