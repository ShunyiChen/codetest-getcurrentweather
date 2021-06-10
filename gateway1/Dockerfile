FROM java:8
VOLUME /tmp
ADD gateway1-1.0.jar /gateway1-1.0.jar
EXPOSE 9527
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway1-1.0.jar"]