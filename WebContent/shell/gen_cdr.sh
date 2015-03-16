#!/bin/bash
java -classpath  /data/webapps/vcaasz/WEB-INF/classes/:/data/webapps/vcaasz/WEB-INF/lib/*:/usr/local/apache-tomcat-6.0.36/lib/* com.bizconf.vcaasz.task.billing.CreateCDRExecutor >> gencdr.log &


