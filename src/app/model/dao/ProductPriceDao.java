/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model.dao;

import java.sql.Connection;
import core.Transacao;
import app.model.entity.Exemplos.ProductPrice;
import app.model.helper.PeriodicityTypeEnum;
import app.model.helper.PriceTypeEnum;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigInteger;
import java.sql.ResultSet;

/**
 *
 * @author Sebastian
 */
public class ProductPriceDao {
    
    public void save(Transacao tr, ProductPrice product_price) throws SQLException{
        Connection con = tr.obterConexao();
        String sql = "INSERT INTO tb_product_price (productId, priceTypeId, periodicity, periodicityTypeId, price) VALUE (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, product_price.getProductId());
        switch(product_price.getPriceTypeId()){
            case ALUGUEL:
                ps.setInt(2, 1);
                break;
            case VENDA:
                ps.setInt(2, 2);
                break;
        }
        ps.setLong(3, product_price.getPeriodicity());
        switch(product_price.getPeriodicityTypeId()){
            case UNICO:
                ps.setInt(4, 1);
                break;
            case DIARIO:
                ps.setInt(4, 2);
                break;
            case SEMANAL:
                ps.setInt(4, 3);
                break;
            case MENSAL:
                ps.setInt(4, 4);
                break;
            case SEMESTRAL:
                ps.setInt(4, 5);
                break;
            case ANUAL:
                ps.setInt(4, 6);
                break;
        }
        ps.setFloat(5, product_price.getPrice());
        int result = ps.executeUpdate();
    }
    
    public ProductPrice findByIdProduct(Transacao tr, String idProduct) throws Exception {
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM tb_product_price WHERE 'productId'=?";
        
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, idProduct);
        ResultSet rs = ps.executeQuery();
        rs.next();
        
        ProductPrice product = new ProductPrice();                
        product.setId(rs.getLong("id"));
        product.setProductId(rs.getLong("productId"));                
        product.setPeriodicity(rs.getLong("periodicity"));
        product.setPrice(rs.getFloat("price"));
        
        return product;
    }
    
    public ProductPrice findById(Transacao tr, String id) throws Exception {
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM tb_product_price WHERE 'id'=?";
        
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        
        ProductPrice product = new ProductPrice();                
        product.setId(rs.getLong("id"));
        product.setProductId(rs.getLong("productId"));                
        product.setPeriodicity(rs.getLong("periodicity"));
        product.setPrice(rs.getFloat("price"));
        
        int pricetype = rs.getInt("priceTypeId");
        
        switch(pricetype){
            case 1:
                product.setPriceTypeId(PriceTypeEnum.ALUGUEL);
                break;
            case 2:
                product.setPriceTypeId(PriceTypeEnum.VENDA);
                break;
        }
        
        
        //FALTA ESCREVER O SWITCH CASE DISSO TUDO, MAS ESTOU CANSADO
        
        
//        int periodicitytype = rs.getInt("periodicityTypeId");
//        switch(periodicitytype){
//            case 1:
//                product.setPeriodicityTypeId(PeriodicityTypeEnum periodicityTypeId);
//                break;
//            case 2:
//                product.setPeriodicityTypeId(PeriodicityTypeEnum periodicityTypeId);
//                break;
//        }        
        
        return product;
    }
    
    
    public static void main (String args[]) throws Exception{
//        Transacao tr = new Transacao();
//        ProductPrice pp = new ProductPrice();
//        ProductPriceDao dao = new ProductPriceDao();
//        tr.begin();
//        BigInteger bigInt = new BigInteger(String.valueOf(9));
//        pp.setProductId(bigInt);
//        pp.setPeriodicityTypeId(PeriodicityTypeEnum.DIARIO);
//        pp.setPriceTypeId(PriceTypeEnum.ALUGUEL);
//        pp.setPrice(3);
//        dao.save(tr,pp);
//        tr.commit();
    }
    
}
