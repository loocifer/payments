package org.mst.payment.business.checkout.boundary;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import org.mst.payment.business.checkout.control.TxnAuthorizer;
import org.mst.payment.business.checkout.entity.Txn;

public class TransactionStoreTest {

    TransactionStore cut;

    @Before
    public void setUp() {
        cut = new TransactionStore();
        cut.em = mock(EntityManager.class);
        cut.authorizer = new TxnAuthorizer();
    }

    @Test
    public void testSave() {
        Txn toSave = new Txn(10L, "4111", "1220", "EUR");
        when(cut.em.merge(toSave)).thenReturn(toSave);

        Txn saved = cut.save(toSave);

        assertThat(saved, is(toSave));
        assertThat(saved.getAuthId(), notNullValue());
    }

    @Test
    public void testFindByCard() {
        TypedQuery<Txn> query = mock(TypedQuery.class);
        when(cut.em.createNamedQuery(Txn.FIND_BY_CARD, Txn.class)).thenReturn(query);
        when(query.setParameter("cardNo", "4111")).thenReturn(query);
        when(query.getResultList()).thenReturn(new ArrayList<Txn>() {{
            add(new Txn(10L, "4111", "1220", "EUR"));
        }});

        List<Txn> result = cut.findByCard("4111");
        assertThat(result.get(0).getExpiry(), is("1220"));
    }

    @Test
    public void testFindAll() {
        TypedQuery<Txn> query = mock(TypedQuery.class);
        when(cut.em.createNamedQuery(Txn.FIND_ALL, Txn.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(new ArrayList<Txn>() {{
            add(new Txn(10L, "4111", "1220", "EUR"));
        }});

        List<Txn> result = cut.findAll();
        assertThat(result.get(0).getExpiry(), is("1220"));
    }

}
