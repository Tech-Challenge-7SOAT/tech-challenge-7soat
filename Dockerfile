FROM openjdk:21-jdk

VOLUME /tmp

RUN mkdir /work

COPY . /work

WORKDIR /work

RUN chmod +x ./mvnw

RUN ./mvnw clean package

EXPOSE 8080

RUN mv /work/build/libs/*.jar /work/app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/work/app.jar"]
