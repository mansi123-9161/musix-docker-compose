FROM openjdk:11
ADD ./target/apigatewayservice-0.0.1-SNAPSHOT.jar /api/src/apigatewayservice-0.0.1-SNAPSHOT.jar
WORKDIR api/src
EXPOSE 8081
ENTRYPOINT ["java","-jar","apigatewayservice-0.0.1-SNAPSHOT.jar"]

