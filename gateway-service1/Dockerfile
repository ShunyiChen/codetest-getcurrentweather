FROM java:8
VOLUME /tmp
ADD gateway-service1-1.0.jar /gateway-service1-1.0.jar
EXPOSE 9527
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway-service1-1.0.jar"]