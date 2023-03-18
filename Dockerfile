FROM openjdk:8
EXPOSE 8070
ENV database_url=http://localhost:8060
COPY target/AuthenticationService.jar AuthenticationService.jar
CMD ["java", "-jar", "AuthenticationService.jar"]