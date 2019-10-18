FROM openjdk:8-jdk-alpine
VOLUME /tmp

COPY ./build/app.jar app.jar
CMD ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
