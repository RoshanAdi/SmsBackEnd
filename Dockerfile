From openjdk:19
copy ./target/SmsBackend-0.0.1-SNAPSHOT.jar SmsBackend-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","SmsBackend-0.0.1-SNAPSHOT.jar"]
