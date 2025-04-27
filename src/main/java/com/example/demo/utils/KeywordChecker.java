package com.example.demo.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class KeywordChecker {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String source = "I l1oa1n222Cbancariaedit";
        if (isMatch(source)) {
            System.out.println("命中关键词");
        } else {
            System.out.println("未命中");
        }
    }

//
//    // 从工作簿1.xlsx 提取的单词列表
//    private static final List<String> WORKBOOK_KEYWORDS = Arrays.asList(
//             "tarjeta", "cta", "bancaria", "debito", "débito", "crédito", "credito",
//            "saldo", "cajero", "banco", "ahorro", "ahorros", "visa", "mastercard", "pichincha",
//            "austro", "produbanco", "pacificard", "daviplata", "prestamo", "prstamo", "prèstamo",
//            "prestamos", "microcredito", "multicredito", "cuota", "pago", "pagar", "mora",
//            "deuda", "deudas", "vencido", "vencimiento", "vencen", "vencidas", "vencid0",
//            "atraso", "atrasado", "compromiso", "obligacion", "intereses", "pagare",
//            "incumplimiento", "refinancie", "refinanciamiento", "transaccion", "transferencia",
//            "deposito", "retiro", "retirado", "realizado", "realizar", "realizaste", "realiza",
//            "acreditado", "acreditacion", "acreditara", "recibido", "recibimos", "recibiste",
//            "exitosamente", "exitoso", "éxito", "rechazada", "rechazado", "operacion", "consumo",
//            "sobregiro", "sobregirada", "gastos", "recordamos", "recuerda", "recordatorio",
//            "urgente", "judicial", "extrajudicial", "cobranza", "legal", "legales"
//    );
//
//
//
//
//    public static boolean isMatch(String source) {
//        // 检查空输入
//        if (source == null || source.isEmpty()) {
//            return false;
//        }
//
//        // 构造正则表达式：匹配 love、china 或工作簿单词
//        StringBuilder regexBuilder = new StringBuilder("(?i)\\b(");
//        regexBuilder.append("cuenta|");
//        for (int i = 0; i < WORKBOOK_KEYWORDS.size(); i++) {
//            regexBuilder.append(Pattern.quote(WORKBOOK_KEYWORDS.get(i)));
//            if (i < WORKBOOK_KEYWORDS.size() - 1) {
//                regexBuilder.append("|");
//            }
//        }
//        regexBuilder.append(")\\b");
//
//        // 编译正则表达式并匹配
//        String regex = regexBuilder.toString();
//        System.out.println(regex);
//
//        System.out.println(regex);
//
//        return Pattern.compile(regex).matcher(source).find();
//    }

    public static boolean isMatch(String source) {
        // 定义正则表达式：匹配 love 或 china，忽略大小写
        String regex = "(?i)(loan|credit)";
//        String regex = "(?i)(love|china|\\d)";
        return Pattern.compile(regex).matcher(source).find();
    }


}
