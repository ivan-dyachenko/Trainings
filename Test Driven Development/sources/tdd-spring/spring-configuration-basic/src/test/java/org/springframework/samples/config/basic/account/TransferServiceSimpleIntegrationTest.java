package org.springframework.samples.config.basic.account;

import org.junit.Test;
import org.springframework.samples.config.basic.account.domain.Account;
import org.springframework.samples.config.basic.account.repository.AccountRepository;
import org.springframework.samples.config.basic.account.repository.InMemoryAccountRepository;
import org.springframework.samples.config.basic.account.service.TransferService;
import org.springframework.samples.config.basic.account.service.TransferServiceImpl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TransferServiceSimpleIntegrationTest {

    @Test
    public void transfer100Dollars() {
        // create instances
        AccountRepository accountRepository = new InMemoryAccountRepository();
        TransferService transferService = new TransferServiceImpl(accountRepository);

        // create accounts to test against
        accountRepository.add(new Account("A123", 1000.00));
        accountRepository.add(new Account("C456", 0.00));

        // check account balances before transfer
        assertThat(accountRepository.findById("A123").getBalance(), equalTo(1000.00));
        assertThat(accountRepository.findById("C456").getBalance(), equalTo(0.00));

        // perform transfer
        transferService.transfer(100.00, "A123", "C456");

        // check account balances after transfer
        assertThat(accountRepository.findById("A123").getBalance(), equalTo(900.00));
        assertThat(accountRepository.findById("C456").getBalance(), equalTo(100.00));
    }

}
