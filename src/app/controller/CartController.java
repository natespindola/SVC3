/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import app.model.core.AbstractDateClass;
import java.math.BigInteger;
import java.util.ArrayList;
import app.model.entity.Exemplos.Product;
import app.model.entity.Exemplos.ProductPrice;
import app.model.dao.ProductDao;
import app.model.dao.ProductPriceDao;
import core.Transacao;

/**
 *
 * @author pc
 */
public class CartController {
    
    private ArrayList<Product> productList;
    private ArrayList<ProductPrice> priceList;
    
    public ArrayList<Product> getProduct(){
        return productList;
    }
    
    public ArrayList<ProductPrice> getProductPrice(){
        return priceList;
    }
    
     public void cleanCart(){
        productList.clear();
        priceList.clear();
    }
    
     
     
    public void addProduct (String id){
        Product product = new Product();
        ProductPrice productPrice = new ProductPrice();
        
        try{
            
        Transacao tr = new Transacao();
        tr.beginReadOnly();
        
        ProductDao pdao = new ProductDao();
        ProductPriceDao ppdao = new ProductPriceDao();
        
        product = pdao.findProductById(id, tr);
        productPrice = ppdao.findById(tr, id);
        
        tr.commit();
        
        productList.add(product);
        priceList.add(productPrice);
        
        }
        catch(Exception e){
        }
        
        
        
        
        
    }
    public void removeProduct (String id){
        Product product = new Product();
        ProductPrice productPrice = new ProductPrice();
        
        try{
            
        Transacao tr = new Transacao();
        tr.beginReadOnly();
        
        ProductDao pdao = new ProductDao();
        ProductPriceDao ppdao = new ProductPriceDao();
        
        product = pdao.findProductById(id, tr);
        productPrice = ppdao.findById(tr, id);
        
        tr.commit();
        
        productList.remove(product);
        priceList.remove(productPrice);
        
        }
        catch(Exception e){
        }
       
    }
    
   
    
    
    
}