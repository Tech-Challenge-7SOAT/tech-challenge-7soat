FROM maven:3.6.0-jdk-8-alpine

ADD pom.xml /

RUN mvn verify clean

ADD . /

RUN mvn package

FROM openjdk:8-jdk-alpine

WORKDIR /root/

COPY --from=0 /target/*-jar-with-dependencies.jar app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "./app.jar"]
