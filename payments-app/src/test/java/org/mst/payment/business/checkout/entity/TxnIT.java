package org.mst.payment.business.checkout.entity;

import java.util.List;
import java.util.UUID;
import static org.hamcrest.core.Is.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.mst.rulz.EntityManagerProvider;

public class TxnIT {

    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.persistenceUnit("it");

    @Test
    public void testCrud() {
        Txn txn = new Txn(10L, "4111", "1220", "EUR");
        txn.setAuthId(UUID.randomUUID().toString());
        this.provider.tx().begin();
        this.provider.em().merge(txn);
        this.provider.tx().commit();
        this.provider.em().clear();

        List<Txn> transactions = this.provider.em()
            .createNamedQuery(Txn.FIND_BY_CARD, Txn.class)
            .setParameter("cardNo", "4111")
            .getResultList();

        assertFalse(transactions.isEmpty());
        assertThat(transactions.get(0).getAmount(), is(10L));
    }

}
