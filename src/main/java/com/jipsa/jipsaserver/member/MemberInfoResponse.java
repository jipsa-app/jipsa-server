package com.jipsa.jipsaserver.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberInfoResponse {
    private String nickname;
    private String email;
    private Integer age;
    private int monthlyStep;
    private int jeonseStep;
    private int saleStep;
    private String assetProfile;
}
