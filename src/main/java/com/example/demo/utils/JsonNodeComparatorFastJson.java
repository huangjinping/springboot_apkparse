package com.example.demo.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.*;

public class JsonNodeComparatorFastJson {

    // 配置忽略的字段（全局）
    private static final Set<String> IGNORED_FIELDS = new HashSet<>(Arrays.asList("in_time", "up_time", "currentSystemTime"));
    // SMS 节点只比较的字段
    private static final Set<String> SMS_COMPARED_FIELDS = new HashSet<>(Arrays.asList(
            "date_sent", "read", "phone", "person", "time", "type", "content", "seen", "status"
    ));
    // 只比较的根节点
    private static final Set<String> COMPARED_NODES = new HashSet<>(Arrays.asList(
            "calendar", "public_ip", "create_time", "other_data", "storage", "battery_status",
            "build_name", "contact", "application", "build_id", "sms",
            "general_data", "package_name", "location", "hardware", "call"
    ));

    String file1Path;
    String file2Path;

    public JsonNodeComparatorFastJson(String file1Path, String file2Path) {
        this.file1Path = file1Path;
        this.file2Path = file2Path;
    }

    public static void main(String[] args) {
        // 示例文件路径，实际使用时可通过命令行参数或输入获取
        String file1Path = "/Users/huhuijie/Downloads/492_479/392应用同意时.txt";
        String file2Path = "/Users/huhuijie/Downloads/492_479/479应用同意时.txt";

        try {
            ComparisonResult result = compareJsonFiles(file1Path, file2Path);
            printResults(result);
            printResultsToHtml(result, "comparison_result.html");
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static ComparisonResult compareJsonFiles(String file1Path, String file2Path) throws IOException {
        // 读取 JSON 文件内容
        String jsonStr1 = FileUtils.getTextByPath(file1Path);
        String jsonStr2 = FileUtils.getTextByPath(file2Path);

        // 解析 JSON
        JSONObject json1 = JSON.parseObject(jsonStr1);
        JSONObject json2 = JSON.parseObject(jsonStr2);

        ComparisonResult result = new ComparisonResult();

        // 获取所有顶级节点
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(json1.keySet());
        allKeys.addAll(json2.keySet());


        for (String key : COMPARED_NODES) {
//        for (String key : allKeys) {
            Object value1 = json1.get(key);
            Object value2 = json2.get(key);

            if (value1 instanceof JSONArray && value2 instanceof JSONArray) {
                // 数组节点：calendar, application, sms
                compareArrayNode(key, (JSONArray) value1, (JSONArray) value2, result);
            } else if (value1 instanceof JSONObject && value2 instanceof JSONObject) {
                // 对象节点：general_data, location, hardware
                compareObjectNode(key, (JSONObject) value1, (JSONObject) value2, result);
            } else {
                // 标量节点：audio_internal, download_files, video_internal, build_id, package_name
                compareScalarNode(key, value1, value2, result);
            }
        }

        return result;
    }

    private static void compareArrayNode(String nodeName, JSONArray array1, JSONArray array2, ComparisonResult result) {
        List<Object> identical = new ArrayList<>();
        Map<String, Map<String, Object[]>> differing = new HashMap<>();
        List<Object> onlyIn1 = new ArrayList<>();
        List<Object> onlyIn2 = new ArrayList<>();

        // 根据节点类型选择唯一标识符
        String keyField = getKeyFieldForNode(nodeName);
        Map<String, JSONObject> map1 = buildArrayMap(array1, keyField, nodeName);
        Map<String, JSONObject> map2 = buildArrayMap(array2, keyField, nodeName);

        for (Map.Entry<String, JSONObject> entry : map1.entrySet()) {
            String key = entry.getKey();
            JSONObject item1 = entry.getValue();
            JSONObject item2 = map2.get(key);

            if (item2 == null) {
                onlyIn1.add(item1);
            } else {
                Map<String, Object[]> differences = compareJsonObjects(item1, item2, nodeName);
                if (differences.isEmpty()) {
                    identical.add(item1);
                } else {
                    differing.put(key, differences);
                }
                map2.remove(key);
            }
        }

        onlyIn2.addAll(map2.values());

        result.identicalItems.put(nodeName, identical);
        result.differingItems.put(nodeName, differing);
        result.onlyInFile1.put(nodeName, onlyIn1);
        result.onlyInFile2.put(nodeName, onlyIn2);
    }

    private static void compareObjectNode(String nodeName, JSONObject obj1, JSONObject obj2, ComparisonResult result) {
        List<Object> identical = new ArrayList<>();
        Map<String, Map<String, Object[]>> differing = new HashMap<>();
        List<Object> onlyIn1 = new ArrayList<>();
        List<Object> onlyIn2 = new ArrayList<>();

        if (obj1 == null && obj2 == null) {
            identical.add(new JSONObject());
        } else if (obj1 == null) {
            onlyIn2.add(obj2);
        } else if (obj2 == null) {
            onlyIn1.add(obj1);
        } else {
            Map<String, Object[]> differences = compareJsonObjects(obj1, obj2, nodeName);
            if (differences.isEmpty()) {
                identical.add(obj1);
            } else {
                differing.put(nodeName, differences);
            }
        }

        result.identicalItems.put(nodeName, identical);
        result.differingItems.put(nodeName, differing);
        result.onlyInFile1.put(nodeName, onlyIn1);
        result.onlyInFile2.put(nodeName, onlyIn2);
    }

    private static void compareScalarNode(String nodeName, Object value1, Object value2, ComparisonResult result) {
        List<Object> identical = new ArrayList<>();
        Map<String, Map<String, Object[]>> differing = new HashMap<>();
        List<Object> onlyIn1 = new ArrayList<>();
        List<Object> onlyIn2 = new ArrayList<>();

        if (Objects.equals(value1, value2)) {
            identical.add(value1);
        } else {
            Map<String, Object[]> diff = new HashMap<>();
            diff.put(nodeName, new Object[]{value1, value2});
            differing.put(nodeName, diff);
        }

        if (value1 != null && value2 == null) {
            onlyIn1.add(value1);
        } else if (value1 == null && value2 != null) {
            onlyIn2.add(value2);
        }

        result.identicalItems.put(nodeName, identical);
        result.differingItems.put(nodeName, differing);
        result.onlyInFile1.put(nodeName, onlyIn1);
        result.onlyInFile2.put(nodeName, onlyIn2);
    }

    private static Map<String, Object[]> compareJsonObjects(JSONObject obj1, JSONObject obj2, String nodeName) {
        Map<String, Object[]> differences = new HashMap<>();
        Set<String> allKeys;

        if ("sms".equals(nodeName)) {
            // 仅比较 SMS 指定的字段
            allKeys = new HashSet<>(SMS_COMPARED_FIELDS);
        } else {
            // 比较所有字段
            allKeys = new HashSet<>();
            allKeys.addAll(obj1.keySet());
            allKeys.addAll(obj2.keySet());
        }

        for (String key : allKeys) {
            if (IGNORED_FIELDS.contains(key)) continue;
            if ("sms".equals(nodeName) && !SMS_COMPARED_FIELDS.contains(key)) continue;
            Object value1 = obj1.get(key);
            Object value2 = obj2.get(key);
            if (!Objects.equals(value1, value2)) {
                differences.put(key, new Object[]{value1, value2});
            }
        }

        return differences;
    }

    private static String getKeyFieldForNode(String nodeName) {
        switch (nodeName) {
            case "application":
                return "package";
            case "calendar":
                return "eventTitle_startTime"; // 组合键
            case "sms":
                return "phone_time"; // 组合键
            default:
                return "id";
        }
    }

    private static Map<String, JSONObject> buildArrayMap(JSONArray array, String keyField, String nodeName) {
        Map<String, JSONObject> map = new HashMap<>();
        for (Object obj : array) {
            JSONObject json = (JSONObject) obj;
            String key;
            if ("calendar".equals(nodeName) && "eventTitle_startTime".equals(keyField)) {
                key = json.getString("eventTitle") + "_" + json.getString("startTime");
            } else if ("sms".equals(nodeName) && "phone_time".equals(keyField)) {
                key = json.getString("phone") + "_" + json.getString("time");
            } else {
                key = json.getString(keyField);
            }
            if (key != null) {
                map.put(key, json);
            }
        }
        return map;
    }

    private static void printResults(ComparisonResult result) {
        System.out.println("\n### Comparison Results\n");

        for (String nodeName : result.identicalItems.keySet()) {
            System.out.println("Node: " + nodeName);

            // 1. 完全相同的条目
            System.out.println("  1. Identical Items:");
            List<Object> identical = result.identicalItems.get(nodeName);
            if (identical.isEmpty()) {
                System.out.println("    None");
            } else {
                for (Object item : identical) {
                    System.out.println("    - " + item);
                }
            }

            // 2. 字段不同的条目
            System.out.println("  2. Items with Differing Fields:");
            Map<String, Map<String, Object[]>> differing = result.differingItems.get(nodeName);
            if (differing.isEmpty()) {
                System.out.println("    None");
            } else {
                for (Map.Entry<String, Map<String, Object[]>> entry : differing.entrySet()) {
                    String key = entry.getKey();
                    Map<String, Object[]> differences = entry.getValue();
                    System.out.println("    - Key: " + key);
                    for (Map.Entry<String, Object[]> diff : differences.entrySet()) {
                        String field = diff.getKey();
                        Object[] values = diff.getValue();
                        System.out.printf("      - %s: File1 = %s, File2 = %s\n", field, values[0], values[1]);
                    }
                }
            }

            // 3. 仅在文件1中存在的条目
            System.out.println("  3. Items Only in File 1:");
            List<Object> onlyIn1 = result.onlyInFile1.get(nodeName);
            if (onlyIn1.isEmpty()) {
                System.out.println("    None");
            } else {
                for (Object item : onlyIn1) {
                    System.out.println("    - " + item);
                }
            }

            // 4. 仅在文件2中存在的条目
            System.out.println("  4. Items Only in File 2:");
            List<Object> onlyIn2 = result.onlyInFile2.get(nodeName);
            if (onlyIn2.isEmpty()) {
                System.out.println("    None");
            } else {
                for (Object item : onlyIn2) {
                    System.out.println("    - " + item);
                }
            }
            System.out.println();
        }
    }

    public static String printResultsToHtml(ComparisonResult result) throws IOException {
        StringBuilder html = new StringBuilder();
//        html.append("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n<meta charset=\"UTF-8\">\n");
//        html.append("<title>JSON Comparison Results</title>\n");
//        html.append("<style>\n");
//        html.append("body { font-family: Arial, sans-serif; margin: 20px; }\n");
//        html.append("h1 { color: #333; }\n");
//        html.append("h2 { color: #555; }\n");
//        html.append("table { border-collapse: collapse; width: 100%; margin-bottom: 20px; }\n");
//        html.append("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }\n");
//        html.append("th { background-color: #f2f2f2; }\n");
//        html.append("tr:nth-child(even) { background-color: #f9f9f9; }\n");
//        html.append(".section { margin-bottom: 40px; }\n");
//        html.append(".redColor { color: #FF0000; }\n");
//
//        html.append("</style>\n</head>\n<body>\n");
        html.append("<h1>JSON Comparison Results</h1>\n");

        for (String nodeName : result.identicalItems.keySet()) {
            html.append("<div class=\"section\">\n");
            html.append("<h2>Node: ").append(escapeHtml(nodeName)).append("</h2>\n");

            // 1. 完全相同的条目
            html.append("<h3>1. Identical Items</h3>\n");
            List<Object> identical = result.identicalItems.get(nodeName);
            if (identical.isEmpty()) {
                html.append("<p>None</p>\n");
            } else {
//                html.append("<table>\n<tr><th>Item</th></tr>\n");
//                for (Object item : identical) {
//                    html.append("<tr><td>").append(escapeHtml(item.toString())).append("</td></tr>\n");
//                }
//                html.append("</table>\n");
            }

            // 2. 字段不同的条目
            html.append("<h3>2. Items with Differing Fields</h3>\n");
            Map<String, Map<String, Object[]>> differing = result.differingItems.get(nodeName);
            if (differing.isEmpty()) {
                html.append("<p>None</p>\n");
            } else {
                html.append("<table class=\"redColor\">\n<tr><th>Key</th><th>Field</th><th>File1 Value</th><th>File2 Value</th></tr>\n");
                for (Map.Entry<String, Map<String, Object[]>> entry : differing.entrySet()) {
                    String key = entry.getKey();
                    Map<String, Object[]> differences = entry.getValue();
                    for (Map.Entry<String, Object[]> diff : differences.entrySet()) {
                        String field = diff.getKey();
                        Object[] values = diff.getValue();
                        html.append("<tr><td>").append(escapeHtml(key)).append("</td>");
                        html.append("<td>").append(escapeHtml(field)).append("</td>");
                        html.append("<td>").append(escapeHtml(String.valueOf(values[0]))).append("</td>");
                        html.append("<td>").append(escapeHtml(String.valueOf(values[1]))).append("</td></tr>\n");
                    }
                }
                html.append("</table>\n");
            }

            // 3. 仅在文件1中存在的条目
            html.append("<h3>3. Items Only in File 1</h3>\n");
            List<Object> onlyIn1 = result.onlyInFile1.get(nodeName);
            if (onlyIn1.isEmpty()) {
                html.append("<p>None</p>\n");
            } else {
                html.append("<table class=\"redColor\">\n<tr><th>Item</th></tr>\n");
                for (Object item : onlyIn1) {
                    html.append("<tr><td>").append(escapeHtml(item.toString())).append("</td></tr>\n");
                }
                html.append("</table>\n");
            }

            // 4. 仅在文件2中存在的条目
            html.append("<h3>4. Items Only in File 2</h3>\n");
            List<Object> onlyIn2 = result.onlyInFile2.get(nodeName);
            if (onlyIn2.isEmpty()) {
                html.append("<p>None</p>\n");
            } else {
                html.append("<table class=\"redColor\">\n<tr><th>Item</th></tr>\n");
                for (Object item : onlyIn2) {
                    html.append("<tr><td>").append(escapeHtml(item.toString())).append("</td></tr>\n");
                }
                html.append("</table>\n");
            }

            html.append("</div>\n");
        }

//        html.append("</body>\n</html>");
//        Files.writeString(Paths.get(outputPath), html.toString());

        return html.toString();
    }

    private static void printResultsToHtml(ComparisonResult result, String outputPath) throws IOException {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n<meta charset=\"UTF-8\">\n");
        html.append("<title>JSON Comparison Results</title>\n");
        html.append("<style>\n");
        html.append("body { font-family: Arial, sans-serif; margin: 20px; }\n");
        html.append("h1 { color: #333; }\n");
        html.append("h2 { color: #555; }\n");
        html.append("table { border-collapse: collapse; width: 100%; margin-bottom: 20px; }\n");
        html.append("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }\n");
        html.append("th { background-color: #f2f2f2; }\n");
        html.append("tr:nth-child(even) { background-color: #f9f9f9; }\n");
        html.append(".section { margin-bottom: 40px; }\n");
        html.append(".redColor { color: #FF0000; }\n");

        html.append("</style>\n</head>\n<body>\n");
        html.append("<h1>JSON Comparison Results</h1>\n");

        for (String nodeName : result.identicalItems.keySet()) {
            html.append("<div class=\"section\">\n");
            html.append("<h2>Node: ").append(escapeHtml(nodeName)).append("</h2>\n");

            // 1. 完全相同的条目
            html.append("<h3>1. Identical Items</h3>\n");
            List<Object> identical = result.identicalItems.get(nodeName);
            if (identical.isEmpty()) {
                html.append("<p>None</p>\n");
            } else {
//                html.append("<table>\n<tr><th>Item</th></tr>\n");
//                for (Object item : identical) {
//                    html.append("<tr><td>").append(escapeHtml(item.toString())).append("</td></tr>\n");
//                }
//                html.append("</table>\n");
            }

            // 2. 字段不同的条目
            html.append("<h3>2. Items with Differing Fields</h3>\n");
            Map<String, Map<String, Object[]>> differing = result.differingItems.get(nodeName);
            if (differing.isEmpty()) {
                html.append("<p>None</p>\n");
            } else {
                html.append("<table class=\"redColor\">\n<tr><th>Key</th><th>Field</th><th>File1 Value</th><th>File2 Value</th></tr>\n");
                for (Map.Entry<String, Map<String, Object[]>> entry : differing.entrySet()) {
                    String key = entry.getKey();
                    Map<String, Object[]> differences = entry.getValue();
                    for (Map.Entry<String, Object[]> diff : differences.entrySet()) {
                        String field = diff.getKey();
                        Object[] values = diff.getValue();
                        html.append("<tr><td>").append(escapeHtml(key)).append("</td>");
                        html.append("<td>").append(escapeHtml(field)).append("</td>");
                        html.append("<td>").append(escapeHtml(String.valueOf(values[0]))).append("</td>");
                        html.append("<td>").append(escapeHtml(String.valueOf(values[1]))).append("</td></tr>\n");
                    }
                }
                html.append("</table>\n");
            }

            // 3. 仅在文件1中存在的条目
            html.append("<h3>3. Items Only in File 1</h3>\n");
            List<Object> onlyIn1 = result.onlyInFile1.get(nodeName);
            if (onlyIn1.isEmpty()) {
                html.append("<p>None</p>\n");
            } else {
                html.append("<table class=\"redColor\">\n<tr><th>Item</th></tr>\n");
                for (Object item : onlyIn1) {
                    html.append("<tr><td>").append(escapeHtml(item.toString())).append("</td></tr>\n");
                }
                html.append("</table>\n");
            }

            // 4. 仅在文件2中存在的条目
            html.append("<h3>4. Items Only in File 2</h3>\n");
            List<Object> onlyIn2 = result.onlyInFile2.get(nodeName);
            if (onlyIn2.isEmpty()) {
                html.append("<p>None</p>\n");
            } else {
                html.append("<table class=\"redColor\">\n<tr><th>Item</th></tr>\n");
                for (Object item : onlyIn2) {
                    html.append("<tr><td>").append(escapeHtml(item.toString())).append("</td></tr>\n");
                }
                html.append("</table>\n");
            }

            html.append("</div>\n");
        }

        html.append("</body>\n</html>");
//        Files.writeString(Paths.get(outputPath), html.toString());

        FileUtils.witermessage(html.toString(), "/Users/huhuijie/Documents/GitHub/springboot_apkparse/json", outputPath);

    }

    private static String escapeHtml(String str) {
        if (str == null) return "null";
        return str.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    public String getFile1Path() {
        return file1Path;
    }

    public void setFile1Path(String file1Path) {
        this.file1Path = file1Path;
    }

    public String getFile2Path() {
        return file2Path;
    }

    public void setFile2Path(String file2Path) {
        this.file2Path = file2Path;
    }

    // 比较结果
    public static class ComparisonResult {
        Map<String, List<Object>> identicalItems = new HashMap<>();
        Map<String, Map<String, Map<String, Object[]>>> differingItems = new HashMap<>();
        Map<String, List<Object>> onlyInFile1 = new HashMap<>();
        Map<String, List<Object>> onlyInFile2 = new HashMap<>();
    }
}