FROM openjdk:11
ADD ./target/track-service-0.0.1-SNAPSHOT.jar /track/src/track-service-0.0.1-SNAPSHOT.jar
WORKDIR track/src
EXPOSE 8080
ENTRYPOINT ["java","-jar","track-service-0.0.1-SNAPSHOT.jar"]

