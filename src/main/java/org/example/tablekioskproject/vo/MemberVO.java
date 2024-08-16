package org.example.tablekioskproject.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberVO {

    private Integer mno;
    private String uid;
    private String upw;
    private String email;
    private boolean delFlag;

}