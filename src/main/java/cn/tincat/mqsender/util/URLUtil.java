package cn.tincat.mqsender.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class URLUtil {
    public static Map<String, String> getRequestMap(String url) {
        Map<String, String> map = new HashMap<>();
        String[] params = url.substring(url.indexOf("?")+1).trim().split("&");
        Arrays.stream(params).forEach(s -> {
            String[] pair = s.split("=");
            map.put(pair[0], pair.length > 1 ? pair[1] : "");
        });
        return map;
    }

}
