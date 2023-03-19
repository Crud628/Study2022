APP_NAME=imooc-bilibili-api
echo "prepare to deploy $APP_NAME"
pid=`ps -ef |grep $APP_NAME | grep 'java' |awk '{print $2}'`
if [[ $pid ]]; then
    echo "$APP_NAME is running and pid is $pid"
    kill -9 $pid
    if [[ $? -eq 0 ]];then
       echo "sucess to stop $APP_NAME"
    else   
       echo "fail to stop $APP_NAME"
    fi
fi
echo "start to deploy $APP_NAME"
nohup /u01/deploy/jdk1.8.0_201/bin/java -jar /u01/deploy/$APP_NAME/$APP_NAME.jar >/dev/null 2>&1 &
