package net.croz.qed.bank.balance.dao.impl;

import net.croz.qed.bank.balance.dao.BalanceRepository;
import net.croz.qed.bank.balance.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BalancePopulator {

    private final transient BalanceRepository balanceRepository;

    @Autowired
    public BalancePopulator(final BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @PostConstruct
    @Transactional
    public void populate() {
        final List<Balance> balances = new ArrayList<>();
        balances.add(new Balance("HR8023400092673653924", "11111111111", new BigDecimal("75442.93"), "HRK", "hr"));
        balances.add(new Balance("HR3424840089132778851", "11111111111", new BigDecimal("442.93"), "HRK", "hr"));

        balances.add(new Balance("GB25BARC20038019521813", "22222222222", new BigDecimal("7921.33"), "GBP", "gb"));
        balances.add(new Balance("DE84500105173926674695", "22222222222", new BigDecimal("9431.39"), "EUR", "de"));

        for (final Balance balance : balances) {
            if (!balanceRepository.existsById(balance.getIban())) {
                balanceRepository.save(balance);
            }
        }
    }
}
