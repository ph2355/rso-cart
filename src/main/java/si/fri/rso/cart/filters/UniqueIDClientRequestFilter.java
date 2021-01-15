package si.fri.rso.cart.filters;

import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class UniqueIDClientRequestFilter implements ClientRequestFilter {

    @Inject
    AdditionalHeaderData additionalHeaderData;

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        requestContext.getHeaders().add("Request-ID", additionalHeaderData.getRequest_id());
    }
}
