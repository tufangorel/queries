FROM java:8-jdk-alpine
COPY ./target/queries-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
RUN sh -c 'touch queries-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","queries-0.0.1-SNAPSHOT.jar"]