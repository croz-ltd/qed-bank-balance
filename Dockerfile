FROM harbor.croz.net/docker/library/openjdk:19
COPY target/*.jar /app.jar
ENV SERVER_PORT 8080
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
