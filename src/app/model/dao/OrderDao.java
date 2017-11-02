package app.model.dao;

import app.model.entity.Exemplos.Adress;
import app.model.entity.Exemplos.Book;
import app.model.entity.Exemplos.Order;
import app.model.entity.Exemplos.Product;
import app.model.entity.Exemplos.User;
import app.model.exception.StringSizeException;
import core.Transacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by User on 11/10/2016.
 */
public class OrderDao {
    
    public void add(Order order, Transacao tr) throws Exception{
        Connection con = tr.obterConexao();
        String sql = "INSERT INTO tb_order (userId, adressId) VALUE (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, order.getUserId());
        ps.setLong(2, order.getAdressId());
        ps.executeUpdate();  
    }
    
    
    public ArrayList<Order> viewByOrder(String orderId, Transacao tr) throws Exception{
        
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM tb_order WHERE 'orderId'=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, orderId);
        
        ResultSet rs = ps.executeQuery();
        ArrayList<Order> results = new ArrayList<Order>();
            while (rs.next()) {
                Order order = new Order();
                
                order.setId(rs.getLong("id"));
                order.setUserId(rs.getLong("userId"));
                order.setAdressId(rs.getLong("adressId"));
                order.setUpdatedAt(rs.getTimestamp("updatedAt"));
                order.setCreatedAt(rs.getTimestamp("createdAt"));
                order.setEvaluation(rs.getString("evaluate"));

                results.add(order);
            }
            rs.close();
            ps.close();
            return results;
    }
    
    public long getLastId(Transacao tr) throws Exception{
        Connection con = tr.obterConexao();
        String sql = "SELECT id FROM tb_order ORDER BY id desc LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getLong("id");
    }
    
    public Vector mostrarPedidos(Transacao tr) throws SQLException
    {
        Vector v = new Vector();
        
        Connection con = tr.obterConexao();
        String sql = "SELECT o.*, u.name FROM db_sebo_virtual.tb_order as o INNER JOIN tb_user as u ON u.id = o.userId ORDER BY id desc";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Order o = new Order();
            User u = new User();
            
            o.setCreatedAt(rs.getTimestamp("createdAt"));
            o.setId(rs.getLong("id"));
            o.setUpdatedAt(rs.getTimestamp("updatedAt"));
            o.setUserId(rs.getLong("userId"));
            u.setName(rs.getNString("name"));
            
            Vector v2 = new Vector();
            v2.add(o);
            v2.add(u);
            v.add(v2);
        }
        
        return v;
    }
   
    
    public Vector mostrarDetalhesPedido(Transacao tr, String id) throws SQLException, StringSizeException {
        Vector v = new Vector();

        Connection con = tr.obterConexao();
        String sql = "SELECT  u.name, a.adress,  p.id, b.title, b.author" +
                    "	FROM tb_adress as a, tb_order as o, tb_order_product as op, tb_book as b, tb_user as u, tb_product as p" +
                            "	WHERE o.id = op.orderId" +
                            "    AND op.productId = p.id" +
                            "    AND p.id = b.id" +
                            "    AND o.userId = u.id" +
                            "    AND o.adressId = a.id"+
                            "    AND o.id = ?" ;
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            User u = new User();
            Adress a = new Adress();
            Product p = new Product();
            Book b = new Book();
            
            u.setName(rs.getNString("name"));
            a.setAdress(rs.getString("adress"));
            p.setId(rs.getLong("id"));
            b.setTitle(rs.getString("title"));
            b.setAuthor(rs.getString("author"));
            
            Vector v2 = new Vector();
            v2.add(u);
            v2.add(a);
            v2.add(p);
            v2.add(b);
            v.add(v2);
        }
        
        return v;
    }
    
    
    public String adicionarcomentario(Transacao tr,String comentario, long orderId)throws Exception
        {
            Connection con = tr.obterConexao();
            String sql = "update tb_order SET evaluation = ? where id = ? ";
            //String sql = "select name from tb_user where email = '"+verifyemail+"'";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, comentario);
            ps.setLong(2, orderId);
            ps.execute();
            ps.close();
            return "Concluído com sucesso";
        }
}