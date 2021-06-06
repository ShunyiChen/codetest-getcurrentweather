FROM java:8
VOLUME /tmp
ADD config-server-1.0.jar /config-server-1.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/config-server-1.0.jar"]