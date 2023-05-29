FROM amazoncorretto:11-al2-full
COPY target/*.jar app.jar
EXPOSE 8765
ENTRYPOINT ["java","-jar","/app.jar"]