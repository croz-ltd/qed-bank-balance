package net.croz.qed.bank.balance.dao.impl;

import net.croz.qed.bank.balance.dao.BalanceDao;
import net.croz.qed.bank.balance.model.Balance;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalanceDaoImpl implements BalanceDao {

    private static final Map<String, List<Balance>> BALANCE_BY_OIB_MAP = new HashMap<>();
    private static final Map<String, Balance> BALANCE_BY_IBAN_MAP = new HashMap<>();

    @Override
    public Optional<List<Balance>> getByOib(final String oib) {
        if (BALANCE_BY_OIB_MAP.containsKey(oib)) {
            return Optional.of(BALANCE_BY_OIB_MAP.get(oib));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Balance> getByIban(final String iban) {
        if (BALANCE_BY_IBAN_MAP.containsKey(iban)) {
            return Optional.of(BALANCE_BY_IBAN_MAP.get(iban));
        }
        return Optional.empty();
    }

    @Override
    public void save(final Balance balance) {
        final List<Balance> newBalances = BALANCE_BY_OIB_MAP.get(balance.getOib()).stream().filter(b -> !b.getIban().equals(balance.getIban())).collect(Collectors.toList());
        newBalances.add(balance);

        BALANCE_BY_OIB_MAP.put(balance.getOib(), newBalances);
        BALANCE_BY_IBAN_MAP.put(balance.getIban(), balance);
    }

    @PostConstruct
    public void loadBalances() {
        Balance balance1 = new Balance();
        balance1.setOib("12345678901");
        balance1.setBalance(new BigDecimal("200.12"));
        balance1.setIban("HR12345");
        BALANCE_BY_IBAN_MAP.put(balance1.getIban(), balance1);

        Balance balance2 = new Balance();
        balance2.setOib("12345678901");
        balance2.setBalance(new BigDecimal("100.12"));
        balance2.setIban("HR66666");
        BALANCE_BY_IBAN_MAP.put(balance2.getIban(), balance2);

        BALANCE_BY_OIB_MAP.put("12345678901", Arrays.asList(balance1, balance2));
    }
}
