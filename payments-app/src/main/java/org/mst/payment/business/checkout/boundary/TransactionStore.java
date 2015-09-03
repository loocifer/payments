package org.mst.payment.business.checkout.boundary;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.mst.payment.business.checkout.control.TxnAuthorizer;
import org.mst.payment.business.checkout.entity.Txn;

@Stateless
public class TransactionStore {

    @PersistenceContext
    EntityManager em;

    @Inject
    TxnAuthorizer authorizer;

    public Txn save(Txn txn) {
        System.out.println("### Trying to save TXN> {ccn="+txn.getCreditCard()+", amt="+txn.getAmount()+", ccy="+txn.getCurrency()+"}");
        txn.setAuthId( authorizer.authorizeTransaction(txn) );
        return this.em.merge(txn);
    }

    public List<Txn> findByCard(String card) {
        return this.em.createNamedQuery(Txn.FIND_BY_CARD, Txn.class)
            .setParameter("cardNo", card)
            .getResultList();
    }

    public List<Txn> findAll() {
        return this.em.createNamedQuery(Txn.FIND_ALL, Txn.class)
            .getResultList();
    }

}
