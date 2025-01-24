package com.example.demo.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApaParser {


    /**
     * Parses a Localizable.strings file and returns a map of key-value pairs.
     *
     * @param filePath The path to the Localizable.strings file.
     * @return A map containing the parsed key-value pairs.
     * @throws IOException If an I/O error occurs.
     */
    public static Map<String, String> parseLocalizableStrings1(String filePath) throws IOException {
        Map<String, String> localizedStrings = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), Charset.forName("UTF-16LE")))) {
            // If the file has a BOM, it will be read as part of the first line,
            // but this should not affect the parsing logic as long as we trim the lines properly.
            // If the BOM is a problem, you might need to skip the first few bytes manually.

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Skip empty lines and comments (lines starting with '#')
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                // Split the line into key and value
                int equalsIndex = line.indexOf('=');
                if (equalsIndex != -1) {
                    String key = line.substring(0, equalsIndex).trim();
                    String value = line.substring(equalsIndex + 1).trim();

                    // Remove surrounding quotes if present
                    if (key.startsWith("\"") && key.endsWith("\"")) {
                        key = key.substring(1, key.length() - 1);
                    }
                    if (value.startsWith("\"") && value.endsWith("\"")) {
                        value = value.substring(1, value.length() - 1);
                    }

                    // Add the key-value pair to the map
                    localizedStrings.put(key, value);
                }
            }
        }
        return localizedStrings;
    }


    public static Map<String, String> parseLocalizableStrings(String filePath) {
        Map<String, String> localizedStrings = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();

                // 忽略空行和以注释符号（#）开头的行
                if (!line.isEmpty() && !line.startsWith("#")) {
                    // 解析键值对
                    int equalsIndex = line.indexOf('=');
                    if (equalsIndex != -1) {
                        String key = line.substring(0, equalsIndex).trim();
                        String value = line.substring(equalsIndex + 1).trim();

                        // 去除键和值两端的双引号（如果存在）
                        if (key.startsWith("\"") && key.endsWith("\"")) {
                            key = key.substring(1, key.length() - 1);
                        }
                        if (value.startsWith("\"") && value.endsWith("\"")) {
                            value = value.substring(1, value.length() - 1);
                        }

                        // 将解析后的键值对添加到映射中
                        localizedStrings.put(key, value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return localizedStrings;
    }

    public static Map<String, Object> parseInfoPlist(String path) {

        Map<String, Object> resultMap = new HashMap<>();
//        String textByPath = FileUtils.getTextByPath(path);

        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(path);
            LogUtils.logJson("===============parseInfoPlist===========》");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        LogUtils.logJson(textByPath);
//
//        Gson gson = new Gson();
//        Type type = new TypeToken<Map<String, Object>>() {
//        }.getType();
//        resultMap = gson.fromJson(textByPath, type);
//
//        LogUtils.logJson("===========parseInfoPlist============");
//        LogUtils.logJson(resultMap);

        return resultMap;
    }


    public static Map<String, Object> parsePlist(String filePath) {
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(filePath);
            Element root = document.getRootElement();
            XPath xPath = new DefaultXPath("/plist/dict");
            List<Element> list = xPath.selectNodes(root);
            return parseElement(list.get(0));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, Object> parseElement(Element element) {
        Map<String, Object> map = new HashMap<>();
        Iterator<Element> iterator = element.elementIterator();
        while (iterator.hasNext()) {
            Element subElement = iterator.next();
            String tagName = subElement.getName();
            if ("key".equals(tagName)) {
                String key = subElement.getText();
                LogUtils.logJson("==========================key====>>>>");
                if (iterator.hasNext()) {
                    Element valueElement = iterator.next();
                    Object value = getValue(valueElement);
                    map.put(key, value);
                }
            }
        }
        return map;
    }

    private static Object getValue(Element element) {
        String tagName = element.getName();
        switch (tagName) {
            case "string":
                return element.getText();
            case "integer":
                return Integer.parseInt(element.getText());
            case "real":
                return Double.parseDouble(element.getText());
            case "boolean":
                return "true".equalsIgnoreCase(element.getText());
            case "date":
                return element.getText();
            case "array":
                return parseArray(element);
            case "dict":
                return parseElement(element);
            default:
                return element.getText();
        }
    }

    private static Object[] parseArray(Element element) {
        Iterator<Element> iterator = element.elementIterator();
        Object[] array = new Object[element.elements().size()];
        int i = 0;
        while (iterator.hasNext()) {
            Element subElement = iterator.next();
            array[i] = getValue(subElement);
            i++;
        }
        return array;
    }


}
