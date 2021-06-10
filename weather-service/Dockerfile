FROM java:8
VOLUME /tmp
ADD weather-service-1.0.jar /weather-service-1.0.jar
EXPOSE 33473
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/weather-service-1.0.jar"]