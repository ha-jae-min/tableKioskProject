package org.example.tablekioskproject.vo;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class OrderVO {
    private Integer ono;
    private int table_number;
    private Integer o_sequence;
    private String o_status;
    private LocalDate o_date;
    private LocalDateTime o_time;

}
