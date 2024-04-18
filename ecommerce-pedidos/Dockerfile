FROM openjdk:17
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/ecommerce-pedidos-0.0.1-SNAPSHOT.jar
EXPOSE 8084
WORKDIR /app
ENTRYPOINT java -jar ecommerce-pedidos-0.0.1-SNAPSHOT.jar