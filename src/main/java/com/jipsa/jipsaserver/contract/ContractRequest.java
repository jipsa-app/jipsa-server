package com.jipsa.jipsaserver.contract;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ContractRequest {
    private String type;
    private String address;
    private LocalDate contractDate;
    private LocalDate balanceDate;
    private LocalDate expiryDate;
    private String memo;
}
