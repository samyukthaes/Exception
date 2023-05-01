FROM openjdk:11

ADD target/employee-0.0.1-SNAPSHOT.jar employee-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/employee-0.0.1-SNAPSHOT.jar"]