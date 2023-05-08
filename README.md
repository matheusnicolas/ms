# Lofi-Credit Microservices

This project contains a set of microservices that make up the Lofi-Credit system (fictional project). The system allows registering users, registering credit cards, consulting customers and requesting credit cards.

## Architecture

The Lofi-Credit system is built using a microservices architecture using the spring boot and spring cloud, where each microservice is responsible for a specific aspect of the system's functionality. The microservices communicate with each other using REST APIs and message queues, and are deployed using Docker containers.

The following microservices are included in the Lofi-Credit system:

- `eurekaserver`: This microservice provides service discovery and registration using Eureka.

- `ms-cards`: This microservice manages card data and provides endpoints for creating, updating, and retrieving card data.

- `ms-cloud-gateway`: This microservice is the main entry point for the Lofi-Credit system. It provides a REST API for user requests and proxies requests to the appropriate microservices based on the request URL.

- `msclients`: This microservice manages customer data and provides endpoints for creating, updating, and retrieving customer data.

- `ms-credit-appraiser`: This microservice is responsible for credit evaluation and approval. It uses a message queue (RabbitMQ) to communicate with the `ms-cards` microservice and retrieves customer data to evaluate credit applications.

## Requirements

To run the Lofi-Credit system, you will need the following:

- Java 17 or higher
- Docker
- Eureka
- RabbitMQ
- Keycloak version 18

## Getting Started

To get started with the Lofi-Credit system, follow these steps:

1. Clone this repository to your local machine.

2. Install Docker, Eureka, RabbitMQ, and Keycloak or run in a docker container.

3. Create a network to make it possible to use all the architecture.

```
$ docker network create lofims-network
```

4. Run the RabbitMQ and create a queue on RabbitMQ named `card-emission`
```
$ docker run -it --rm --name lofimsrabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management
```

5. Run the keycloak container and import the realm from the folder `keycloak-config`:
```
$ docker run --name ms-infra-keycloak -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.0 start-dev
```
6. Build the Docker images for each microservice using the following command:

- `eurekaserver`: inside the eureka directory:
```
$ docker build --tag lofims-eureka .
```
- `ms-cards`: inside the ms-cards directory:
```
$ docker build --tag lofims-cards .
```
- `ms-cloud-gateway`: inside the ms-cloud-gateway directory:
```
$ docker build --tag lofims-gateway .
```
- `ms-cloud-gateway`: inside the ms-cloud-gateway directory:
```
$ docker build --tag lofims-gateway .
```
- `msclients`: inside the msclients directory:
```
$ docker build --tag lofims-clients .
```

- `ms-credit-appraiser`: inside the ms-credit-appraiser directory:
```
$ docker build --tag lofims-credit-appraiser .
```

5. Run the microservices

```
$ docker run --name lofims-eureka -p 8761:8761 --network lofims-network -d lofims-eureka
$ docker run --name lofims-cards --network lofims-network -e RABBITMQ_SERVER=lofimsrabbitmq -e EUREKA_SERVER=lofims-eureka -d lofims-cards
$ docker run --name lofims-gateway -p 8080:8080 --network lofims-network -e EUREKA_SERVER=lofims-eureka -e KEYCLOAK_SERVER=ms-infra-keycloak -e KEYCLOAK_PORT=8080 lofims-gateway
$ docker run --name lofims-client --network lofims-network -e EUREKA_SERVER=lofims-eureka -d lofims-client
$ docker run --name lofims-credit-appraiser --network lofims-network -e RABBITMQ_SERVER=lofimsrabbitmq -e EUREKA_SERVER=lofims-eureka -d lofims-credit-appraiser
```