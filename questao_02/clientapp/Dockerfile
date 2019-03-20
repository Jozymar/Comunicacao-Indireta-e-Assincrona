FROM openjdk:8-jdk-alpine
WORKDIR /opt
COPY target/clientapp-jar-with-dependencies.jar clientapp-jar-with-dependencies.jar
COPY target/sender-jar-with-dependencies.jar sender-jar-with-dependencies.jar
COPY start.sh start.sh
CMD ["sh", "start.sh"]