package app.model.entity.Exemplos;

import app.model.core.AbstractDateClass;



public class Order extends AbstractDateClass
{

    private long userId;
    private long adressId;
    private long orderId;
    private String evaluation;



    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public long getAdressId()
    {
        return adressId;
    }

    public void setAdressId(long adressId)
    {
        this.adressId = adressId;
    }

    @Override
    public String getTableName() {
        return "tb_order";
    }

    public String getEvaluation()
    {
        return evaluation;
    }

    public void setEvaluation(String evaluation)
    {
        this.evaluation = evaluation;
    }
    
    public long getorderId()
    {
        return orderId;
    }

    public void setorderId(long orderId)
    {
        this.orderId = orderId;
    }
}