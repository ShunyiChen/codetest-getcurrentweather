FROM java:8
VOLUME /tmp
ADD eureka-server2-1.0.jar /eureka-server2-1.0.jar
EXPOSE 7002
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka-server2-1.0.jar"]