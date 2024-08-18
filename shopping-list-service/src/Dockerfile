# Используем официальный образ Spring Boot
FROM openjdk:11-jre-slim

# Копируем JAR-файл приложения в контейнер
COPY target/shopping-1.0.0-SNAPSHOT.jar /app.jar

# Устанавливаем порт, на котором будет работать приложение
EXPOSE 8081

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "/app.jar"]