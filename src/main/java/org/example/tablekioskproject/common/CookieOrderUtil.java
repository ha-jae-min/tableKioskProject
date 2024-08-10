package org.example.tablekioskproject.common;

import jakarta.servlet.http.HttpServletRequest;
import org.example.tablekioskproject.vo.OrderDetailVO;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CookieOrderUtil {

    // 쿠키에서 주문 정보를 가져와 OrderDetailVO 리스트와 총합을 계산하는 메서드
    public static List<OrderDetailVO> getCookies(HttpServletRequest req) throws UnsupportedEncodingException {
        Map<Integer, String> orderMap = CookieUtil.parseOrderCookies(req);
        List<OrderDetailVO> orderDetails = new ArrayList<>();

        for (Map.Entry<Integer, String> entry : orderMap.entrySet()) {
            // 구분자로 하이픈(-)을 사용하여 데이터를 분리
            String[] values = entry.getValue().split("-");

            // 각각의 값들을 올바르게 파싱
            int mno = Integer.parseInt(values[0]);
            String menuName = URLDecoder.decode(values[1], "UTF-8");
            int tableNumber = Integer.parseInt(values[2]);
            BigDecimal menuPrice = new BigDecimal(values[3]);
            int quantity = Integer.parseInt(values[4]);
            BigDecimal totalPrice = new BigDecimal(values[5]);

            // OrderDetailVO 객체 생성
            OrderDetailVO orderDetail = OrderDetailVO.builder()
                    .mno(mno)
                    .menuName(menuName)
                    .menuPrice(menuPrice)
                    .quantity(quantity)
                    .total_price(totalPrice)
                    .build();

            // 리스트에 추가
            orderDetails.add(orderDetail);
        }

        return orderDetails;
    }



    // OrderDetailVO 리스트에서 totalSum을 계산하는 메서드
    public static BigDecimal calculateTotalSum(List<OrderDetailVO> orderDetails) {
        BigDecimal totalSum = BigDecimal.ZERO;

        for (OrderDetailVO orderDetail : orderDetails) {
            totalSum = totalSum.add(orderDetail.getTotal_price());
        }

        return totalSum;
    }
}
