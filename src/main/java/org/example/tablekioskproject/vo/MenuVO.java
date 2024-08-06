package org.example.tablekioskproject.vo;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class MenuVO {
    private  Integer mno;
    private Integer categoryId;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean is_sold_out;
    private boolean isRecommend;
    private boolean delflag;
}
