FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY chores.jar /opt/app
CMD ["java", "-Dspring.datasource.url=jdbc:mysql://choresdb.chores.svc.cluster.local:3306/cms?useSSL=false", "-jar", "/opt/app/chores.jar"]
