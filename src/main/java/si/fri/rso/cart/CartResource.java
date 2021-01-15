package si.fri.rso.cart;

import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.logs.cdi.LogParams;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log(LogParams.METRICS)
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("cart")
public class CartResource {
    @Inject
    private CartService cartBean;

    @Inject
    private FaultToleranceDemo faultToleranceDemo;

    @GET
    @Path("{ownerId}")
    public Response getCart(@PathParam("ownerId") Integer ownerId) {
        try {
            Cart c = cartBean.getCart(ownerId);
            return Response.ok(c).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("{ownerId}")
    public Response addToCart(@PathParam("ownerId") Integer ownerId, Integer productId) {
        try {
            cartBean.addProduct(ownerId, productId);
            return Response.noContent().build();
        }
        catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{ownerId}")
    public Response deleteCart(@PathParam("ownerId") Integer ownerId) {
        cartBean.removeCart(ownerId);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{ownerId}/removeProduct")
    public Response deleteCart(@PathParam("ownerId") Integer ownerId, Integer cartProductId) {
        cartBean.removeProductFromCart(ownerId, cartProductId);
        return Response.noContent().build();
    }

    @GET
    @Path("faultToleranceDemo")
    public Response faultToleranceDemo() {
        if(faultToleranceDemo.getFaultToleranceDemo())
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        else
            return Response.ok("ok").build();
    }
}
