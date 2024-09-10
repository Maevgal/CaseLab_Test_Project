FROM gradle:8-jdk21-alpine
EXPOSE 8080
COPY / .
RUN gradle --no-daemon build
ENTRYPOINT ["java","-jar","build/libs/caseLab_test_project-0.0.1-SNAPSHOT.jar"]
