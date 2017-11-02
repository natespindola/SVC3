package app.model.entity.Exemplos;

import app.model.core.AbstractDateClass;
import app.model.entity.Exemplos.Product;
import app.model.entity.Exemplos.ProductPrice;

public class ProductInfos {
    
    private Product product;
    private ProductPrice price;
    
    public ProductInfos(Product product, ProductPrice price)
    {
        this.product = product;
        this.price = price;
    }
    
    public Product getProduct()
    {
        return product;
    }
    
    public ProductPrice getPrice()
    {
        return price;
    }
    
}
