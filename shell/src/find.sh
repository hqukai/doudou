#!/usr/bin/env bash

#查找深度为1的空文件夹
 find . -maxdepth 1 -type d -empty


#按类型查找
#find . -type 类型参数   f 普通文件  l 符号连接  d 目录  c字符设备  b 快设备  s 套接字  p fifo