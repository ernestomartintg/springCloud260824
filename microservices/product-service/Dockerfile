FROM eclipse-temurin:21.0.4_7-jre
EXPOSE 8080

ADD ./build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

#FROM eclipse-temurin:21.0.4_7-jre as builder
#WORKDIR extracted
#ADD ./build/libs/*.jar app.jar
#RUN java -Djarmode=layertools -jar app.jar extract

#FROM eclipse-temurin:21.0.4_7-jre
#WORKDIR application
#COPY --from=builder extracted/dependencies/ ./
#COPY --from=builder extracted/spring-boot-loader/ ./
#COPY --from=builder extracted/snapshot-dependencies/ ./
#COPY --from=builder extracted/dependencies/ ./

#EXPOSE 8080

#ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]