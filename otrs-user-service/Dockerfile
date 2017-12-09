FROM java:8
EXPOSE 8083:8083
ADD /target/otrs-user-service-0.0.1-SNAPSHOT.jar user-service.jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]