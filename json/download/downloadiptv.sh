#!/bin/bash

# 定义下载目录和日志文件
DOWNLOAD_DIR="/Users/huhuijie/Downloads/downloader"                        # 修改为你想保存的路径
LOG_FILE="$DOWNLOAD_DIR/download_log.txt"               # 日志文件路径
TIMESTAMP=$(date +"%Y-%m-%d %H:%M:%S")                  # 当前时间戳

# 确保下载目录存在
mkdir -p "$DOWNLOAD_DIR"

#rm -rf "$DOWNLOAD_DIR/*.m3u"

# 定义URL数组
URLS=(
    "https://cdn.jsdelivr.net/gh/Guovin/iptv-api@gd/output/result.m3u"
    "https://iptv-org.github.io/iptv/index.m3u"
    "https://gh-proxy.com/raw.githubusercontent.com/vbskycn/iptv/refs/heads/master/tv/iptv4.m3u"
    "https://ghfast.top/raw.githubusercontent.com/TianmuTNT/iptv/main/iptv.m3u"
    "https://raw.githubusercontent.com/BurningC4/Chinese-IPTV/master/TV-IPV4.m3u"
    # 在这里添加更多URL
)

# 循环下载每个URL
for URL in "${URLS[@]}"; do
    # 从URL中提取文件名（可选）
    FILENAME=$(basename "$URL")
    echo "[$TIMESTAMP] 开始下载: $URL" >> "$LOG_FILE"

    # 使用wget下载文件
    wget -q "$URL" -O "$DOWNLOAD_DIR/$FILENAME"

    # 检查下载是否成功
    if [ $? -eq 0 ]; then
        echo "[$TIMESTAMP] $URL 下载成功，保存为 $FILENAME" >> "$LOG_FILE"
    else
        echo "[$TIMESTAMP] $URL 下载失败" >> "$LOG_FILE"
    fi
done