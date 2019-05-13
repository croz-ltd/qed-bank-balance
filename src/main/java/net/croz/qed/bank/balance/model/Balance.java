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
    private BigDecimal balance;
    private String currency;
    private String country;

    public Balance() {
    }

    public Balance(final String iban, final String oib, final BigDecimal balance, final String currency, final String country) {
        this.iban = iban;
        this.oib = oib;
        this.balance = balance;
        this.currency = currency;
        this.country = country;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
