FROM java:8
VOLUME /tmp
ADD city-service-1.0.jar /city-service-1.0.jar
EXPOSE 33472
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/city-service-1.0.jar"]