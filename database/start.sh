#!/bin/sh

# Run the MySQL container, with a database named 'users' and credentials
# for a users-service user which can access it.
echo "Starting DB..."  
docker run --name db -d  -e MYSQL_ALLOW_EMPTY_PASSWORD=true -p 3306:3306 mysql:latest

# Wait for the database service to start up.
echo "Waiting for DB to start up..."  
docker exec db mysqladmin --silent --wait=30 -uusers_service -p123 ping || exit 1

# Run the setup script.
echo "Setting up initial data..."  
docker exec -i db mysql -uusers_service -p123 users < setup.sql  

#docker run -it --link db:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot '


  user-service:
    build: ./otrs-user-service
    container_name: otrs-user-service
    ports:
     - "8083:8083"
    links:
      - db:db
    depends_on:
     - db
  billing-service:
    build: ./otrs-billing-service
    container_name: otrs-billing-service
    ports:
     - "8081:8081"
    links:
      - db:db
    depends_on:
     - db
  booking-service:
    build: ./otrs-booking-service
    container_name: otrs-booking-service
    ports:
     - "8082:8082"
    links:
      - db:db
    depends_on:
     - db