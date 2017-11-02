package app.model.entity.Exemplos;

import app.model.core.AbstractIdClass;
import app.model.entity.Exemplos.Product;

import java.math.BigInteger;

public class OrderProduct extends AbstractIdClass
{
    private long orderId;
    private long productId;
    private float price;
    private long quantity;
    private float total_product;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public float getTotal_product() {
        return total_product;
    }

    public void setTotal_product(float total_product) {
        this.total_product = total_product;
    }
    
    public String getTitle(){
        String product = new String();
        return product;
        
    }

    @Override
    public String getTableName() {
        return null;
    }
}
