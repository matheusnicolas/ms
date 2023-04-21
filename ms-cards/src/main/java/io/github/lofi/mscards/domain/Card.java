package io.github.lofi.mscards.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated
    private CardCompanyEnum company;
    private BigDecimal earnings;
    private BigDecimal basicLimit;

    public Card(String name, CardCompanyEnum company, BigDecimal earnings, BigDecimal basicLimit) {
        this.name = name;
        this.company = company;
        this.earnings = earnings;
        this.basicLimit = basicLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardCompanyEnum getCompany() {
        return company;
    }

    public void setCompany(CardCompanyEnum company) {
        this.company = company;
    }

    public BigDecimal getEarnings() {
        return earnings;
    }

    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }

    public BigDecimal getBasicLimit() {
        return basicLimit;
    }

    public void setBasicLimit(BigDecimal basicLimit) {
        this.basicLimit = basicLimit;
    }
}
