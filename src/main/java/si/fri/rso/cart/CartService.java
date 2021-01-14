package si.fri.rso.cart;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CartService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Cart getCart(Integer ownerId) {
        CartDAO cartDAO = getCartDAO(ownerId);
        return cartDAOToCart(cartDAO);
    }


    @Transactional
    private CartDAO getCartDAO(Integer ownerId) {
        CartDAO cartDAO = em.find(CartDAO.class, ownerId);

        if(cartDAO == null) {
            cartDAO = new CartDAO();
            cartDAO.setOwnerId(ownerId);
            cartDAO.setProducts(new ArrayList<>());
            em.persist(cartDAO);
        }

        return cartDAO;
    }

    @Transactional
    public void addProduct(Integer ownerId, Integer productId) {
        CartDAO cartDAO = getCartDAO(ownerId);

//      find product by id
        Product p = em.find(Product.class, productId);
        if (p == null)
            throw new NotFoundException();

//      create product
        CartProduct cp = new CartProduct();
        cp.setCart(cartDAO);
        cp.setProduct(p);
        em.persist(cp);

        cartDAO.addProduct(cp);

    }

    @Transactional
    public void clearCart(Integer ownerId) {
        CartDAO cartDAO = getCartDAO(ownerId);

        List<CartProduct> products = cartDAO.getProducts();
        for(CartProduct cp : products)
            em.remove(cp);

        products.clear();

    }

    @Transactional
    public void removeCart(Integer ownerId) {
        CartDAO cartDAO = getCartDAO(ownerId);

        clearCart(ownerId);
        em.remove(cartDAO);

    }

    @Transactional
    public void removeProductFromCart(Integer ownerId, Integer productCartId) {
        CartDAO cartDAO = getCartDAO(ownerId);
        CartProduct cp = em.find(CartProduct.class, productCartId);
        cartDAO.getProducts().remove(cp);
        if(cp != null) {
            em.remove(cp);
        }

    }

    private Cart cartDAOToCart(CartDAO cartDAO) {
        Cart c = new Cart();
        c.setOwnerId(cartDAO.getOwnerId());
        List<ProductDTO> products = new ArrayList<>();
        for(CartProduct cp : cartDAO.getProducts()) {
            if(cp.getProduct() != null) {
                ProductDTO productDTO = productToProductDTO(cp.getProduct());
                productDTO.setProductCartId(cp.getId());
                products.add(productDTO);
            }

        }
        c.setProducts(products);

        return c;
    }

    private ProductDTO productToProductDTO(Product p) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription(p.getDescription());
        productDTO.setOwnerId(p.getOwnerId());
        productDTO.setPrice(p.getPrice());
        productDTO.setId(p.getId());
        productDTO.setTitle(p.getTitle());

        return productDTO;
    }

}