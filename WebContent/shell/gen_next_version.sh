#!/bin/sh

BASE_DIR="/data/deploy"

NEXT_BUILD_VERSION=`tail -n1 $BASE_DIR/buildnum.txt  | awk -F "." '{print ($NF)+1}'`

NEXT_MAIN_SUB_VERSION=`tail -n1 $BASE_DIR/buildnum.txt | awk -F "." '{printf "%s.%s.%s.%s",$1,$2,$3,$4}'`

NEXT_VERSION=$NEXT_MAIN_SUB_VERSION.$NEXT_BUILD_VERSION

echo "`date +'%F %T'`:" >> $BASE_DIR/buildnum.txt

echo $NEXT_VERSION >> $BASE_DIR/buildnum.txt

