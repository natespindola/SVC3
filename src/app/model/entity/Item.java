package app.model.entity;

import app.model.core.AbstractIdClass;

public class Item extends AbstractIdClass
{
  private String adressType;
  private String zipCode;
  private String adress;
  private String number;
  private String complement;
  private String reference;
  private String userId;

    @Override
    public String getTableName()
    {
        return "tb_adress";
    }

    public String getAdressType()
    {
        return adressType;
    }

    public void setAdressType(String adressType)
    {
        this.adressType = adressType;
    }
    
    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public String getuserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
