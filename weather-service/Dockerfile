FROM java:8
VOLUME /tmp
ADD weather-service-1.0.jar /weather-service-1.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/weather-service-1.0.jar"]