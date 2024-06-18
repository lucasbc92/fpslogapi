FROM maven:3.9.7-amazoncorretto-17-al2023

WORKDIR /app

# ENV JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n

COPY . .

EXPOSE 8080
EXPOSE 5005
EXPOSE 27017

RUN mvn clean install -DskipTests

CMD ["mvn", "-Dspring-boot.run.jvmArguments=\"-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n\"", "spring-boot:run"]