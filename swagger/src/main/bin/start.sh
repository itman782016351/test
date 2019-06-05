#!/usr/bin/env bash
rm *.yaml
nohup java -jar swagger-1.0-SNAPSHOT-jar-with-dependencies.jar  > log.txt 2>&1 &
