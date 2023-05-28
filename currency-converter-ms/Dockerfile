FROM amazoncorretto:11-al2-full
COPY target/*.jar app.jar
EXPOSE 8100
ENTRYPOINT ["java","-jar","/app.jar"]