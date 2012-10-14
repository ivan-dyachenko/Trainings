package org.springframework.samples.config.basic.account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.config.basic.account.domain.Account;
import org.springframework.samples.config.basic.account.repository.AccountRepository;
import org.springframework.samples.config.basic.account.service.TransferService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class TransferServiceClassRunnerTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferService transferService;

    @Before
    public void setUp() {
        accountRepository.add(new Account("A123", 1000.00));
        accountRepository.add(new Account("C456", 0.00));
    }


    @After
    public void tearDown() {
        accountRepository.clear();
    }

    @Test
    public void shouldHaveCorrectInitialState() {
        // then
        assertThat(accountRepository.findById("A123").getBalance(), equalTo(1000.00));
        assertThat(accountRepository.findById("C456").getBalance(), equalTo(0.00));
    }

    @Test
    public void shouldTransferMoneyBetweenAccounts() {
        // when
        transferService.transfer(100.00, "A123", "C456");
        // then
        assertThat(accountRepository.findById("A123").getBalance(), equalTo(900.00));
        assertThat(accountRepository.findById("C456").getBalance(), equalTo(100.00));
    }
}
