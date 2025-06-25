package com.example.demo.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.FileUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JsonApplicationComparatorFastJson {
    // 配置忽略的字段
    private static final Set<String> IGNORED_FIELDS = new HashSet<>(Arrays.asList("in_time", "up_time"));

    public static void main(String[] args) {
        // 示例文件路径，实际使用时可通过命令行参数或输入获取
        String file1Path = "/Users/huhuijie/Downloads/492_479/392应用同意时.txt";
        String file2Path = "/Users/huhuijie/Downloads/492_479/479应用同意时.txt";
//        String file1Path = "/Users/huhuijie/Downloads/492_479/392拒绝时.txt";
//        String file2Path = "/Users/huhuijie/Downloads/492_479/479拒绝时.txt";


        try {
            ComparisonResult result = compareApplications(file1Path, file2Path);
            printResults(result);
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
            e.printStackTrace();
        }
    }






    public static ComparisonResult compareApplications(String file1Path, String file2Path) throws IOException {
        // 读取 JSON 文件内容
        String jsonStr1 = FileUtils.getTextByPath(file1Path);
        String jsonStr2 = FileUtils.getTextByPath(file2Path);

        // 解析 JSON
        JSONObject json1 = JSON.parseObject(jsonStr1);
        JSONObject json2 = JSON.parseObject(jsonStr2);

        // 提取 application 列表
        JSONArray apps1 = json1.getJSONArray("application");
        JSONArray apps2 = json2.getJSONArray("application");

        // 转换为 Application 对象
        List<Application> appList1 = convertToApplicationList(apps1);
        List<Application> appList2 = convertToApplicationList(apps2);

        // 按 package 组织应用
        Map<String, Application> appMap1 = appList1.stream()
                .collect(Collectors.toMap(app -> app.package_name, app -> app));
        Map<String, Application> appMap2 = appList2.stream()
                .collect(Collectors.toMap(app -> app.package_name, app -> app));

        ComparisonResult result = new ComparisonResult();

        // 比较应用
        for (String pkg : appMap1.keySet()) {
            Application app1 = appMap1.get(pkg);
            Application app2 = appMap2.get(pkg);

            if (app2 == null) {
                // 仅在文件1中存在
                result.onlyInFile1.add(app1);
            } else {
                // 存在于两个文件中，比较字段
                Map<String, Object> map1 = app1.toMap();
                Map<String, Object> map2 = app2.toMap();
                Map<String, Object[]> differences = new HashMap<>();

                for (String field : map1.keySet()) {
                    if (IGNORED_FIELDS.contains(field)) continue; // 忽略指定字段
                    Object value1 = map1.get(field);
                    Object value2 = map2.get(field);
                    if (!Objects.equals(value1, value2)) {
                        differences.put(field, new Object[]{value1, value2});
                    }
                }

                if (differences.isEmpty()) {
                    // 所有字段相同
                    result.identicalApps.add(app1);
                } else {
                    // 有字段不同
                    result.differingApps.put(pkg, differences);
                }
                appMap2.remove(pkg); // 移除已处理的包
            }
        }

        // 文件2中剩余的应用为仅在文件2中存在的
        result.onlyInFile2.addAll(appMap2.values());

        return result;
    }

    private static List<Application> convertToApplicationList(JSONArray jsonArray) {
        List<Application> apps = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject json = (JSONObject) obj;
            Application app = new Application();
            app.app_name = json.getString("app_name");
            app.package_name = json.getString("package");
            app.version_name = json.getString("version_name");
            app.version_code = json.getLongValue("version_code");
            app.in_time = json.getLongValue("in_time");
            app.app_type = json.getString("app_type");
            app.flags = json.getLongValue("flags");
            app.up_time = json.getLongValue("up_time");
            apps.add(app);
        }
        return apps;
    }

    private static void printResults(ComparisonResult result) {
        System.out.println("\n### Comparison Results\n");

        // 1. 完全相同的应用
        System.out.println("1. Identical Applications:");
        if (result.identicalApps.isEmpty()) {
            System.out.println("  None");
        } else {
            for (Application app : result.identicalApps) {
                System.out.println("  - " + app);
            }
        }

        // 2. 字段不同的应用
        System.out.println("\n2. Applications with Differing Fields:");
        if (result.differingApps.isEmpty()) {
            System.out.println("  None");
        } else {
            for (Map.Entry<String, Map<String, Object[]>> entry : result.differingApps.entrySet()) {
                String pkg = entry.getKey();
                Map<String, Object[]> differences = entry.getValue();
                System.out.println("  - Package: " + pkg);
                for (Map.Entry<String, Object[]> diff : differences.entrySet()) {
                    String field = diff.getKey();
                    Object[] values = diff.getValue();
                    System.out.printf("    - %s: File1 = %s, File2 = %s\n", field, values[0], values[1]);
                }
            }
        }

        // 3. 仅在文件1中存在的应用
        System.out.println("\n3. Applications Only in File 1:");
        if (result.onlyInFile1.isEmpty()) {
            System.out.println("  None");
        } else {
            for (Application app : result.onlyInFile1) {
                System.out.println("  - " + app);
            }
        }

        // 4. 仅在文件2中存在的应用
        System.out.println("\n4. Applications Only in File 2:");
        if (result.onlyInFile2.isEmpty()) {
            System.out.println("  None");
        } else {
            for (Application app : result.onlyInFile2) {
                System.out.println("  - " + app);
            }
        }
    }

    // 表示应用的对象
    static class Application {
        String app_name;
        String package_name;
        String version_name;
        long version_code;
        long in_time;
        String app_type;
        long flags;
        long up_time;

        // 转换为 Map 用于比较
        public Map<String, Object> toMap() {
            Map<String, Object> map = new HashMap<>();
            map.put("app_name", app_name);
            map.put("package", package_name);
            map.put("version_name", version_name);
            map.put("version_code", version_code);
            map.put("in_time", in_time);
            map.put("app_type", app_type);
            map.put("flags", flags);
            map.put("up_time", up_time);
            return map;
        }

        @Override
        public String toString() {
            return String.format("App: %s (%s), Version: %s (%d), Type: %s, Flags: %d, InTime: %d, UpTime: %d",
                    app_name, package_name, version_name, version_code, app_type, flags, in_time, up_time);
        }
    }


    // 比较结果
    static class ComparisonResult {
        List<Application> identicalApps = new ArrayList<>();
        Map<String, Map<String, Object[]>> differingApps = new HashMap<>();
        List<Application> onlyInFile1 = new ArrayList<>();
        List<Application> onlyInFile2 = new ArrayList<>();
    }
}