#!/usr/bin/env bash



#-A num, --after-context=num: 在结果中同时输出匹配行之后的num行
#-B num, --before-context=num: 在结果中同时输出匹配行之前的num行，有时候我们需要显示几行上下文。
#-i, --ignore-case: 忽略大小写
#-n, --line-number: 显示行号
#-R, -r, --recursive: 递归搜索子目录
#-v, --invert-match: 输出没有匹配的行



#在当前目录的log文件中查找txt文本  -i 忽略大小写 n 显示行号
grep -ni txt *.log