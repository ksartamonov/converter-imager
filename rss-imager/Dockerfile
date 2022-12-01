FROM openjdk:17
WORKDIR /rss-imager-docker
ARG JAR_FILE=target/rss-imager-1.0-SNAPSHOT-exec.jar
COPY ${JAR_FILE} .
EXPOSE 8083
ENTRYPOINT java -jar "rss-imager-1.0-SNAPSHOT-exec.jar"