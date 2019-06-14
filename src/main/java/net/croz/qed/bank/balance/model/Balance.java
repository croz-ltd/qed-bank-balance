package net.croz.qed.bank.balance.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table
@Entity
public class Balance {

    @Id
    private String iban;
    private String oib;
    private BigDecimal amount;
    private String currency;
    private String country;

    public Balance() {
    }

    public Balance(final String iban, final String oib, final BigDecimal amount, final String currency, final String country) {
        this.iban = iban;
        this.oib = oib;
        this.amount = amount;
        this.currency = currency;
        this.country = country;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(final String oib) {
        this.oib = oib;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(final String iban) {
        this.iban = iban;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }
}
