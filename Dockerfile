# 1. Usamos una imagen base estable y ligera (Eclipse Temurin)
FROM eclipse-temurin:17-jdk-alpine

# 2. Creamos una carpeta de trabajo
WORKDIR /app

# 3. Copiamos el .jar generado al contenedor
# Asegúrate de que este nombre sea exacto al que tienes en la carpeta target
COPY target/sistema-gestion-0.0.1-SNAPSHOT.jar app.jar

# 4. Exponemos el puerto 8080
EXPOSE 8080

# 5. Comando para iniciar la app
ENTRYPOINT ["java", "-jar", "app.jar"]