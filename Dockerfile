# Krok 1: Budowanie pliku JAR na serwerze
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Krok 2: Uruchamianie aplikacji
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/fakepersona-0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
