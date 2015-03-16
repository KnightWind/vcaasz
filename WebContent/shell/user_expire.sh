JAVA_OPTS="-Xmx256m -Xms128m -Xmn32m -Xss256k"
#JAVA_OPTS=""
BASE_CLASSPATH="/data/webapps/vcaasz/WEB-INF/classes/"
LIB_CLASSPATH="/data/webapps/vcaasz/WEB-INF/lib/*"
TOMCAT_CLASSPATH="/usr/local/apache-tomcat-6.0.36/lib/*"
LOG_FILE="user_expire.log"


java $JAVA_OPTS -classpath $BASE_CLASSPATH:$LIB_CLASSPATH:$TOMCAT_CLASSPATH
com.bizconf.vcaasz.task.user.UserExpiredExecutor >> $LOG_FILE 2>&1 &

