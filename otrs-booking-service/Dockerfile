FROM java:8
EXPOSE 8082:8082
ADD /target/otrs-booking-service-0.0.1-SNAPSHOT.jar booking-service.jar
ENTRYPOINT ["java", "-jar", "booking-service.jar"]