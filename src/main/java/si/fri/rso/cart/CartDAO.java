package si.fri.rso.cart;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "carts")
public class CartDAO implements Serializable {

    @Id
    private Integer ownerId;
    @OneToMany
    private List<CartProduct> products;

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public List<CartProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CartProduct> products) {
        this.products = products;
    }

    public void addProduct(CartProduct product) {
        this.products.add(product);
    }
}
