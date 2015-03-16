#!/bin/bash

DEPLOY_DISABLED=0

if [ $DEPLOY_DISABLED -ne 0 ]
then
	echo "deploy disabled..."
	exit
fi

if [ $# -ne 2 ]
then
        echo "Usage $0 branch_name version"
        exit
fi

BRANCH_NAME="$1"

DATE_TIME="`date +'%F %T'`"

cd /data/source/

if [ -d vcaasz ]
then
	rm -rf vcaasz
fi

git clone root@10.184.129.240:/data/git-repos/vcaasz.git
git fetch origin

cd vcaasz

#2014-11-20
git branch $BRANCH_NAME origin/$BRANCH_NAME
git checkout $BRANCH_NAME
git pull origin $BRANCH_NAME

#git pull origin $BRANCH_NAME:$BRANCH_NAME

if [ $? -ne 0 ]
then
	echo "****************GIT PULL ERROR: $?. invalid branch name: ${BRANCH_NAME} ****************"
	exit
fi

git checkout $BRANCH_NAME

sh /data/deploy/get_build_version.sh $2

echo -e "<b>Product Version: </b><br/>" > ./WebContent/version.html
echo "<font color=red> V${2%.*} </font><br/>" >> ./WebContent/version.html
echo -e "<b>Build Version: </b><br/>" >> ./WebContent/version.html
echo "<font>`tail -n1 /data/deploy/buildnum.txt`</font><br/>" >> ./WebContent/version.html
echo -e "<b>Git Branch:</b><br/>" >> ./WebContent/version.html
echo "${BRANCH_NAME}<br/>" >> ./WebContent/version.html
echo -e "<b>Code Version:</b><br/>" >> ./WebContent/version.html
echo "`git rev-parse HEAD`<br/>" >> ./WebContent/version.html
echo -e "<b>Build Time:</b><br/>" >> ./WebContent/version.html
echo $DATE_TIME >> ./WebContent/version.html



rm -rf /data/source/vcaasz/src/log4j.xml

cp /data/source/log4j.xml /data/source/vcaasz/src

ant 

PID=`ps -ef | grep java | grep Bootstrap | head -n1 |awk '{print $2}'`

kill -9 $PID

mv /data/webapps/vcaasz/uploadfiles /data/webapps/


rm /data/webapps/vcaasz/* -rf
scp -r WebContent/* /data/webapps/vcaasz/ 
mv /data/webapps/uploadfiles /data/webapps/vcaasz/
cp /data/download /data/webapps/vcaasz/ -rf

sh /usr/local/apache-tomcat-6.0.36/bin/catalina.sh start

exit
