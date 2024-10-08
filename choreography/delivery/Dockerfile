FROM jelastic/maven:3.9.5-openjdk-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21
WORKDIR /app

COPY --from=build /app/target/delivery-0.0.1-SNAPSHOT.jar /app/delivery-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "delivery-0.0.1-SNAPSHOT.jar"]
