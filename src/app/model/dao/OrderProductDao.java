/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model.dao;

import app.model.entity.Exemplos.OrderProduct;
import core.Transacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author pc
 */
public class OrderProductDao {
    
    
    
    
    public void add(OrderProduct orderProduct, Transacao tr) throws Exception{
        Connection con = tr.obterConexao();
        String sql = "INSERT INTO tb_order_product (orderId, productId, price, quantity) VALUE (?, ?, ?, ? , ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, orderProduct.getOrderId());
        ps.setLong(2, orderProduct.getProductId());
        ps.setFloat(3, orderProduct.getPrice());
        ps.setLong(4, orderProduct.getQuantity());
        
        ps.executeUpdate();
        
    }
    
    public ArrayList<OrderProduct> viewByOrder(long orderId, Transacao tr) throws Exception{
        
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM tb_order_product WHERE 'orderId'=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, orderId);
        
        ResultSet rs = ps.executeQuery();   

        
        
        ArrayList<OrderProduct> results = new ArrayList<OrderProduct>();
            while (rs.next()) {
                OrderProduct orderProduct = new OrderProduct();
                
                orderProduct.setId(rs.getLong("id"));
                orderProduct.setOrderId(rs.getLong("orderId"));
                orderProduct.setProductId(rs.getLong("productId"));
                orderProduct.setPrice(rs.getFloat("price"));
                orderProduct.setQuantity(rs.getLong("quantity"));

                results.add(orderProduct);
            }
            rs.close();
            ps.close();
            return results;
    }
}
