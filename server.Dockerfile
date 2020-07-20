#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
FROM adoptopenjdk/openjdk11 as finalApp
COPY --from=build /home/app/target/dogmates-0.0.1-SNAPSHOT.jar /usr/local/lib/dogmates.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/usr/local/lib/dogmates.jar"]