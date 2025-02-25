#!/bin/bash

set -e

# 检查命令行参数
if [ $# -lt 2 ] || [ $# -gt 3 ]; then
    echo "Usage: $0 {start|stop|restart} [spring_profiles_active]" >&2
    exit 2 # bad usage
fi

# 操作
APP_NAME=$1
ACTION=$2
SPRING_PROFILES_ACTIVE=${3:-dev}

echo "APP_NAME: ${APP_NAME}"
echo "ACTION: ${ACTION}"
echo "Spring Profiles Active: ${SPRING_PROFILES_ACTIVE}"

# 检查是否运行在容器内
IN_DOCKER=$(command -v docker &> /dev/null && echo true || echo false)

# 定义日志文件
DEPLOY_LOG="/home/admin/${APP_NAME}/deploy.log"

# 定义 Java 主类和 jar 文件路径
MAIN_CLASS="com.rany.ops.CakeOpsApplication"
JAR_FILE="/home/admin/${APP_NAME}/cake-ops-service.jar"

# 检查 JAR 文件是否存在
if [ ! -f "$JAR_FILE" ]; then
    echo "JAR file does not exist at path: $JAR_FILE" >&2
    exit 1
fi

# 定义 JVM 选项
JAVA_OPTS=(
    "-Xms512m -Xmx1024m"
    "-Xloggc:/home/admin/gc.log"
    "-XX:+PrintGCDetails"
    "-XX:+PrintGCDateStamps"
    "-XX:+PrintGCTimeStamps"
    "-XX:+PrintGCCause"
    "-XX:+PrintGCApplicationStoppedTime"
    "-XX:+UseGCLogFileRotation"
    "-XX:NumberOfGCLogFiles=10"
    "-XX:GCLogFileSize=100M"
)

# 定义启动命令
START_CMD="java ${JAVA_OPTS[*]} -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar $JAR_FILE"

echo "开始启动: $START_CMD"

# 检查应用是否正在运行
is_running() {
    if [[ $IN_DOCKER == true ]]; then
        pgrep -f "$MAIN_CLASS" > /dev/null
        return $?
    else
        ps aux | grep -q "[j]ava.*$MAIN_CLASS"
        return $?
    fi
}

# 启动应用
start_app() {
    echo "Starting application..." | tee -a "$DEPLOY_LOG"
    if is_running; then
        echo "Application is already running." | tee -a "$DEPLOY_LOG"
    else
        # 使用nohup来让Java应用在后台运行
        # 注意：这里不再重定向到日志文件，而是直接启动应用
        exec $START_CMD
        echo "Application started with PID $$." | tee -a "$DEPLOY_LOG"
    fi
}

# 停止应用
stop_app() {
    echo "Stopping application..." | tee -a "$DEPLOY_LOG"
    if is_running; then
        if [[ $IN_DOCKER == true ]]; then
            pkill -f "$MAIN_CLASS"
        else
            ps aux | grep "[j]ava.*$MAIN_CLASS" | awk '{print $2}' | xargs kill
        fi
        echo "Application stopped." | tee -a "$DEPLOY_LOG"
    else
        echo "Application is not running." | tee -a "$DEPLOY_LOG"
    fi
}

# 重启应用
restart_app() {
    stop_app
    start_app
}

# 执行启动命令
case "$ACTION" in
    start)
        start_app
        ;;
    stop)
        stop_app
        ;;
    restart)
        restart_app
        ;;
    *)
        echo "Usage: $0 {start|stop|restart}" >&2
        exit 1
        ;;
esac

exit 0
