package com.example.demo.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommandLineTool {

    //    https://www.cnblogs.com/aeimio/p/16453633.html
    //参考文献

    //服务器执行命令行方法
    public static List<String> executeNewFlow(List<String> commands) {
        List<String> rspList = new ArrayList<String>();
        Runtime run = Runtime.getRuntime();
        try {
            Process proc = run.exec("/bin/bash", null, null);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
            for (String line : commands) {
                out.println(line);
            }
            // out.println("cd /home/test");
            // out.println("pwd");
            // out.println("rm -fr /home/proxy.log");
            out.println("exit");// 这个命令必须执行，否则in流不结束。
//            System.out.println("===============================++>>>>>>0>>>");

            String rspLine = "";
            while ((rspLine = in.readLine()) != null) {
//                System.out.println(rspLine);
                rspList.add(rspLine);
            }
            proc.waitFor();
            in.close();
            out.close();
            proc.destroy();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rspList;
    }


    public static String buildCMDbyList(String dir, List<String> list) {
        StringBuilder builder = new StringBuilder();
        builder.append("grep -rnR '");
        for (int i = 0; i < list.size(); i++) {
            String res = list.get(i);
            builder.append(res);
            if (i < list.size() - 1) {
                builder.append("\\|");
            }
        }
        builder.append("' " + dir);
        return builder.toString();
    }


    public static void unZipCMD(String zipPath) {
        List<String> commands = new ArrayList<>();
        LogUtils.log("unzip " + zipPath);
        commands.add("unzip " + zipPath);
        List<String> strings = CommandLineTool.executeNewFlow(commands);
    }
}
