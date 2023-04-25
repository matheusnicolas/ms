#!/bin/bash

filepath=`pwd`

cd $filepath/eurekaserver
./mvnw spring-boot:run &
sleep 5

cd ..
cd $filepath/ms-cards
./mvnw spring-boot:run &
sleep 5

cd ..
cd $filepath/msclients
./mvnw spring-boot:run &
sleep 5

cd ..
cd /$filepath/ms-credit-appraiser
./mvnw spring-boot:run &
sleep 10

cd ..
cd $filepath/ms-cloud-gateway
./mvnw spring-boot:run