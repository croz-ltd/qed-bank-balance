package net.croz.qed.bank.balance.controller;

import net.croz.qed.bank.balance.dao.BalanceDao;
import net.croz.qed.bank.balance.model.AddFund;
import net.croz.qed.bank.balance.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BalanceController {

    private final BalanceDao balanceDao;

    @Autowired
    public BalanceController(BalanceDao balanceDao) {
        this.balanceDao = balanceDao;
    }

    @GetMapping("/balance/oib/{oib}")
    public ResponseEntity<List<Balance>> getBalanceByOib(@PathVariable final String oib) {
        return ResponseEntity.of(balanceDao.getByOib(oib));
    }

    @GetMapping("/balance/iban/{iban}")
    public ResponseEntity<Balance> getBalanceByIban(@PathVariable final String iban) {
        return ResponseEntity.of(balanceDao.getByIban(iban));
    }

    @PostMapping("/balance/modify")
    public ResponseEntity<Void> modifyBalance(@RequestBody final AddFund addFund) {
        final Optional<Balance> balanceByIban = balanceDao.getByIban(addFund.getIban());
        if (balanceByIban.isPresent()) {
            final Balance balance = balanceByIban.get();
            balance.setBalance(balance.getBalance().add(addFund.getFund()));
            balanceDao.save(balance);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
