FROM maven:3.6.3-openjdk-14 AS build
COPY ./ /app
WORKDIR /app
RUN mvn clean package -U

FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /app
WORKDIR /app
COPY --from=build app/target/cart-1.0-SNAPSHOT.jar /app
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "cart-1.0-SNAPSHOT.jar"]