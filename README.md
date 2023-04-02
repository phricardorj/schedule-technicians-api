![idJava API](./stapi_logo.png 'STAPI - Scheduling Technicians API')
------------------------
This API allows the registration of technicians and customers to schedule a technical
visit at the customer's address.

### Techs
- [Spring Boot 3.0.0](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0.0-M1-Release-Notes)
- Java 20
- Lombok
- MySQL 8.0 / Flyway
- Maven
- [mapstruct](https://mapstruct.org/)
- [JUnit 5](https://junit.org/junit5/)
- [SpringDoc OpenAPI 3](https://springdoc.org/) and Swagger UI 
- Docker

### Development
````shell
docker-compose up -d
````

# To do
```
/api/v1/company
- [✔️][GET] Get all registered companies
- [✔️][GET] Get Company by Id
- [✔️][POST] Register Company
- [✔️][PATCH] Edit Company by Id
- [✔️][DELETE] Delete Company by Id
-------------------
/api/v1/technician
- [✔️][GET] Get Technician by Enrollment
- [✔️][POST] Register Technician
- [✔️][PATCH] Edit Technician by Enrollment
- [✔️][DELETE] Delete Customer via Enrollment
-------------------
/api/v1/customer
- [✔️][GET] Get Customer by Id
- [✔️][POST] Register Customer
- [✔️][PATCH] Edit Customer by Id
- [✔️][DELETE] Delete Customer by Id
-------------------
/api/v1/scheduling
- [✔️][GET] Get Scheduling by Service Order (OS)
- [✔️][POST] Register Scheduling
- [✔️][PATCH] Reschedule Scheduling by Service Order (OS)
- [✔️][DELETE] Cancel Scheduling by Service Order (OS)
```

### [PT-BR] Substituições do Spring Boot 2.x
- Classes, métodos e propriedades que foram obsoletos no Spring Boot 2.x foram removidos nesta versão.
- Mudanças nos Requisitos Mínimos
- Atualizações de dependência
- Nas novas notas de lançamento do Spring boot 3, eles informam que esta versão usará Jakarta EE 9 (libs nomeadas como jakarta) em vez de JEE (libs nomeadas como javax).
