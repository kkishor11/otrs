FROM java:8
EXPOSE 8081:8081
ADD /target/otrs-pricing-service-0.0.1-SNAPSHOT.jar pricing-service.jar
ENTRYPOINT ["java", "-jar", "pricing-service.jar"]