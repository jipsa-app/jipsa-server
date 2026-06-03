package com.jipsa.jipsaserver.contract;

import com.jipsa.jipsaserver.domain.Contract;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ContractResponse {
    private final Long id;
    private final String type;
    private final String address;
    private final LocalDate contractDate;
    private final LocalDate balanceDate;
    private final LocalDate expiryDate;
    private final String memo;

    public ContractResponse(Contract c) {
        this.id = c.getId();
        this.type = c.getType();
        this.address = c.getAddress();
        this.contractDate = c.getContractDate();
        this.balanceDate = c.getBalanceDate();
        this.expiryDate = c.getExpiryDate();
        this.memo = c.getMemo();
    }
}
