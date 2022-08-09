package com.example.demo.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class FileUtils {

    public static void deleteDirWithPath(String path) {
        File file = new File(path);
        if (file.exists()) {
            deleteDirWithFile(file);
        }
    }


    public static void deleteDirWithFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWithFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }


    /**
     * 递归删除目录和下面的文件————java api删除目录的话，目录必须是空的才能删除
     */
    private void deleteDirectoryAll(Path path, final AtomicBoolean result) {

        try (Stream<Path> stream = Files.list(path)) {
            stream.forEach(p -> {
                if (Files.isDirectory(p)) {
                    deleteDirectoryAll(p, result);
                }
                try {
                    Files.delete(p);
                } catch (IOException e) {
//                    log.error("删除文件错误", e);
                    result.set(false);
                }
            });
        } catch (IOException e) {
//            log.error("list path error", e);
            result.set(false);
        }
    }


}
