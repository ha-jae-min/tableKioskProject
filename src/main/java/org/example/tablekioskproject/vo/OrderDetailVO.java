package org.example.tablekioskproject.vo;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class OrderDetailVO {
    private int ono;         // Add ono field
    private int mno;         // Add mno field
    private int category_id;
    private String menuName;
    private BigDecimal menuPrice;
    private int quantity;
    private BigDecimal total_price;
}
