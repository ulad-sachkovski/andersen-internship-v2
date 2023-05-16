FROM maven:3.9.1-eclipse-temurin-17 AS BUILD
RUN mkdir /usr/src/project
COPY . /usr/src/project
WORKDIR /usr/src/project
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
RUN mkdir /project
COPY --from=build /usr/src/project/target/*.jar /project/
WORKDIR /project
ENV JAVA_OPTS="-XX:MaxRAMPercentage=40"
EXPOSE 8080
CMD java -jar *.jar
