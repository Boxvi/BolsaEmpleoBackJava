FROM openjdk:8-jdk-alpine
COPY target/springgc1-0.0.1-SNAPSHOT.jar springgc1-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/springgc1-0.0.1-SNAPSHOT.jar"]
