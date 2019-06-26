#!/usr/bin/env bash
rm *.yaml
echo '删除上次生成的yaml文件完成'
nohup java -jar swagger-1.0-SNAPSHOT-jar-with-dependencies.jar  > log.txt 2>&1 &
echo '新的yaml文件生成完成'
