package ssafy.haruman.global.utils;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UrlUtil {

    public static String getFinalURL(String urlPath) {
        try {
            URL url = new URL(urlPath);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setInstanceFollowRedirects(false);
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            con.addRequestProperty("Referer", "https://www.google.com/");
            con.connect();
            // Header에서 Status Code를 뽑는다.
            int resCode = con.getResponseCode();
            // http코드가 301(영구이동), 302(임시 이동), 303(기타 위치 보기) 이면 또다시 이 함수를 태운다. 재귀함수.
            if (resCode == HttpURLConnection.HTTP_SEE_OTHER || resCode == HttpURLConnection.HTTP_MOVED_PERM || resCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                String Location = con.getHeaderField("Location");
                if (Location.startsWith("/")) {
                    Location = url.getProtocol() + "://" + url.getHost() + Location;
                }
                return getFinalURL(Location);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlPath;
    }

    public static String getFormatFromQuery(String urlPath) {
        try {
            URL aURL = new URL(urlPath);
            Map<String, String> map = getQueryMap(aURL.getQuery());

            if (map != null) {
                Set<String> keys = map.keySet();
                for (String key : keys) {
                    if (key.equals("fm") || key.equals("format")) {
                        return map.get(key);
                    }
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return "jpg";
    }

    public static Map<String, String> getQueryMap(String query) {
        if (query == null) return null;

        int pos1 = query.indexOf("?");
        if (pos1 >= 0) {
            query = query.substring(pos1 + 1);
        }

        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }
}
