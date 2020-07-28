#
# Package stage
#
FROM adoptopenjdk/openjdk11 as finalApp
COPY pawmates-0.0.1-SNAPSHOT.jar /usr/local/lib/pawmates.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/usr/local/lib/pawmates.jar"]