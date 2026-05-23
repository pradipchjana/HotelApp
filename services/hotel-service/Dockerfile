FROM gradle:9.5.0-jdk25 AS builder
WORKDIR /app
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
RUN gradle dependencies --no-daemon || true
COPY src ./src
RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar hotel.jar
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "hotel.jar"]