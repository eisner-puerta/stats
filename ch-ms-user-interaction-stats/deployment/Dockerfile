# Imagen base con Java 17 y Alpine Linux
FROM eclipse-temurin:17-jdk-alpine

# Crear un usuario sin privilegios (seguridad)
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Crear volumen temporal
VOLUME /tmp

# Copiar el JAR generado por Gradle o Maven
COPY *.jar ch-ms-user-interaction-stats.jar

# Variables de entorno (opcional: reduce consumo de RAM en contenedor)
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"

# Usar el usuario sin privilegios
USER appuser

# Ejecutar la app (java $JAVA_OPTS -jar ...)
ENTRYPOINT exec java $JAVA_OPTS -jar ch-ms-user-interaction-stats.jar
