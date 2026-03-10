# Pet Store Catalog API

This is a REST API for a product catalog management system. It manages producers and their associated products, specifically handling items with highly variable attribute structures.

## Setup and Running Instructions

This project is designed to run completely standalone with zero external dependencies.

**Prerequisites:**
- Java 21 or higher
- Maven (Wrapper included)

**To run the application:**
1. Clone the repository
2. Open a terminal in the root directory
3. Run the following command:
   ```bash
   ./mvnw spring-boot:run
4. The API will be available at http://localhost:8080
5. The H2 Database console is available at http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:petstoredb, User: sa, Password: [blank])

## Decisions

*Dynamic Attributes (JSON):* Products can have anywhere from just a few attributes to 50-200 distinct attributes (dimensions, certifications, etc.) depending on their type. I utilized an H2 JSON column. Using Hibernate 6's @JdbcTypeCode(SqlTypes.JSON), this maps seamlessly to a Java Map<String, Object>, providing high flexibility without sacrificing schema integrity for the core fields (price, name, etc.).

*In-Memory Database (H2):* Chosen specifically so it doesn't need to configure a local PostgreSQL or Oracle instance just to run the app.

*Database Migrations:* Managed via Liquibase on startup

*Exclusions:* Advanced features like Spring Security, caching, and Docker deployments were deliberately excluded to keep the application focused on the core domain.

## Core API Endpoints (Pending Implementation)

*GET /products* - List all products 

*POST /products* - Create a new product 

*PUT /products/{id}* - Update an existing product 

*DELETE /products/{id}* - Delete a product