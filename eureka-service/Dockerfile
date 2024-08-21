# Указываем базовый образ с JDK
FROM openjdk:17-jdk-slim

# Указываем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем собранный JAR файл в контейнер
COPY target/eureka-service-0.0.1-SNAPSHOT.jar /app/eureka-service.jar

# Указываем команду для запуска приложения
CMD ["java", "-jar", "/app/eureka-service.jar"]

# Открываем порт, на котором работает Eureka
EXPOSE 8761
