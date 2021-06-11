FROM java:8
VOLUME /tmp
ADD gateway-service2-1.0.jar /gateway-service2-1.0.jar
EXPOSE 9528
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway-service2-1.0.jar"]