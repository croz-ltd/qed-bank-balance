package net.croz.qed.bank.balance.controller;

import net.croz.qed.bank.balance.dao.BalanceRepository;
import net.croz.qed.bank.balance.model.AddFund;
import net.croz.qed.bank.balance.model.Balance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    public static final Logger log = LoggerFactory.getLogger(BalanceController.class);

    private final transient BalanceRepository balanceRepository;

    @Autowired
    public BalanceController(final BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @GetMapping(value = "/balance/oib/{oib}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Balance>> getBalanceByOib(@PathVariable final String oib) {
        log.info("test");
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
            balance.setAmount(balance.getAmount().add(addFund.getFund()));
            log.info("Adding amount {} from {} with description {}", addFund.getFund(), addFund.getIban(), addFund.getDescription());
            balanceRepository.save(balance);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
