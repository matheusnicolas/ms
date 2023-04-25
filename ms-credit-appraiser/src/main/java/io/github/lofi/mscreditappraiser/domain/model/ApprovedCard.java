package io.github.lofi.mscreditappraiser.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCard {
    private String card;
    private String company;
    private BigDecimal approvedLimit;
}
