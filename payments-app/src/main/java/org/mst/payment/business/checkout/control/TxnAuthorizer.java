package org.mst.payment.business.checkout.control;

import java.util.UUID;
import org.mst.payment.business.checkout.entity.Txn;

public class TxnAuthorizer {

    public String authorizeTransaction(Txn txn) {
        return UUID.randomUUID().toString();
    }

}
