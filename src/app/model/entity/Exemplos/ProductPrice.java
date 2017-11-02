package app.model.entity.Exemplos;

import app.model.core.AbstractDateClass;
import app.model.helper.PeriodicityTypeEnum;
import app.model.helper.PriceTypeEnum;

import java.math.BigInteger;

public class ProductPrice extends AbstractDateClass
{
    private long productId;
    private PriceTypeEnum priceTypeId;
    private long periodicity;
    private PeriodicityTypeEnum periodicityTypeId;
    private float price;


    public long getProductId()
    {
        return productId;
    }

    public void setProductId(long productId)
    {
        this.productId = productId;
    }

    public PriceTypeEnum getPriceTypeId()
    {
        return priceTypeId;
    }

    public void setPriceTypeId(PriceTypeEnum priceTypeId)
    {
        this.priceTypeId = priceTypeId;
    }

    public long getPeriodicity()
    {
        return periodicity;
    }

    public void setPeriodicity(long periodicity)
    {
        this.periodicity = periodicity;
    }

    public PeriodicityTypeEnum getPeriodicityTypeId()
    {
        return periodicityTypeId;
    }

    public void setPeriodicityTypeId(PeriodicityTypeEnum periodicityTypeId)
    {
        this.periodicityTypeId = periodicityTypeId;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    @Override
    public String getTableName() {
        return "tb_product_price";
    }
}
