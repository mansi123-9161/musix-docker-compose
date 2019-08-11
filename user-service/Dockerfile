FROM openjdk:11
ADD ./target/userservice-0.0.1-SNAPSHOT.jar /user/src/userservice-0.0.1-SNAPSHOT.jar
WORKDIR user/src
EXPOSE 8000
ENTRYPOINT ["java","-jar","userservice-0.0.1-SNAPSHOT.jar"]

