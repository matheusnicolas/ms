package io.github.lofi.mscards.application.representation;

import io.github.lofi.mscards.domain.CardClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsByClientResponse {

    private String name;
    private String company;
    private BigDecimal cardLimit;

    public static CardsByClientResponse fromModel(CardClient model) {
        return new CardsByClientResponse(
                model.getCard().getName(),
                model.getCard().getCompany().toString(),
                model.getCard().getBasicLimit()
        );
    }
}
