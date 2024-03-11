FROM openjdk:8-jdk-slim

ADD target/user-0.1-SNAPSHOT.jar final.jar

CMD "java" "-jar" "final.jar" "-Dspring.profiles.active=development"