package io.github.lofi.mscreditappraiser.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardClient {
    private String name;
    private String company;
    private BigDecimal approvedLimit;
}
