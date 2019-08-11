FROM openjdk:11
ADD ./target/configuration-server-0.0.1-SNAPSHOT.jar /config/src/configuration-server-0.0.1-SNAPSHOT.jar
WORKDIR config/src
EXPOSE 8050
ENTRYPOINT ["java","-jar","configuration-server-0.0.1-SNAPSHOT.jar"]

