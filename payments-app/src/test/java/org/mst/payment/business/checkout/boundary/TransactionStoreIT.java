package org.mst.payment.business.checkout.boundary;

import java.util.List;
import org.junit.Before;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.mst.payment.business.checkout.control.TxnAuthorizer;
import org.mst.payment.business.checkout.entity.Txn;
import org.mst.rulz.EntityManagerProvider;

public class TransactionStoreIT {

    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.persistenceUnit("it");

    TransactionStore tut;

    @Before
    public void setUp() {
        tut = new TransactionStore();
        tut.em = provider.em();
        tut.authorizer = new TxnAuthorizer();
    }

    @Test
    public void testSaveAndRead() {
        Txn txn = new Txn(10L, "4111", "1220", "EUR");

        this.provider.tx().begin();
        Txn saved = tut.save(txn);
        this.provider.tx().commit();
        this.provider.em().clear();
        assertThat(saved.getCurrency(), is(txn.getCurrency()));
        assertThat(saved.getAmount(), is(txn.getAmount()));
        assertThat(saved.getCreditCard(), is(txn.getCreditCard()));

        List<Txn> transactions = tut.findByCard("4111");
        assertThat(transactions.get(0).getAmount(), is(10L));
        assertThat(transactions.get(0).getCreditCard(), is("4111"));
    }

}
