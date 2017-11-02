package app.model.dao;

import core.Transacao;
import app.model.dao.LogDao;
import app.controller.ProductController;
import app.model.entity.Exemplos.ErrorLog;
import app.model.entity.Exemplos.Log;
import app.model.entity.Exemplos.User;
import app.model.entity.Exemplos.Product;
import app.model.entity.Exemplos.Pedido;
import app.model.entity.Exemplos.Book;
import core.Utils;
import java.util.Vector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import app.model.entity.Exemplos.ProductPrice;
import app.model.entity.Exemplos.ProductInfos;
import java.math.BigInteger;
import app.model.helper.PeriodicityTypeEnum;
import app.model.helper.PriceTypeEnum;

public class ProductDao
{
    
    public ArrayList<ProductInfos> findLike(Transacao tr, String bookId, String description, String priceTypeId, String priceMax, String userId) throws Exception
    {
            String sql = "SELECT * FROM tb_product JOIN tb_product_price ON tb_product.id = tb_product_price.productId JOIN tb_user ON tb_product.userId = tb_user.id WHERE tb_product.active = 1 AND tb_product.excluded = 0";
            if (bookId != null && !bookId.isEmpty()) {
                sql += " AND LOWER(tb_product.bookId) = ?";
            }
            if (description != null && !description.isEmpty()) {
                sql += " AND LOWER(tb_product.description) LIKE LOWER(?)";
            }
            if (priceTypeId != null && !priceTypeId.isEmpty()) {
                sql += " AND tb_product_price.priceTypeId = ?";
            }
            if (priceMax != null && !priceMax.isEmpty()) {
                sql += " AND tb_product_price.price <= ?";
            }
            if (userId != null && !userId.isEmpty()) {
                sql += " AND tb_user.id = ?";
            }
        try {
            Connection con = tr.obterConexao();
            PreparedStatement ps = con.prepareStatement(sql);
            Integer index = 0;
            if (bookId != null && !bookId.isEmpty()) {
                index++;
                ps.setString(index, bookId);
            }
            if (description != null && !description.isEmpty()) {
                index++;
                ps.setString(index, "%" + description + "%");
            }
            if (priceTypeId != null && !priceTypeId.isEmpty()) {
                index++;
                ps.setString(index, priceTypeId);
            }
            if (priceMax != null && !priceMax.isEmpty()) {
                index++;
                ps.setString(index, priceMax);
            }
            if (userId != null && !userId.isEmpty()) {
                index++;
                ps.setString(index, userId);
            }
            
            ResultSet rs = ps.executeQuery();
            ArrayList<ProductInfos> results = new ArrayList<ProductInfos>();
            while (rs.next()) {
                Product product = new Product();
                ProductPrice price = new ProductPrice();
                
                product.setUserId(rs.getLong("userId"));
                product.setBookId(rs.getLong("bookId"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getLong("quantity"));
                product.setActive(true);
                product.setExcluded(false);
                price.setProductId(rs.getInt("productId"));
                price.setPriceTypeId(PriceTypeEnum.fromInt(rs.getInt("priceTypeId")));
                price.setPeriodicity(rs.getLong("periodicity"));
                price.setPeriodicityTypeId(PeriodicityTypeEnum.fromInt(rs.getInt("periodicityTypeId")));
                price.setPrice(rs.getFloat("price"));

                ProductInfos product_infos = new ProductInfos(product, price);
                results.add(product_infos);
            }
            rs.close();
            ps.close();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public Product findById(Transacao tr, String idProduct) throws Exception
    {
        String sql = "SELECT * FROM tb_product WHERE `id` = ?";
        try {
            Connection con = tr.obterConexao();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, idProduct);
           
            ResultSet rs = ps.executeQuery();
            rs.next();
            
                Product product = new Product();
                
                product.setUserId(rs.getLong("userId"));
                product.setBookId(rs.getLong("bookId"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getLong("quantity"));
                product.setActive(true);
                product.setExcluded(false);
                
            rs.close();
            ps.close();
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    
    
    public ProductInfos findCart(Transacao tr, String idProduct) throws Exception
    {
            String sql = "SELECT * FROM tb_product, tb_product_price where tb_product.id = ? and tb_product.id = tb_product_price.id;";
         
        try {
            Connection con = tr.obterConexao();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, idProduct);
           
            
            ResultSet rs = ps.executeQuery();
            rs.next();
                Product product = new Product();
                ProductPrice price = new ProductPrice();
                
                product.setUserId(rs.getLong("userId"));
                product.setBookId(rs.getLong("bookId"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getLong("quantity"));
                product.setActive(true);
                product.setExcluded(false);
                price.setProductId(rs.getInt("productId"));
                price.setPriceTypeId(PriceTypeEnum.fromInt(rs.getInt("priceTypeId")));
                price.setPeriodicity(rs.getLong("periodicity"));
                price.setPeriodicityTypeId(PeriodicityTypeEnum.fromInt(rs.getInt("periodicityTypeId")));
                price.setPrice(rs.getFloat("price"));

                ProductInfos results = new ProductInfos(product, price);
            rs.close();
            ps.close();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public Product[] findAllProducts(Transacao tr) throws Exception {        
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM tb_product ORDER BY id";        
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();        
        int count = 0;
        Product[] ProductArray;
        ProductArray = new Product[100000];
        if (rs != null){
            while (rs.next()){
                Product product = new Product();                
                product.setId(rs.getLong("id"));
                product.setUserId(rs.getLong("userId"));                
                product.setBookId(rs.getLong("bookId"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getLong("quantity"));
                product.setActive(rs.getBoolean("active"));
                product.setExcluded(rs.getBoolean("excluded"));            
                product.setCreatedAt(rs.getTimestamp("createdAt"));
                product.setUpdatedAt(rs.getTimestamp("updatedAt"));
                ProductArray[count] = product;
                count+=1;
                }
            }       
        return ProductArray;
    }
    
    public String findSellerName(Transacao tr, Product product) throws Exception{
        Connection con = tr.obterConexao();
        String sql = "SELECT name from tb_user where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, product.getUserId());
        ResultSet rs = ps.executeQuery();
        rs.next();       
        return rs.getString("name");
    }
    
        public String findBookTitle(Transacao tr, Product product) throws Exception{
        Connection con = tr.obterConexao();
        String sql = "SELECT title from tb_book where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, product.getBookId());
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString("title");
    }
        
        public Product findProductById(String id, Transacao tr) throws Exception {        
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM tb_product WHERE 'id'=?";
        
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery(); 
        
        Product product = new Product();                
        product.setId(rs.getLong("id"));
        product.setUserId(rs.getLong("userId"));                
        product.setBookId(rs.getLong("bookId"));
        product.setDescription(rs.getString("description"));
        product.setQuantity(rs.getLong("quantity"));
        product.setActive(rs.getBoolean("active"));
        product.setExcluded(rs.getBoolean("excluded"));            
        product.setCreatedAt(rs.getTimestamp("createdAt"));
        product.setUpdatedAt(rs.getTimestamp("updatedAt"));
                
        return product;
    }
        public Long findProductId(Transacao tr, Product product) throws Exception{
        Connection con = tr.obterConexao();
        String sql = "SELECT id from tb_product where description = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, product.getDescription());
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getLong("id");
    }
        
    public void save(Transacao tr, Product product) throws Exception{
        Connection con = tr.obterConexao();
        String sql = "INSERT INTO tb_product (userId, bookId, description, quantity, active, excluded) VALUE (?, ?, ?, ? , ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, product.getUserId());
        ps.setLong(2, product.getBookId());
        ps.setString(3, product.getDescription());
        ps.setLong(4, product.getQuantity());
        ps.setBoolean(5, product.isActive());
        ps.setBoolean(6, product.isExcluded());
        int result = ps.executeUpdate();
    }
          
    
    public ArrayList<Pedido> ver_pedido (Transacao tr, long id ) throws Exception {
        Connection con = tr.obterConexao();
        String sql = "select tb_order.id as orderId, name, productId, title, price, tb_order_product.quantity, tb_order.createdAt from tb_order_product, tb_order, tb_user, tb_product, tb_book where tb_order_product.orderId = tb_order.id and tb_order.userId = tb_user.id and productId = tb_product.id and tb_product.bookId = tb_book.id and tb_user.id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<Pedido> pedidos = new ArrayList();
        if (rs != null){
            while (rs.next()){
                Pedido p = new Pedido();
                p.setorderId(rs.getLong("orderId"));
                p.setname(rs.getString("name"));
                p.setproductId(rs.getLong("productId"));
                p.settitle(rs.getString("title"));
                p.setprice(rs.getLong("price"));
                p.setquantity(rs.getLong("quantity"));
                p.setCreatedAt(rs.getTimestamp("CreatedAt"));
                pedidos.add(p);
                }
            }
        ps.close(); 
        rs.close();
        return pedidos;

    }
    
    public void suspend(String productId, Transacao tr) throws Exception{
        Connection con = tr.obterConexao();
        String sql = "UPDATE tb_product SET 'active'=0 WHERE 'productId'=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, productId);             
        
    }
    
    public void activate(String productId, Transacao tr) throws Exception{
        Connection con = tr.obterConexao();
        String sql = "UPDATE tb_product SET 'active'=1 WHERE 'productId'=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, productId);             
        
    }
    
    public static void main (String args[]) throws Exception{
//        Transacao tr = new Transacao();
//        ProductDao dao = new ProductDao();
//        tr.begin();
//        Product produto = new Product();
//        produto.setBookId(1);
//        produto.setUserId(1);
//        produto.setDescription("lalala2");
//        produto.setQuantity(1);
//        produto.setActive(true);
//        produto.setExcluded(true);
//        dao.save(tr,produto);
//        tr.commit();
    }
    
    
}