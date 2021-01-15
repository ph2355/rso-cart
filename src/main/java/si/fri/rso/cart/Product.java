package si.fri.rso.cart;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(
                name = "Product.findProducts",
                query = "SELECT p " +
                        "FROM Product p"
        ),
        @NamedQuery(
                name = "Product.findByOwnerId",
                query = "SELECT p " +
                        "FROM Product p "  +
                        "WHERE p.ownerId = :ownerId"
        )
})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @ManyToOne
    private Integer ownerId;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private Integer price;
    @Column(name = "description")
    private String description;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
