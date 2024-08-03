FROM openjdk:8-jdk-alpine
# 定义构建参数 ENV
ARG ENV=dev
# 设置 spring.profiles.active 环境变量
ENV SPRING_PROFILES_ACTIVE=${ENV}

APP_NAME=cake-devops

RUN apk add --no-cache bash && \
    mkdir -p /home/admin/$APP_NAME && \
    mkdir -p /home/admin/$APP_NAME/logs

# 添加 appctl.sh 脚本
ADD appctl.sh /home/admin/appctl.sh
RUN chmod +x /home/admin/appctl.sh

# 挂载目录
VOLUME /tmp



ADD /start/target/cake-ops-service.jar /home/admin/cake-ops/cake-ops-service.jar

# 设置 ENTRYPOINT
ENTRYPOINT ["/home/admin/appctl.sh", "cake-ops", "start"]
