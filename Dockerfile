FROM openjdk:8-jre-alpine
COPY ./target/MetaData_Project-*.jar ./MetaData_Project-*.jar
ENTRYPOINT ["java","-jar","/MetaData_Project-*.jar"]

