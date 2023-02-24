package net.croz.qed.bank.balance.model;

import java.math.BigDecimal;

public class AddFund {

    private String iban;
    private BigDecimal fund;

    private String description;

    public AddFund() {
    }

    public AddFund(final String iban, final BigDecimal fund) {
        this.iban = iban;
        this.fund = fund;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(final String iban) {
        this.iban = iban;
    }

    public BigDecimal getFund() {
        return fund;
    }

    public void setFund(final BigDecimal fund) {
        this.fund = fund;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
