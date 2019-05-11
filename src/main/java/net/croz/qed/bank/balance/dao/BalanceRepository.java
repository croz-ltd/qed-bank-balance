package net.croz.qed.bank.balance.dao;

import net.croz.qed.bank.balance.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, String> {

    Optional<List<Balance>> findAllByOib(final String oib);

    Optional<Balance> findByIban(final String iban);

}
