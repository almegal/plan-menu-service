# Используем официальный образ Maven для сборки приложения
FROM maven:3.8.4-openjdk-17-slim AS builder

# Устанавливаем рабочую директорию
WORKDIR /usr/src/app

# Копируем файлы pom.xml и исходный код
COPY pom.xml .
COPY src ./src

# Собираем приложение
RUN mvn package -DskipTests

# Используем официальный образ OpenJDK для запуска Java-приложения
FROM openjdk:17-jdk-slim

# Устанавливаем переменную окружения для указывающей на папку с приложением
ENV APP_HOME=/usr/app/

# Создаем рабочую директорию
WORKDIR $APP_HOME

# Копируем файл JAR из стадии сборки в рабочую директорию
COPY --from=builder /usr/src/app/target/eureka-server-1.0-SNAPSHOT.jar eureka-server.jar

# Указываем команду для запуска JAR файла
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]

# Указываем порт, который будет открыт в контейнере
EXPOSE 8761