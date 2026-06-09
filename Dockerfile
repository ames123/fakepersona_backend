FROM maven:3.8.8-eclipse-temurin-17
WORKDIR /app

# Kopiujemy kod projektu
COPY . .

# Budujemy aplikację
RUN mvn clean package -DskipTests

# Otwieramy port, na którym działa Spring Boot
EXPOSE 8080

# Uruchamiamy aplikację bezpośrednio wskazując na wybudowany folder target
CMD ["java", "-jar", "target/fakepersona-0.1.jar"]
