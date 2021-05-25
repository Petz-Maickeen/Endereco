FROM adoptopenjdk/openjdk11:latest

COPY target/address-0.0.1-SNAPSHOT.jar address-1.0.0.jar

ENTRYPOINT ["java","-jar","/address-1.0.0.jar"]