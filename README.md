# Apache Kafka Curso

Proyecto de práctica con **Apache Kafka** y **Spring Boot**, compuesto por dos aplicaciones independientes:

- **str-producer** — servicio que envía mensajes al topic.
- **str-consumer** — servicio que recibe los mensajes del topic.

## Stack

- Java 21
- Spring Boot 4.1.1 (spring-boot-starter-kafka, webmvc, lombok)
- Maven (wrapper incluido)
- Docker: Zookeeper, Kafka (Confluent 7.4.0) y Kafdrop

## Estructura

```
.
├── docker-compose.yml   # Infraestructura (Zookeeper + Kafka + Kafdrop)
├── str-producer         # Aplicación productora
└── str-consumer         # Aplicación consumidora
```

## Requisitos

- Java 21
- Docker y Docker Compose

## Puesta en marcha

1. Levantar la infraestructura:

```bash
docker compose up -d
```

2. Ejecutar el productor y el consumidor (en terminales separadas):

```bash
cd str-producer
./mvnw spring-boot:run
```

```bash
cd str-consumer
./mvnw spring-boot:run
```

En Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

## URLs y puertos

| Servicio  | URL                   |
|-----------|-----------------------|
| Kafka     | localhost:9092        |
| Kafdrop   | http://localhost:19000|

Kafdrop es una UI web para inspeccionar topics y mensajes.

## Detener la infraestructura

```bash
docker compose down
```
