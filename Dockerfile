FROM openjdk:8
COPY target/springgc1.jar springgc1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/springgc1.jar"]
