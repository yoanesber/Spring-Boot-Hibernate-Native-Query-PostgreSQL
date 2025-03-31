# Native Query PostgreSQL

## 📖 Overview
This project is **a RESTful API** built using **Spring Boot** that manages **Netflix Shows**. The API provides CRUD operations using **Spring Data JPA with Hibernate** as the default JPA provider. The database used is **PostgreSQL**, and the project leverages **Lombok** to reduce boilerplate code.  

**A key aspect** of this project is the use of **Native Query** in JPA Hibernate at the repository layer. Unlike **JPQL (Java Persistence Query Language)**, which is an object-oriented query language, native queries **allow direct execution of SQL queries** on the database. This provides **flexibility** to utilize complex SQL operations, database-specific functions, or optimized queries that **might not be feasible using JPQL**. In this project, the `@Query` annotation with `nativeQuery = true` is used to execute raw SQL queries directly. This ensures greater control over database interactions and can improve performance in specific scenarios where JPQL is not efficient.  

---

## 🤖 Tech Stack
The technology used in this project are:
- `Spring Boot Starter Web` – Building RESTful APIs or web applications
- `PostgreSQL` – Database for persisting Netflix Shows
- `Hibernate` – Simplifying database interactions
- `Lombok` – Reducing boilerplate code
---

## 🏗️ Project Structure
The project is organized into the following package structure:
```bash
native-query-postgresql/
│── src/main/java/com/yoanesber/spring/hibernate/native_query_postgresql/
│   ├── 📂controller/            # Exposes REST API endpoints for handling requests and responses
│   ├── 📂dto/                   # Data Transfer Objects (DTOs) for request/response payloads
│   ├── 📂entity/                # Entity classes representing database tables
│   ├── 📂repository/            # JPA repositories for database access
│   ├── 📂service/               # Business logic layer
│   │   ├── 📂impl/              # Implementation of services
```
---

## ⚙ Environment Configuration
Configuration values are stored in `.env.development` and referenced in `application.properties`.  
Example `.env.development` file content:  
```properties
# Application properties
APP_PORT=8081
SPRING_PROFILES_ACTIVE=development
 
# Database properties
SPRING_DATASOURCE_PORT=5432
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password
SPRING_DATASOURCE_DB=your_db
SPRING_DATASOURCE_SCHEMA=your_schema
```

Example `application.properties` file content:  
```properties
# Application properties
spring.application.name=native-query-postgresql
server.port=${APP_PORT}
spring.profiles.active=${SPRING_PROFILES_ACTIVE}

# Database properties
spring.datasource.url=jdbc:postgresql://localhost:${SPRING_DATASOURCE_PORT}/${SPRING_DATASOURCE_DB}?currentSchema==${SPRING_DATASOURCE_SCHEMA}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
```
---

## 💾 Database Schema (DDL – PostgreSQL)
The following is the database schema for the PostgreSQL database used in this project:  
```sql
CREATE SCHEMA your_schema;

CREATE SEQUENCE your_schema.id_netflix_shows_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE your_schema.netflix_shows (
	id int8 NOT NULL DEFAULT nextval('your_schema.id_netflix_shows_seq'::regclass),
	"type" varchar(7) NOT NULL,
	title text NOT NULL,
	director text NULL,
	cast_members text NULL,
	country varchar(60) NOT NULL,
	date_added date NOT NULL,
	release_year int4 NOT NULL,
	rating int4 NULL,
	duration_in_minute int4 NULL,
	listed_in text NULL,
	description text NULL,
	CONSTRAINT netflix_shows_pkey PRIMARY KEY (id),
	CONSTRAINT netflix_shows_type_check CHECK (((type)::text = ANY (ARRAY[('MOVIE'::character varying)::text, ('TV_SHOW'::character varying)::text])))
);
```
---

## 🛠️ Installation & Setup
A step by step series of examples that tell you how to get a development env running.
1. Ensure you have **Git installed on your Windows** machine, then clone the repository to your local environment:
```bash
git clone https://github.com/yoanesber/Spring-Boot-Hibernate-Native-Query-PostgreSQL.git
cd Spring-Boot-Hibernate-Native-Query-PostgreSQL
```

2. Set up PostgreSQL
- Run the provided DDL script to set up the database schema
- Configure the connection in `.env.development` file:
```properties
# Database properties
SPRING_DATASOURCE_PORT=5432
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password
SPRING_DATASOURCE_DB=your_db
SPRING_DATASOURCE_SCHEMA=your_schema
```

3. Run the application locally
- Make sure PostgreSQL is running, then execute: 
```bash
mvn spring-boot:run
```

4. Now, API is available at:  
```bash
http://localhost:8081/ 
```

You can test the API using: Postman (Desktop/Web version) or cURL

---

## 🌐 API Endpoints
The REST API provides a set of endpoints to manage Netflix shows, allowing clients to perform CRUD operations (Create, Read, Update, Delete). Each endpoint follows RESTful principles and accepts/returns JSON data. Below is a list of available endpoints along with sample requests.

- `GET` http://localhost:8081/api/v1/netflix-shows - Retrieve all Netflix Shows.  
- `GET` http://localhost:8081/api/v1/netflix-shows/1 - Retrieve a specific Netflix Show by ID.  

**Successful Response:**
```json
{
    "statusCode": 200,
    "timestamp": "2025-03-31T10:59:25.4794899",
    "message": "NetflixShows retrieved successfully",
    "data": {
        "id": 1,
        "showType": "MOVIE",
        "title": "Sankofa",
        "director": "Haile Gerima",
        "castMembers": "Kofi Ghanaba, Oyafunmike Ogunlano, Alexandra Duah, Nick Medley, Mutabaruka, Afemo Omilami, Reggie Carter, Mzuri, Oliver",
        "country": "United States",
        "dateAdded": "2021-09-24",
        "releaseYear": 2024,
        "rating": 10,
        "durationInMinute": 90,
        "listedIn": "Comedy",
        "description": "A woman adjusting to life after a loss contends with a feisty bird that's taken over her garden — and a husband who's struggling to find a way forward."
    }
}
```

- `POST` http://localhost:8081/api/v1/netflix-shows - Create a new Netflix Show.  

**Request Body:**
```json
{
    "showType":"TV_SHOW",
    "title":"The Smart Money Woman",
    "director":"Bunmi Ajakaiye",
    "castMembers":"Osas Ighodaro, Ini Dima-Okojie, Kemi Lala Akindoju, Toni Tones, Ebenezer Eno, Eso Okolocha DIke, Patrick Diabuah, Karibi Fubara, Temisan Emmanuel, Timini Egbuson",
    "country":"India",
    "dateAdded":"2021-09-16",
    "releaseYear":2020,
    "rating":5,
    "durationInMinute":90,
    "listedIn":"Romantic TV Shows",
    "description":"Five glamorous millennials strive for success as they juggle careers, finances, love and friendships. Based on Arese Ugwu's 2016 best-selling novel."
}
```

**Successful Response:**
```json
{
    "statusCode": 201,
    "timestamp": "2025-03-31T11:00:44.0278893",
    "message": "NetflixShows created successfully",
    "data": null
}
```

- `PUT` http://localhost:8081/api/v1/netflix-shows/1 - Update an existing Netflix Show.  

**Request Body:**
```json
{
    "showType":"TV_SHOW",
    "title":"The Smart Money Woman",
    "director":"Bunmi Ajakaiye",
    "castMembers":"Osas Ighodaro, Ini Dima-Okojie, Kemi Lala Akindoju, Toni Tones, Ebenezer Eno, Eso Okolocha DIke, Patrick Diabuah, Karibi Fubara, Temisan Emmanuel, Timini Egbuson",
    "country":"India",
    "dateAdded":"2021-09-16",
    "releaseYear":2020,
    "rating":5,
    "durationInMinute":90,
    "listedIn":"TV Comedies",
    "description":"Five glamorous millennials strive for success as they juggle careers, finances, love and friendships. Based on Arese Ugwu's 2016 best-selling novel."
}
```

**Successful Response:**
```json
{
    "statusCode": 200,
    "timestamp": "2025-03-31T11:01:48.4959796",
    "message": "NetflixShows updated successfully",
    "data": null
}
```

- `DELETE` http://localhost:8081/api/v1/netflix-shows/1 - Delete a Netflix Show.  
```json
{
    "statusCode": 200,
    "timestamp": "2025-02-27T21:35:17.0110773",
    "message": "NetflixShows deleted successfully",
    "data": null
}
```