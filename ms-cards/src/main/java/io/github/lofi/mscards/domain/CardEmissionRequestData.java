package io.github.lofi.mscards.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardEmissionRequestData {
    private Long cardId;
    private String cpf;
    private String address;
    private BigDecimal approvedLimit;
}