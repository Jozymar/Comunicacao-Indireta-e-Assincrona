FROM openjdk:8-jdk-alpine
WORKDIR /opt
COPY target/serverapp-jar-with-dependencies.jar serverapp-jar-with-dependencies.jar
COPY target/receiver-jar-with-dependencies.jar receiver-jar-with-dependencies.jar
COPY start.sh start.sh
EXPOSE 10991
CMD ["sh", "start.sh"]