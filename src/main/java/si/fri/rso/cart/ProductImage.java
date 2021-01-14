package si.fri.rso.cart;

import javax.persistence.*;

@Entity
@Table(name = "productImages")
@NamedQueries({
        @NamedQuery(
                name = "ProductImage.find",
                query = "SELECT pi " +
                        "FROM ProductImage pi " +
                        "WHERE pi.productId = :productId"
        )
})
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @ManyToOne
    private Integer productId;
    @Lob
    @Column(name = "image")
    private byte[] image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
