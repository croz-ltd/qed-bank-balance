package net.croz.qed.bank.balance.dao.impl;

import net.croz.qed.bank.balance.dao.BalanceRepository;
import net.croz.qed.bank.balance.model.Balance;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Repository
public class BalancePopulator {


    private BalanceRepository balanceRepository;

    public BalancePopulator(BalanceRepository balanceRepository) {

        this.balanceRepository = balanceRepository;
    }

    @PostConstruct
    @Transactional
    public void populate() {
        Balance balance1 = new Balance();
        balance1.setOib("12345678901");
        balance1.setBalance(new BigDecimal("200.12"));
        balance1.setIban("HR12345");
        if (!balanceRepository.existsById(balance1.getIban())) {
            balanceRepository.save(balance1);
        }

        Balance balance2 = new Balance();
        balance2.setOib("12345678901");
        balance2.setBalance(new BigDecimal("100.12"));
        balance2.setIban("HR66666");
        if (!balanceRepository.existsById(balance2.getIban())) {
            balanceRepository.save(balance2);
        }
    }
}
