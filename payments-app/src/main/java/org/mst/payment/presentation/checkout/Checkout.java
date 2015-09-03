package org.mst.payment.presentation.checkout;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import org.mst.payment.business.checkout.boundary.TransactionStore;
import org.mst.payment.business.checkout.entity.Txn;

@Model
public class Checkout {
    
    Txn txn;

    @Inject
    TransactionStore txnStore;

    @PostConstruct
    public void init() {
        this.txn = new Txn();
    }

    public Txn getTxn() {
        return txn;
    }

    public String checkout() {
        txnStore.save(txn);
        return "confirmed";
    }

}
