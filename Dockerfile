# Etapa 1: Build
# Usamos Eclipse Temurin 21 (LTS) por ser una distribución Open Source y estable.
# Si realmente necesitas Java 26 (futuro) o 23 (actual), cambia el tag a '23-jdk'.
FROM docker.io/eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

# Copiamos los archivos de configuración de Gradle
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Damos permisos de ejecución al wrapper (necesario en Linux/Contenedores)
RUN chmod +x gradlew

# Descargamos las dependencias (mejor cacheo)
RUN ./gradlew dependencies --no-daemon

# Copiamos el código fuente y construimos el proyecto
COPY src src
RUN ./gradlew bootJar -x test --no-daemon

# Etapa 2: Runtime
# Usamos un JRE ligero para reducir el tamaño de la imagen y la superficie de ataque.
FROM docker.io/eclipse-temurin:21-jre-alpine

WORKDIR /app

# Creamos un usuario no-root por seguridad (Best Practice)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiamos solo el ejecutable de la etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Puerto por defecto de Spring Boot
EXPOSE 8080

# Configuración de límites de memoria para contenedores (Java 10+)
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
