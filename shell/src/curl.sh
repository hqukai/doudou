#!/usr/bin/env bash



#获取请求状态码
testcode1=`curl -o /dev/null -s -w %{http_code} www.baidu.com`