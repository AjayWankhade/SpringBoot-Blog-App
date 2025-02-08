# 📝 Spring Boot Blog App 🚀

> A full-stack **Spring Boot** blog application with **RESTful APIs** for users, posts, categories, comments, authentication, and more!

## 📌 Features
✅ User Authentication (Register/Login with JWT)  
✅ Create, Read, Update, Delete (CRUD) APIs for Users, Posts, and Categories  
✅ File Upload Support (Image Upload)  
✅ Sorting & Searching APIs for Efficient Queries  
✅ Pagination for Large Data Handling  
✅ One-to-Many & Many-to-One Relationships  
✅ Swagger Documentation for API Testing  
✅ Environment Profiles (dev/prod)  
✅ Hosted on AWS Elastic Beanstalk & RDS MySQL  

## 🚀 Technologies Used
- **Spring Boot** - Backend Framework  
- **Spring Security & JWT** - Authentication  
- **Spring Data JPA** - Database Handling  
- **MySQL** - Database  
- **Maven** - Dependency Management  
- **Swagger UI** - API Documentation  
- **AWS Elastic Beanstalk** - Hosting  
- **AWS RDS MySQL** - Cloud Database  

## 🎯 API Endpoints

### 🔑 **Authentication APIs**
- `POST /api/auth/register` → Register a new user  
- `POST /api/auth/login` → Login and receive JWT  

### 📝 **Post APIs**
- `GET /api/posts` → Get all posts  
- `POST /api/posts` → Create a new post  
- `PUT /api/posts/{id}` → Update a post  
- `DELETE /api/posts/{id}` → Delete a post  

### 📂 **Category APIs**
- `GET /api/categories` → Get all categories  
- `POST /api/categories` → Create a new category  

### 💬 **Comment APIs**
- `POST /api/comments` → Add a comment  
- `DELETE /api/comments/{id}` → Delete a comment  

## 📄 Swagger API Documentation
Easily test APIs via **Swagger UI**.  
**URL:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  

## 🛠️ Setup Instructions
### 🔹 **Clone the Repository**
```bash
git clone (https://github.com/AjayWankhade/SpringBoot-Blog-App)
cd SpringBoot-Blog-App
