FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/*.jar ms-client.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","ms-client.jar"]