package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class FileUtils {

    public static void moveFile(String src, String dest ) {
        Path result = null;
        try {
            result = Files.move(Paths.get(src), Paths.get(dest));
        } catch (IOException e) {
            System.out.println("Exception while moving file: " + e.getMessage());
        }
        if(result != null) {
            System.out.println("文件已成功移动。");
        }else{
            System.out.println("文件移动失败。");
        }
    }
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

    public static String getTextByPath(String path) {
        String reader = null;
        BufferedReader br = null;
        File f = new File(path);
        String result = "";
        if (f.exists()) {
            try {
                br = new BufferedReader(new FileReader(f));
                while ((reader = br.readLine()) != null) {
                    result += reader;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
