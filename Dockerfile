FROM amazoncorretto:17
   
WORKDIR /app

ENV SPRING_DATASOURCE_URL="jdbc:mysql://benefitsdb-mysql-cluster.cluster-c5y4we8omlh2.us-east-2.rds.amazonaws.com:3306/benefitsdb"
ENV SPRING_DATASOURCE_USERNAME="admin"
ENV SPRING_DATASOURCE_PASSWORD="1qaz#EDC"

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]