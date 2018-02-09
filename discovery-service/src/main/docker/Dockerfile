FROM anapsix/alpine-java:8
VOLUME /tmp
ADD discovery-service-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx64m","-jar","/app.jar"]