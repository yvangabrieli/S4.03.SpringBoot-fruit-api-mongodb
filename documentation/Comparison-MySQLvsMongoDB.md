# 📊 Level 2 vs Level 3 Comparison
## Spring Boot REST API: MySQL vs MongoDB

| Feature                  | Level 2 (MySQL)                     | Level 3 (MongoDB)                   |
|--------------------------|------------------------------------|------------------------------------|
| Database Type            | Relational                        | Document (NoSQL)                   |
| Entities                 | Fruit, Provider                     | Order, OrderItem                   |
| Relationships            | 1:N (Provider → Fruit)             | Embedded Documents (Order → Items) |
| Schema                    | Fixed, defined tables             | Flexible, JSON-like documents      |
| ID Generation             | AUTO_INCREMENT                     | ObjectId (String)                  |
| DTOs                     | Records/Classes                     | Records                            |
| Validation               | Bean Validation                     | Bean Validation                     |
| Transactions             | Yes                                | Limited (single doc atomic)        |
| Queries                  | JPQL, SQL                           | Repository + QueryDSL optional     |
| Advantages               | ACID, normalized                   | Flexible, easy scaling             |
| Use Case                  | Inventory management                | Order tracking with variable items |
