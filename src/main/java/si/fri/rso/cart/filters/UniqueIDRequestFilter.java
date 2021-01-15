package si.fri.rso.cart.filters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.UUID;


@Provider
@ApplicationScoped
public class UniqueIDRequestFilter implements ContainerRequestFilter {

    @Inject
    AdditionalHeaderData headerData;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if(requestContext.getHeaders().get("Request-ID") == null) {
            String id = UUID.randomUUID().toString();
            requestContext.getHeaders().add("Request-ID", id);
        }
        headerData.setRequest_id(requestContext.getHeaders().get("Request-ID").toString());
    }
}

