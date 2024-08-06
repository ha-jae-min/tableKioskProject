package org.example.tablekioskproject.common;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class OrderCalculator {

    private int quantity; // 주문 수량
    private BigDecimal price;    // 단가

    public OrderCalculator(int quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

    // 총 가격 계산
    public BigDecimal calculateTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

}
