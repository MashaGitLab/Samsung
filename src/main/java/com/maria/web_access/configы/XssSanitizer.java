package com.maria.web_access.configы;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class XssSanitizer {

    /**
     * Очищает входящую строку от потенциально опасного содержимого.
     *
     * @param input Входящая строка
     * @return Очищенная строка
     */
    public static String sanitize(String input) {
        return Jsoup.clean(input, Whitelist.basic());
    }
}