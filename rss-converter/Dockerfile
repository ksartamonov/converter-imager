FROM openjdk:17
WORKDIR /rss-converter-docker
ARG JAR_FILE=target/rss-converter-1.0-SNAPSHOT-exec.jar
COPY ${JAR_FILE} .
EXPOSE 8082
ENTRYPOINT java -jar "rss-converter-1.0-SNAPSHOT-exec.jar"