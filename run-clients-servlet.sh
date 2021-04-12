#!/bin/bash
CIERA_VERSION=2.1.1-SNAPSHOT
CLASSPATH=/root/.m2/repository/io/ciera/runtime/$CIERA_VERSION/runtime-$CIERA_VERSION.jar:/root/.m2/repository/org/json/json/20180813/json-20180813.jar:/root/.m2/repository/io/ciera/Deployment/1.0.0-SNAPSHOT/Deployment-1.0.0-SNAPSHOT.jar
java -cp $CLASSPATH -jar /root/.m2/repository/io/ciera/Deployment/1.0.0-SNAPSHOT/Deployment-1.0.0-SNAPSHOT.jar