package org.mst.payment.business.checkout.boundary;

import org.mst.payment.business.checkout.boundary.TransactionStore;
import org.mst.payment.business.checkout.boundary.CheckoutResource;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.*;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mst.payment.business.checkout.entity.Txn;

public class CheckoutResourceTest {

    CheckoutResource cut;

    @Before
    public void setUp() {
        cut = new CheckoutResource();
        cut.store = mock(TransactionStore.class);
    }

    @Test
    public void testFind() throws Exception {
        when(cut.find("4111")).thenReturn(new ArrayList<Txn>() {{
            add(new Txn(10L, "4111", "1220", "EUR"));
        }});
        List<Txn> txns = cut.find("4111");
        verify(cut.store).findByCard("4111");
        assertThat(txns.get(0).getExpiry(), is("1220"));
    }

}
