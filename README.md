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
   ```
4. The API will be available at `http://localhost:8080`
5. The H2 Database console is available at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:petstoredb`, User: `sa`, Password: [blank])

## Architectural Decisions

* **Dynamic Attributes (JSON):** Products can have anywhere from just a few attributes to 50-200 distinct attributes depending on their type. I utilized an H2 JSON column mapped via Hibernate's `@JdbcTypeCode(SqlTypes.JSON)`. This maps seamlessly to a Java `Map<String, Object>`, providing high flexibility without sacrificing schema integrity for the core fields (price, name, etc.).

* **In-Memory Database (H2):** Chosen specifically so reviewers do not need to configure a local PostgreSQL or Oracle instance to run the application.

* **Database Migrations:** Managed via Liquibase on startup to ensure a consistent schema.

* **Exclusions:** Advanced features like Spring Security, caching, and Docker deployments were deliberately excluded to strictly adhere to the assessment's "What We're NOT Looking For" guidelines.

## Implemented:

*GET /products* - List all products 

*POST /products* - Create a new product 

*PUT /products/{id}* - Update an existing product 

*DELETE /products/{id}* - Delete a product

## API Usage Examples

### 1. Producers (Bonus Implemented)
**Create a Producer**
```bash
curl -X POST -H "Content-Type: application/json" -d '{"name": "Royal Canin Europe"}' http://localhost:8080/producers
```

**List Producers**
```bash
curl http://localhost:8080/producers
```

### 2. Products
**Create a Complex Product**
*Note: Requires an existing `producerId`.*
```bash
curl -X POST -H "Content-Type: application/json" -d '{"name": "Premium Grain-Free Salmon Kibble", "description": "High-protein dry dog food", "price": 45.99, "attributes": {"weight_kg": 12.5, "flavor": "Salmon and Broccoli", "life_stage": "Adult", "grain_free": true}}' "http://localhost:8080/products?producerId=1"
```
**List All Products**
```bash
curl http://localhost:8080/products
```

**Filter Products by Producer (Bonus Implemented)**
```bash
curl "http://localhost:8080/products?producerId=1"
```