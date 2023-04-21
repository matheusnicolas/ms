package io.github.lofi.mscards.application.representation;

import io.github.lofi.mscards.domain.Card;
import io.github.lofi.mscards.domain.CardCompanyEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveRequest {
    private String name;
    private CardCompanyEnum company;
    private BigDecimal earnings;
    private BigDecimal limit;

    public Card toModel() {
        return new Card(name, company, earnings, limit);
    }
}
