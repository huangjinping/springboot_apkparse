package com.example.demo.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.*;

public class ZIPUtils {
    /**
     * 压缩zip
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    private static String compressFileByZIP(String filePath) throws Exception {
        File file = new File(filePath);
        System.out.println(file.getName());
        String outputFIleName = file.getName() + ".zip";
        ArrayList<File> fileList = new ArrayList<>();
        if (file.isDirectory()) {
            fileList.addAll(Arrays.asList(file.listFiles()));
        } else {
            fileList.add(file);
        }
        FileInputStream fileInputStream = null;
        CheckedOutputStream checkedOutputStream = new CheckedOutputStream(new FileOutputStream(outputFIleName), new Adler32());
        ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream);
        for (File f : fileList) {
            if (f.isDirectory()) {
                continue;
            }
            zipOutputStream.putNextEntry(new ZipEntry(f.getName()));
            fileInputStream = new FileInputStream(f);
            byte[] bytes = new byte[1024];
            int read;
            while ((read = fileInputStream.read(bytes)) != -1) {
                zipOutputStream.write(bytes);
            }
        }
        byte[] bytes = new byte[1024];
        int read;
        while ((read = fileInputStream.read(bytes)) != -1) {
            zipOutputStream.write(bytes);
        }
        fileInputStream.close();
        zipOutputStream.close();

        return outputFIleName;
    }


    /**
     * 解压zip文件
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String extractZip(String target, String filePath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        CheckedInputStream checkedInputStream = new CheckedInputStream(fileInputStream, new Adler32());
        ZipInputStream zipInputStream = new ZipInputStream(checkedInputStream);
        ZipEntry zipEntry;

        FileOutputStream fileOutputStream = null;
        File savePath = new File(filePath.replace(".zip", ""));
        if (!savePath.exists()) {
            savePath.mkdir();
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(zipInputStream);
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            fileOutputStream = new FileOutputStream(savePath.getName() + "/" + zipEntry.getName());
            int x;
            byte[] bytes = new byte[1024];
            while ((x = bufferedInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes);
            }
            fileOutputStream.close();
        }
        zipInputStream.close();
        fileInputStream.close();
        return null;
    }

    /**
     * 解压
     *
     * @param zipPath    zip 文件夹路径
     * @param targetPath 解压路径
     */
    public static void unzip(String zipPath, String targetPath) {

        File pathFile = new File(targetPath);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        try {
            //指定编码
            try (ZipFile zipFile = new ZipFile(zipPath, Charset.forName("gbk"))) {
                //遍历里面的文件及文件夹
                Enumeration entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = (ZipEntry) entries.nextElement();
                    String zipEntryName = entry.getName();

                    try (InputStream in = zipFile.getInputStream(entry)) {
                        String outpath = (targetPath + File.separator + zipEntryName);
                        //判断路径是否存在，不存在则创建文件路径
                        File file = new File(outpath.substring(0, outpath.lastIndexOf(File.separator)));
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        //判断文件全路径是否为文件夹
                        if (new File(outpath).isDirectory())
                            continue;

                        try (OutputStream out = new FileOutputStream(outpath)) {
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = in.read(buffer)) > 0) {
                                out.write(buffer, 0, len);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
