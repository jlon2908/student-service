# 🎓 Student Service

Este proyecto es un microservicio para la gestión de estudiantes, desarrollado con **Java 17**, **Spring Boot 3.4.4**, **Arquitectura Hexagonal** y documentado con **Swagger/OpenAPI**.

---

## 📌 Características

- CRUD de estudiantes.
- Arquitectura limpia y desacoplada.
- Validación con Jakarta Validation.
- Documentación automática con Swagger UI.
- Base de datos:  PostgreSQL.
- Listo para integrar con otros microservicios.

---

## 📦 Tecnologías

- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- MapStruct
- Lombok
- Swagger OpenAPI (springdoc-openapi 2.7.0)
-  PostgreSQL
- Maven

---

## 🛠️ Arquitectura Hexagonal

- `domain`: lógica del negocio.
- `application`: casos de uso y puertos.
- `infrastructure`: adaptadores de entrada (REST) y salida (JPA).

---

