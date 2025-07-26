# 🧾 Cliente Microservicio - Spring Boot + Kafka + GCP

Este proyecto es un microservicio diseñado para gestionar el registro y análisis de clientes. Está construido con Java, Spring Boot, arquitectura hexagonal y mensajería asíncrona con Kafka, y está listo para ser desplegado en producción en Google Cloud Platform (GCP).

---

## 🚀 Funcionalidades

- Registrar nuevos clientes
- Listar clientes
- Calcular métricas (promedio y desviación estándar de edad)
- Obtener cliente por ID con fecha estimada de esperanza de vida
- Publicación asíncrona de eventos `ClienteCreadoEvent` a Kafka

---

## 🧱 Arquitectura

- **Java 17**, **Spring Boot 3**
- **Arquitectura hexagonal**
- **Kafka** para eventos asincrónicos
- **PostgreSQL** como base de datos
- **Swagger** para documentación de la API
- **Actuator** para monitoreo y health checks
- **Docker** y **Docker Compose** para desarrollo local
- **GCP Cloud Run** para despliegue productivo

---

## 🧪 Pruebas

- Pruebas unitarias con **JUnit 5** y **Mockito**
- Pruebas de controlador con `@WebMvcTest`
- Verificación automática en CI (GitHub Actions)

---

## 🐳 Despliegue local (Docker)

```bash
mvn clean package -DskipTests
docker-compose up --build
```

Servicios incluidos:
- challenge-app (Spring Boot app)
- Kafka + Zookeeper
- PostgreSQL

---

## ☁️ Despliegue en producción - Google Cloud Run

### 1. Construir imagen Docker y subir a GCP

```bash
docker build -t gcr.io/YOUR_PROJECT_ID/challenge-service .
docker push gcr.io/YOUR_PROJECT_ID/challenge-service
```

### 2. Desplegar con gcloud

```bash
gcloud run deploy challenge-service \
  --image gcr.io/YOUR_PROJECT_ID/challenge-service \
  --platform managed \
  --region us-central1 \
  --allow-unauthenticated \
  --set-env-vars SPRING_PROFILES_ACTIVE=prod
```

---

## 🔄 Mensajería Kafka

Al registrar un cliente, se publica un evento JSON en el topic:

- `clientes-creados`

Esto permite integración con otros sistemas de forma asíncrona y desacoplada.

---

## 🔍 Observabilidad

- `GET /actuator/health` para health checks
- Integración con **Prometheus**, **Grafana**, o **GCP Monitoring**
- Logs estructurados con SLF4J

---

## 🧰 DevOps

- GitHub Actions para CI (compilación + pruebas + SonarQube)
- Soporte para análisis estático con **PMD**
- Dockerfile optimizado
- Configuración externalizada para `application-prod.yml`

---

## 📄 API Docs

Swagger disponible en:
```
http://localhost:8080/swagger-ui/index.html
```

---

## 🔐 Seguridad

- Spring Security básico (listo para JWT)
- Configurable con OAuth2 / Auth0 en producción

---

## 🧩 Extensiones futuras

- Integración con Cloud SQL Proxy o Pub/Sub
- Kafka Dead Letter Queue
- Auditoría persistente
- Soporte para métricas personalizadas

---

## 🧑‍💻 Autor
Desarrollado por [ Fernanda Mendoza]