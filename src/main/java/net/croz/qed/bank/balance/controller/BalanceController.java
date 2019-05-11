package net.croz.qed.bank.balance.controller;

import net.croz.qed.bank.balance.dao.BalanceRepository;
import net.croz.qed.bank.balance.model.AddFund;
import net.croz.qed.bank.balance.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BalanceController {

    private final BalanceRepository balanceRepository;

    @Autowired
    public BalanceController(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @GetMapping("/balance/oib/{oib}")
    public ResponseEntity<List<Balance>> getBalanceByOib(@PathVariable final String oib) {
        return ResponseEntity.of(balanceRepository.findAllByOib(oib));
    }

    @GetMapping("/balance/iban/{iban}")
    public ResponseEntity<Balance> getBalanceByIban(@PathVariable final String iban) {
        return ResponseEntity.of(balanceRepository.findByIban(iban));
    }

    @PostMapping("/balance/modify")
    public ResponseEntity<Void> modifyBalance(@RequestBody final AddFund addFund) {
        final Optional<Balance> balanceByIban = balanceRepository.findByIban(addFund.getIban());
        if (balanceByIban.isPresent()) {
            final Balance balance = balanceByIban.get();
            balance.setBalance(balance.getBalance().add(addFund.getFund()));
            balanceRepository.save(balance);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
