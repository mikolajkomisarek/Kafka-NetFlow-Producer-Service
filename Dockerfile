FROM openjdk:11.0.2-jre-slim
ADD build/libs/kafkaproducer-0.1.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Xmx4048m","-Xms2048m","-jar","/app.jar"]
