package org.example.tablekioskproject.common;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

public class CookieUtil {

    // 쿠키에서 값을 가져올 때도 디코딩
    public static Map<Integer, String> parseOrderCookies(HttpServletRequest req) throws UnsupportedEncodingException {
        Map<Integer, String> orderMap = new LinkedHashMap<>();
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("orders".equals(cookie.getName())) {
                    String value = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    String[] orders = value.split("&");

                    for (String order : orders) {
                        String[] parts = order.split(":");

                        // 파트가 2개 이상인지 확인하고, mno가 유효한지 확인
                        if (parts.length == 2 && !parts[0].isEmpty()) {
                            try {
                                int mno = Integer.parseInt(parts[0]);  // 빈 문자열이 아닌지 확인
                                String decodedOrder = URLDecoder.decode(parts[1], "UTF-8");
                                orderMap.put(mno, decodedOrder);
                            } catch (NumberFormatException e) {
                                // 잘못된 입력 무시
                            }
                        }
                    }
                }
            }
        }

        // Map을 역순으로 반환
        List<Map.Entry<Integer, String>> entryList = new ArrayList<>(orderMap.entrySet());
        Collections.reverse(entryList);

        // 역순된 리스트를 새로운 LinkedHashMap으로 변환
        Map<Integer, String> reversedOrderMap = entryList.stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,  // 충돌 방지
                        LinkedHashMap::new
                ));

        return reversedOrderMap;
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

    // 특정 주문 항목을 삭제하는 메소드 추가
    public static void removeOrderFromCookie(HttpServletRequest req, HttpServletResponse resp, int mno) throws UnsupportedEncodingException {
        // 쿠키에서 현재 주문 목록을 가져옴
        Map<Integer, String> orderMap = parseOrderCookies(req);

        // 특정 mno에 해당하는 항목 삭제
        orderMap.remove(mno);

        // 삭제된 주문 목록을 다시 쿠키 값으로 빌드
        String updatedCookieValue = buildOrderCookieValue(orderMap);

        // 업데이트된 쿠키 설정
        Cookie updatedCookie = new Cookie("orders", updatedCookieValue);
        updatedCookie.setPath("/"); // 사이트 전체에서 쿠키 접근 가능
        updatedCookie.setMaxAge(60 * 60 * 24); // 1일 동안 유지

        // 쿠키 추가
        resp.addCookie(updatedCookie);
    }

    // 모든 주문 쿠키를 삭제하는 메소드
    public static void removeAllOrderCookies(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("orders".equals(cookie.getName())) {
                    Cookie orderCookie = new Cookie("orders", "");
                    orderCookie.setPath("/");  // 사이트 전체에서 쿠키 접근 가능
                    orderCookie.setMaxAge(0);  // 쿠키 삭제
                    resp.addCookie(orderCookie);
                }
            }
        }
    }
}

