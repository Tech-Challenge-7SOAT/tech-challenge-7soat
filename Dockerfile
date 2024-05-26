FROM openjdk:17-jdk-alpine

VOLUME /tmp

RUN mkdir /work

COPY . /work

WORKDIR /work

RUN apk add --no-cache dos2unix

RUN dos2unix ./mvnw && chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

RUN mv /work/target/fastfood-api-0.0.1-SNAPSHOT.jar /work/app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/work/app.jar"]