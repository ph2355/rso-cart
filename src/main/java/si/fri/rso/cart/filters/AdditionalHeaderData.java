package si.fri.rso.cart.filters;

import javax.enterprise.context.RequestScoped;

@RequestScoped
class AdditionalHeaderData {
    private String request_id;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }
}