/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.model.entity.Exemplos.OrderProduct;
import app.model.entity.Exemplos.Product;
import app.model.entity.Exemplos.Order;
import app.model.entity.Exemplos.ProductPrice;
import app.model.entity.Exemplos.ProductInfos;
import java.util.ArrayList;
import app.model.dao.LogDao;
import app.model.dao.ProductDao;
import app.model.dao.OrderProductDao;
import app.model.dao.OrderDao;
import app.model.entity.Exemplos.ErrorLog;
import app.model.entity.Exemplos.Log;
import core.Transacao;
import app.model.entity.Exemplos.Product;
import core.Utils;

/**
 *
 * @author pc
 */
public class OrderProductController {
    
    public void addProductOrder (CartController cart, long orderId ){
        Transacao tr = new Transacao();
        try{
            ArrayList<Product> product = new ArrayList<Product>();
            ArrayList<ProductPrice> productPrice = new ArrayList<ProductPrice>();
            product = cart.getProduct();
            productPrice = cart.getProductPrice();
            OrderProductDao dao = new OrderProductDao();
            //int i = 0;
            
            for (int i = 0; i < product.size(); i++) {
                
                
                OrderProduct op = new OrderProduct();
                op.setOrderId(orderId);
                Product p = new Product();
                p = product.get(i);
                op.setProductId(p.getId());
                ProductPrice pp = new ProductPrice();
                pp = productPrice.get(i);
                op.setPrice(pp.getPrice());
                long quant = 1;
                op.setQuantity(quant);
      
                dao.add(op, tr);
            }           
            
            tr.commit();
            
            //falta logs
            
        } catch(Exception e){
            System.out.println("erro ao adicionar produto");
            e.printStackTrace();
        }
        
    }//percorre o CartController adicionando productId e productPrice ao tb_order_Product
    
    public ArrayList<OrderProduct> getOrderProduct (long orderId){
        
        Transacao tr = new Transacao();
        try{
            OrderProductDao dao = new OrderProductDao();
            ArrayList<OrderProduct> results = new ArrayList<OrderProduct>();
            tr.begin();
            results = dao.viewByOrder(orderId,tr);
            tr.commit();
            return results;
            
            //falta logs
            
        } catch(Exception e){
            System.out.println("erro ao adicionar produto");
            e.printStackTrace();
            return null;
            
            //falta logs
        }
        
    }//retorna um ArrayList com as infos de um determinado pedido
    
    public void addPOrder (ArrayList<ProductInfos> pInfoList, long orderId ){
        Transacao tr = new Transacao();
        try{
            //ArrayList<Product> product = new ArrayList<Product>();
            //ArrayList<ProductPrice> productPrice = new ArrayList<ProductPrice>();
            //product = cart.getProduct();
            //productPrice = cart.getProductPrice();
            OrderProductDao dao = new OrderProductDao();
            //int i = 0;
            
            for (ProductInfos pInfo : pInfoList) {
                
                
                OrderProduct op = new OrderProduct();
                op.setOrderId(orderId);
                Product p = new Product();
                p = pInfo.getProduct();
                op.setProductId(p.getId());
                ProductPrice pp = new ProductPrice();
                pp = pInfo.getPrice();
                op.setPrice(pp.getPrice());
                long quant = 1;
                op.setQuantity(quant);
                //precisar arrumar contador de quantidade
      
                dao.add(op, tr);
            }           
            
            tr.commit();
            
            //falta logs
            
        } catch(Exception e){
            System.out.println("erro ao adicionar produto");
            e.printStackTrace();
        }
        
    }
}
