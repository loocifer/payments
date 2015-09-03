package org.mst.payment.business.checkout.boundary;

import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.mst.payment.business.checkout.entity.Txn;

@Stateless
@Produces({MediaType.APPLICATION_JSON})
@Path("checkout")
public class CheckoutResource {

    @Inject
    TransactionStore store;

    @GET
    @Path("{cardno}")
    public List<Txn> find(@PathParam("cardno") String cardNo) {
        return store.findByCard( cardNo );
    }

    @GET
    @Path("all")
    public List<Txn> all() {
        return store.findAll();
    }

    @POST
    public Response create(@Valid Txn transaction, @Context UriInfo info) {
        Txn saved = this.store.save(transaction);
        URI uri = info.getAbsolutePathBuilder().path("/" + saved.getCreditCard()).build();
        return Response.created(uri).build();
    }

}
