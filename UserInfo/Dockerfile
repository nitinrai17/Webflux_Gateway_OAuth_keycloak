FROM gradle:7.3.3-jdk11 AS GRADLE_BUILD

COPY --chown=gradle:gradle build.gradle /home/gradle/

COPY --chown=gradle:gradle settings.gradle /home/gradle/

COPY --chown=gradle:gradle gradle.properties /home/gradle/

COPY --chown=gradle:gradle src /home/gradle/src

RUN gradle clean build --refresh-dependencies



FROM openjdk:11-jre-slim

WORKDIR /app

EXPOSE 8083

COPY --from=GRADLE_BUILD /home/gradle/build/libs/*.jar /app/UserInfoApplication.jar

ENTRYPOINT ["java", "-jar", "UserInfoApplication.jar"]