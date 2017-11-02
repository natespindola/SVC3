package app.model.entity.Exemplos;

import app.model.core.AbstractDateClass;
import java.sql.Timestamp;


public class Pedido extends AbstractDateClass
{
    private long orderId;
    private String name;
    private long productId;
    private String title;
    private long price;
    private long quantity; 
    private Timestamp createdAt;
    
    public long getorderId()
    {
        return orderId;
    }

    public void setorderId(long orderId)
    {
        this.orderId = orderId;
    }
    
    public String getname()
    {
        return name;
    }

    public void setname(String name)
    {
        this.name = name;
    }

    public long getproductId()
    {
        return productId;
    }

    public void setproductId(long productId)
    {
        this.productId = productId;
    }

    public String gettitle()
    {
        return title;
    }

    public void settitle(String title)
    {
        this.title = title;
    }

    public long getprice()
    {
        return price;
    }

    public void setprice(long price)
    {
        this.price = price;
    }
    
    public long getquantity()
    {
        return quantity;
    }

    public void setquantity(long quantity)
    {
        this.quantity = quantity;
    }

    public Timestamp getcreatedAt()
    {
        return createdAt;
    }

    public void setcreatedAt(Timestamp createdAt)
    {
        this.createdAt = createdAt;
    }

    @Override
    public String getTableName()
    {
        return "ver_pedido";
    }

}