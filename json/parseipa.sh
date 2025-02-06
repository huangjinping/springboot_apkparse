#!/bin/bash
echo "开始下载";

pwd;

rootPath=$1

cd $rootPath
ls;
pwd;
#mv target.ipa target.zip;
#unzip target.zip;

unzip $2;
ls;
# 打印传递给脚本的所有参数
echo "第一个参数: $1"
echo "第二个参数: $2"


# for file in /path/to/directory/*; do
#   echo $file
# done

osType=''
if [ -f /System/Library/CoreServices/SystemVersion.plist ]; then
    # macOS 系统通常有这个文件
    echo "This is a macOS system."
    osType='0'
elif [ -f /etc/os-release ]; then
    # 大多数 Linux 发行版都有这个文件
    source /etc/os-release
    echo "This is a Linux system. Distribution: $ID"
    osType='1'
else
    # 如果既不是 macOS 也不是已知的 Linux 发行版
    echo "Unknown Unix-like system."
    osType='2'
fi



# 使用 find 命令并通过管道传递给 while 循环
find ./Payload -name "*.app" | while IFS= read -r file; do
  echo "Processing file: $file"
  # 提取文件名（带扩展名）
  file_name="${file##*/}"
  echo "File name: $file_name"
  # 提取文件名（不带扩展名）
  file_name_without_ext="${file_name%.*}"
  echo "File name without extension: $file_name_without_ext"
  # file_name=$(basename "$file")
  echo "File name: $file_name"

  plistpath="${file}/Info.plist"
  extpath="${file}/${file_name_without_ext}"
  strings $extpath >strings.txt
  echo $osType
  echo "extpath: $extpath"
  # 在这里对文件进行操作
  if [ $osType = "0" ]; then
      echo "------------mac shell--------."
      nm -u $extpath >nm.txt
      otool -ov $extpath >otool.txt
      plutil -convert xml1 -o Info.plist.xml $plistpath

  elif [ $osType = "1" ]; then
      echo "------------Linux shell--------."
#       sudo yum install libplist
      plistutil -i $plistpath -o Info.plist.xml
      objdump -t $extpath >objdump.txt
      readelf -s $extpath >readelfs.txt
      readelf -l $extpath >readelfl.txt
  fi

done

cd ..
cd ..
pwd