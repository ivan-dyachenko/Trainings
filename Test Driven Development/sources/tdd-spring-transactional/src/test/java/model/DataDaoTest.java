package model;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Collection;
import java.util.UUID;

@ContextConfiguration(locations = {"classpath:/app-context.xml"})
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class DataDaoTest {

    @Autowired
    private DataDao dataDao;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @After
    public void after() {
        dataDao.checkpoint();
    }

    @Test
    @Rollback(true)
    public void simpleTest() {
        String text = UUID.randomUUID().toString();
        dataDao.save(new Data(text));

        Collection<Data> result = dataDao.find(text);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(text, result.iterator().next().getText());
    }

    @Test
    public void complexTest() {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition(
                TransactionDefinition.PROPAGATION_REQUIRES_NEW));

        String text = UUID.randomUUID().toString();
        dataDao.save(new Data(text));
        transactionManager.commit(transaction);

        transaction = transactionManager.getTransaction(new DefaultTransactionDefinition(
                TransactionDefinition.PROPAGATION_REQUIRES_NEW));

        Collection<Data> result = dataDao.find(text);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(text, result.iterator().next().getText());

        transactionManager.rollback(transaction);
    }

}
