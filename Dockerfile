FROM amazoncorretto:17-al2-jdk
COPY target/original-fakepersona-0.1.jar fakepersona-backend.jar
ENTRYPOINT ["java", "-jar", "/fakepersona-backend.jar"]