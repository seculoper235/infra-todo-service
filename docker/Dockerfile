FROM amazoncorretto:17.0.13

WORKDIR /app

COPY build/libs ./

ENV DB_URL "192.168.0.14"
ENV DB_USER "devteller"
ENV DB_PASS "devteller123!"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "infra-test-api-0.0.1-SNAPSHOT.jar"]
