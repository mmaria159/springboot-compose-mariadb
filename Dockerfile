FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/SpringBootApplication-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]