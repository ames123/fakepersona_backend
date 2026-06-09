FROM maven:3.8.8-eclipse-temurin-17
WORKDIR /app

# Kopiujemy pliki projektu
COPY . .

# Pobieramy zależności i kompilujemy projekt
RUN mvn dependency:go-offline

EXPOSE 8080

# Uruchamiamy Spring Boota bezpośrednio przez wbudowany plugin mavenowy
CMD ["mvn", "spring-boot:run"]
