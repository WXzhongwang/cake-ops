FROM openjdk:8-jdk-alpine

# 定义构建参数 ENV
ARG ENV=dev

# 设置 spring.profiles.active 环境变量
ENV SPRING_PROFILES_ACTIVE=${ENV}

# 应用名称
ENV APP_NAME=cake-ops

# 安装必要的工具并创建目录
RUN apk add --no-cache bash && \
    mkdir -p /home/admin/$APP_NAME && \
    mkdir -p /home/admin/$APP_NAME/logs

# 添加 appctl.sh 脚本
ADD appctl.sh /home/admin/appctl.sh
RUN chmod +x /home/admin/appctl.sh

# 挂载目录
VOLUME /tmp

# 添加应用 jar 包
ADD /start/target/cake-ops-service.jar /home/admin/$APP_NAME/cake-ops-service.jar

# 设置 ENTRYPOINT
ENTRYPOINT ["/home/admin/appctl.sh", "cake-ops", "start"]
