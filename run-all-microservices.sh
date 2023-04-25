#!/bin/bash

cd /home/matheus/repositories/ms/eurekaserver
./mvnw spring-boot:run &
sleep 2

cd ..
cd /home/matheus/repositories/ms/ms-cards
./mvnw spring-boot:run &
sleep 2

cd ..
cd /home/matheus/repositories/ms/msclients
./mvnw spring-boot:run &
sleep 2

cd ..
cd /home/matheus/repositories/ms/ms-credit-appraiser
./mvnw spring-boot:run &
sleep 2

cd ..
cd /home/matheus/repositories/ms/ms-cloud-gateway
./mvnw spring-boot:run