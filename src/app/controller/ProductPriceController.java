/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package app.controller;



import app.model.entity.Exemplos.ProductPrice;
import java.util.ArrayList;
import app.model.dao.LogDao;
import app.model.dao.ProductDao;
import app.model.dao.ProductPriceDao;
import app.model.entity.Exemplos.ErrorLog;
import app.model.entity.Exemplos.Log;
import core.Transacao;
import app.model.entity.Exemplos.Product;
import core.Utils;

/**
 *
 * @author Sebastian
 */

public class ProductPriceController {
    
    public void inserir(ProductPrice product_price) {
        Transacao tr = new Transacao();
        try{
            ProductPriceDao dao = new ProductPriceDao();
            tr.begin();
            dao.save(tr,product_price);
            tr.commit();
        }catch(Exception e){
            System.out.println("erro ao pesquisar");
            e.printStackTrace();            
        }
    }

    public void SearchByIdProduct(String id) {
        Transacao tr = new Transacao();
        try{
            ProductPriceDao dao = new ProductPriceDao();
            tr.begin();
            dao.findByIdProduct(tr,id);
            tr.commit();
        }catch(Exception e){
            System.out.println("erro ao pesquisar");
            e.printStackTrace();            
        }
    }
    
}
