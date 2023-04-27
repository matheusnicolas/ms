package io.github.lofi.mscreditappraiser.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardEmissionRequestData {
    private Long cardId;
    private String cpf;
    private String address;
    private BigDecimal approvedLimit;
}
