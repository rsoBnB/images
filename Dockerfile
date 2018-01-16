FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./api/target /app

EXPOSE 8085

CMD ["java", "-jar", "images-api-1.0.0-SNAPSHOT.jar"]