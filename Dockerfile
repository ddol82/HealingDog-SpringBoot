FROM openjdk:11-jre-slim
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY ./build/libs/healingdog-0.0.1-SNAPSHOT.jar /usr/local/healingdog-0.0.1-SNAPSHOT.jar
RUN chmod +x /usr/local/healingdog-0.0.1-SNAPSHOT.jar
WORKDIR /usr/local
ENTRYPOINT ["java", "-jar", "healingdog-0.0.1-SNAPSHOT.jar"]
