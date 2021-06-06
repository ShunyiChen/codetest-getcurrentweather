FROM java:8
VOLUME /tmp
ADD eureka-server1-1.0.jar /eureka-server1-1.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka-server1-1.0.jar"]