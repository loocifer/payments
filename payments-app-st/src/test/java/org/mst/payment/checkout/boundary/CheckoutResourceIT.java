package org.mst.payment.checkout.boundary;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckoutResourceIT {

    private Client client;
    private WebTarget tut;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        String host = System.getProperty("resource-host", "localhost");
        String port = System.getProperty("resource-port", "8080");
        String url = String.format("http://%s:%s/payments-app/checkout", host, port);
        this.tut = this.client.target(url);
    }

    @Test
    public void testCRUD() throws Exception {
        JsonObject actual = Json.createObjectBuilder().
                add("amount", "9876").
                add("creditCard", "5111").
                add("currency", "EUR").
                add("expiry", "1230").
                build();
        Response response = this.tut.request().post(Entity.json(actual));

        assertThat(response.getStatus(), is(201));

        response = this.tut.path("5111").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonArray result = response.readEntity(JsonArray.class);

        assertThat(result.toString(), containsString("\"expiry\":\"1230\""));
        assertThat(result.toString(), containsString("\"amount\":9876"));
    }

}
