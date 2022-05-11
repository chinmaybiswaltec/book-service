FROM openjdk:11-jdk
VOLUME /tmp
WORKDIR /app
COPY target/book-catalog-service-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "book-catalog-service-0.0.1-SNAPSHOT.jar"]