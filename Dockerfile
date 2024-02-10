FROM openjdk

WORKDIR /app

COPY target/crudapi-0.0.1-SNAPSHOT.jar /app/crudapi.jar

ENTRYPOINT ["java", "-jar", "crudapi.jar"]