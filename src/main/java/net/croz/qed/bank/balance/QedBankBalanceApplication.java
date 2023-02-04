package net.croz.qed.bank.balance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QedBankBalanceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(QedBankBalanceApplication.class, args);
    }

}
