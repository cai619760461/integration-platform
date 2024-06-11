#!/bin/bash

APP_NAME=medical-systm-1.0.0.jar
COMMAND="$1"

if [[ "$COMMAND" != "start" ]] && [[ "$COMMAND" != "stop" ]] && [[ "$COMMAND" != "restart" ]]; then
  echo "Usage: $0 start | stop | restart"
  exit 0
fi

APP_BASE_PATH=$(cd `dirname $0`; pwd)

function start()
{
    JAVA_OPTS="-Dspring.profiles.active=prod -Dfile.encoding=utf-8"
    # -Xms分配堆最小内存，默认为物理内存的1/64；-Xmx分配最大内存，默认为物理内存的1/4 如果程序会崩溃请将此值调高
    nohup java -Xms512m -Xmx512m -Xmn256m -jar ${JAVA_OPTS} ${APP_NAME} >> /dev/null 2>&1 &
}

function stop()
{
    P_ID=`ps -ef | grep -w ${APP_NAME} | grep -v "grep" | awk '{print $2}'`
    kill $P_ID
    echo "项目已关闭"
}

function restart()
{
    P_ID=`ps -ef | grep -w ${APP_NAME} | grep -v "grep" | awk '{print $2}'`
    sleep 5s
    kill $P_ID
    start
    echo "项目重启成功"
}

if [[ "$COMMAND" == "start" ]]; then
  start
elif [[ "$COMMAND" == "stop" ]]; then
    stop
else
    restart
fi