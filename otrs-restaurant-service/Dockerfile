FROM java:8
EXPOSE 8080:8080
ADD /target/otrs-restaurant-service-0.0.1-SNAPSHOT.jar restaurant-service.jar
ENTRYPOINT ["java", "-jar", "restaurant-service.jar"]