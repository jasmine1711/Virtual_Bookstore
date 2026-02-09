FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy Maven wrapper files
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# 🔥 FIX: give execute permission
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the app
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

# Updated CMD to match the finalName in pom.xml
CMD ["java", "-jar", "target/app.jar"]
