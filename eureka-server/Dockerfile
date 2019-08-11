FROM openjdk:11
ADD ./target/eureka-server-0.0.1-SNAPSHOT.jar /eureka/src/eureka-server-0.0.1-SNAPSHOT.jar
WORKDIR eureka/src
EXPOSE 8020
ENTRYPOINT ["java","-jar","eureka-server-0.0.1-SNAPSHOT.jar"]

