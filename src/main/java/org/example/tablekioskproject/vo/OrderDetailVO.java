package org.example.tablekioskproject.vo;

import lombok.*;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class OrderDetailVO {
    private int ono;
    private int mno;
    private int category_id;
    private String menuName;
    private BigDecimal menuPrice;
    private int quantity;
    private BigDecimal total_price;
    private LocalDateTime o_time;
    private String o_status;
}
