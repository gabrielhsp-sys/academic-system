# Entrega do sistema academico com Docker (TUS-2381).
# Build em dois estagios: o primeiro compila e testa com Maven
# (a infraestrutura de testes tambem roda dentro do build, TUS-2383);
# o segundo contem apenas o JRE e o jar final.

FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B clean verify

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/AcademicSystem-1.0-SNAPSHOT.jar app.jar

# A aplicacao de linha de comando e interativa:
#   docker build -t academic-system .
#   docker run -it academic-system
ENTRYPOINT ["java", "-jar", "app.jar"]
