package org.example.tablekioskproject.vo;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class DetailVO {
    private Integer ono;
    private Integer mno;
    private int quantity;
    private BigDecimal total_price;
}
