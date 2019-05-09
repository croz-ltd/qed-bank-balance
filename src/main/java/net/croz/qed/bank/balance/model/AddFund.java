package net.croz.qed.bank.balance.model;

import java.math.BigDecimal;

public class AddFund {
    private String iban;
    private BigDecimal fund;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getFund() {
        return fund;
    }

    public void setFund(BigDecimal fund) {
        this.fund = fund;
    }
}
