package cn.lxj.java8.common;

/**
 * CartManager
 * description TODO
 * create by lxj 2018/4/25
 **/
public class CartManager {
    private String productId;

    private Integer quantity;

    public CartManager(String productId) {
        this.productId = productId;
    }

    public CartManager() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
