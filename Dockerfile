FROM openjdk
ADD target/Bookstore-0.0.1-SNAPSHOT.jar Bookstore-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Bookstore-0.0.1-SNAPSHOT.jar"]
