FROM jeanblanchard/java:8

EXPOSE 8080

CMD ["java", "-jar", "run-1.0-SNAPSHOT.jar"]

WORKDIR /app

ADD . ./
