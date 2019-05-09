package net.croz.qed.bank.balance.dao;

import net.croz.qed.bank.balance.model.Balance;

import java.util.List;
import java.util.Optional;

public interface BalanceDao {

    Optional<List<Balance>> getByOib(final String oib);

    Optional<Balance> getByIban(final String iban);

    void save(final Balance balance);
}
