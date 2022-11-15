FROM openjdk:8-jdk-alpine
COPY target/springgc.jar springgc.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/springgc.jar"]
