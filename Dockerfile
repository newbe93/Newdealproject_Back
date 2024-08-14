FROM openjdk:17-jdk-slim

# 시간대 설정
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# SSL을 위한 디렉토리 생성
RUN mkdir -p /etc/ssl/private

ARG JAR_FILE=app.jar
COPY ${JAR_FILE} app.jar

# 프로필 설정
ENV SPRING_PROFILES_ACTIVE=prod

# ENTRYPOINT 수정
ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-jar", "/app.jar"]

# 8080 포트 노출
EXPOSE 8080