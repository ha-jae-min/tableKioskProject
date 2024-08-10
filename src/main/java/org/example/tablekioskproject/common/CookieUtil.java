package org.example.tablekioskproject.common;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CookieUtil {

    // 쿠키에서 값을 가져올 때도 디코딩
    public static Map<Integer, String> parseOrderCookies(HttpServletRequest req) throws UnsupportedEncodingException {
        Map<Integer, String> orderMap = new HashMap<>();
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("orders".equals(cookie.getName())) {
                    String value = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    String[] orders = value.split("&");

                    for (String order : orders) {
                        String[] parts = order.split(":");
                        int mno = Integer.parseInt(parts[0]);
                        String decodedOrder = URLDecoder.decode(parts[1], "UTF-8");
                        orderMap.put(mno, decodedOrder);
                    }
                }
            }
        }

        return orderMap;
    }

    // 쿠키에 저장할 값들을 빌드하는 메소드
    public static String buildOrderCookieValue(Map<Integer, String> orderMap) {
        StringBuilder builder = new StringBuilder();
        orderMap.forEach((key, value) -> builder.append(key).append(":").append(value).append("&"));
        if (builder.length() > 0) {
            builder.setLength(builder.length() - 1); // Remove last '&'
        }
        return builder.toString();
    }

    // 새로운 주문 정보를 쿠키에 추가하는 메소드
    public static void addOrderToCookie(HttpServletRequest req, HttpServletResponse resp,
                                        int mno, String menuName, int tableNumber,
                                        BigDecimal price, int quantity, BigDecimal totalPrice) throws UnsupportedEncodingException {

        Map<Integer, String> orderMap = parseOrderCookies(req);

        // 모든 데이터를 URL 인코딩
        String encodedMenuName = URLEncoder.encode(menuName, "UTF-8");
        String encodedOrderValue = URLEncoder.encode(String.join("-",
                String.valueOf(mno),
                encodedMenuName,
                String.valueOf(tableNumber),
                price.toString(),
                String.valueOf(quantity),
                totalPrice.toString()), "UTF-8");

        // Map에 추가
        orderMap.put(mno, encodedOrderValue);

        // 새로운 쿠키 값을 빌드
        String newCookieValue = buildOrderCookieValue(orderMap);

        // 쿠키 생성 및 추가
        Cookie orderCookie = new Cookie("orders", newCookieValue);
        orderCookie.setPath("/"); // 사이트 전체에서 쿠키 접근 가능
        orderCookie.setMaxAge(60 * 60 * 24); // 1일 동안 유지

        // 쿠키 추가
        resp.addCookie(orderCookie);
    }
}
