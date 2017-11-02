package app.model.dao;

import core.Transacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao
{
    
    public boolean isUserRegistered(String user, String password, Transacao tr) throws SQLException
    {
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM Usuario WHERE Login = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        boolean result = false;
        ps.setString(1,user);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            if(rs.getString("Senha").compareTo(password) == 0){
                result = true;
            }else{
                result = false;
            }
            
        }
        rs.close();
        ps.close();
        return result;
    }
    
     public String userType(Transacao tr, String nome) throws Exception
    {
        try {
            Connection con = tr.obterConexao();
            String sql = "SELECT Usuario.Funcao from Usuario WHERE Usuario.Login = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            String userType = "";
            if (rs.next()) {
                userType = rs.getString("Funcao");
            }
            return userType;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    /*
    public int block(User entity, Transacao tr) throws SQLException
    {
        //NESPINDOLA 27/11: Implementacao de funcionalidade
        Connection con = tr.obterConexao();
        String sql = "UPDATE db_sebo_virtual.tb_user SET blocked = '1' WHERE name = ? AND document = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getDocument());
        int result = ps.executeUpdate();
        return result;
    }
    
    public void save(User entity, Transacao tr) throws SQLException
    {
        Connection con = tr.obterConexao();
        String sql = "INSERT INTO tb_user (name, email, document, profile, password, phone, celPhone) VALUE (?, ?, ?, ? , MD5(?), ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getEmail());
        ps.setString(3, entity.getDocument());
        ps.setString(4, entity.getProfile());
        ps.setString(5, entity.getPassword());
        ps.setString(6, entity.getPhone());
        ps.setString(7, entity.getCelPhone());
        int result = ps.executeUpdate();
    }

    public long findLastInsertId(Transacao tr) throws SQLException
    {
        Connection con = tr.obterConexao();
        String sql = "SELECT id FROM tb_user ORDER BY id desc LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getLong("id");
    }
    
    public boolean isLoginValid(User user, Transacao tr) throws SQLException
    {
        Connection con = tr.obterConexao();
        String sql = "SELECT id FROM tb_user WHERE email = ? AND password = MD5(?) AND excluded = 0 AND activated = 1 AND blocked = 0 ORDER BY id desc LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,user.getEmail());
        ps.setString(2,user.getPassword());
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst() ) {
            return false;
        }
        return true;
    }
    

    public void update(User entity,long id, Transacao tr) throws Exception
    {
        Connection con = tr.obterConexao();
        String sql = "UPDATE tb_user SET name = ?, document = ?, celPhone = ?, phone = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getDocument());
        ps.setString(3, entity.getCelPhone());
        ps.setString(4, entity.getPhone());
        ps.setLong(5, id);
        ps.execute();
        ps.close();
    }

    public int delete(User entity, Transacao tr) throws SQLException
    {
        //NESPINDOLA 27/11: Implementacao de funcionalidade
        Connection con = tr.obterConexao();
        String sql = "UPDATE db_sebo_virtual.tb_user SET excluded = '1' WHERE name = ? AND document = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, entity.getName());
        //DELETE FROM db_sebo_virtual.tb_user WHERE name = "batata" AND document = ?
        ps.setString(2, entity.getDocument());
        //DELETE FROM db_sebo_virtual.tb_user WHERE name = "batata" AND document = "123123123"
        int result = ps.executeUpdate();
        return result;
    }

    public ArrayList<User> findAll(Transacao tr) throws Exception {
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM tb_user ORDER BY name";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<User> UserArray = new ArrayList<User>();   
        if (rs != null){
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setProfile("FISICA".equalsIgnoreCase(rs.getString("profile")) ? ProfileEnum.FISICA : ProfileEnum.JURIDICA);
                user.setDocument(rs.getString("document"));
                user.setPhone(rs.getString("phone"));
                user.setCelPhone(rs.getString("celPhone"));            
                user.setBlocked(rs.getBoolean("blocked"));
                user.setActivated(rs.getBoolean("activated"));
                user.setExcluded(rs.getBoolean("excluded"));
                user.setCreatedAt(rs.getTimestamp("createdAt"));
                user.setUpdatedAt(rs.getTimestamp("updatedAt"));
                UserArray.add(user);
                }
            }
        
        return UserArray;
        
    }
    public boolean checarEmail(User user, Transacao tr) throws Exception
    {
            Connection con = tr.obterConexao();
            String sql = "SELECT email FROM tb_user WHERE email = ? AND excluded = 0";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            if(!rs.isBeforeFirst()){
                return false;
            }
            return true;
        }
        public boolean checarSenha(User user, Transacao tr) throws Exception 
        {
            if(checarEmail(user,tr)){
                Connection con = tr.obterConexao();
                String sql = "SELECT password FROM tb_user WHERE email = ? AND excluded =0";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, user.getEmail());
                ResultSet rs = ps.executeQuery();
                if(!rs.isBeforeFirst()){
                    return false;
                }
                return true;
            }
            return false;
        }
        
        public String[] myproducts(Transacao tr) throws Exception
        {
            Connection con = tr.obterConexao();
            String sql = "select name,quantity,title,price,tb_product_price.createdAt,tb_product_price.updatedAt  from tb_user,tb_product,tb_book,tb_product_price\n" +
            "where tb_user.id = tb_product.userId\n" +
            "and tb_book.id = tb_product.bookId\n" +
            "and tb_product.Id = tb_product_price.productId\n";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = 0;
            String[] nomes_array;
            nomes_array = new String[1000000];
            if(rs!= null){
            while(rs.next())
            {
               String nome,quantity,title,price,createdAt,updatedAt;


               nome = rs.getString("name");
               quantity = rs.getString("quantity");
               title = rs.getString("title");
               price = rs.getString("price");
               createdAt = rs.getString("tb_product_price.createdAt");
               updatedAt = rs.getString("tb_product_price.updatedAt");

               nomes_array[count] = nome;
               nomes_array[count+1] = quantity;
               nomes_array[count+2] = title;
               nomes_array[count+3] = price;
               nomes_array[count+4] = createdAt;
               nomes_array[count+5] = updatedAt;
               count = count+6;
            }

            }
            ps.close();
            rs.close();
            return nomes_array;
        }

        public String esquecisenha(Transacao tr,String verifyemail)throws Exception
        {
            Connection con = tr.obterConexao();
            String sql = "select email from tb_user where email = '"+verifyemail+"' ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String email = rs.getString("email");
            ps.close();
            rs.close();
            return email;
        }

       public void mandarsenha(Transacao tr,String verifyemail, String senha)throws Exception
        {
            Connection con = tr.obterConexao();
            String sql = "update tb_user SET password = MD5(?) where email = ? and activated = 1 and blocked = 0 and excluded = 0";
            //String sql = "select name from tb_user where email = '"+verifyemail+"'";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, senha);
            ps.setString(2, verifyemail);
            ps.execute();
            ps.close();
        }

       public String userNameFromId(Transacao tr, String id) throws Exception
    {
        try {
            Connection con = tr.obterConexao();
            String sql = "SELECT tb_user.name from tb_user WHERE tb_user.id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            String title = "";
            if (rs.next()) {
                title = rs.getString("name");
            }
            return title;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
        
        public User getUserFromEmail(Transacao tr, String email) throws Exception
        {
            User user = new User();
            Connection con = tr.obterConexao();
            String sql = "SELECT * from tb_user WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setProfile("FISICA".equalsIgnoreCase(rs.getString("profile")) ? ProfileEnum.FISICA : ProfileEnum.JURIDICA);
            user.setDocument(rs.getString("document"));
            user.setPhone(rs.getString("phone"));
            user.setCelPhone(rs.getString("celPhone"));            
            user.setBlocked(rs.getBoolean("blocked"));
            user.setActivated(rs.getBoolean("activated"));
            user.setExcluded(rs.getBoolean("excluded"));
            user.setCreatedAt(rs.getTimestamp("createdAt"));
            user.setUpdatedAt(rs.getTimestamp("updatedAt"));            
            return user;
        }

        public User findById(Transacao tr, long id) throws Exception
        {
            Connection con = tr.obterConexao();
            String sql = "SELECT * FROM tb_user WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.isBeforeFirst()){
                rs.next();
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setProfile("FISICA".equalsIgnoreCase(rs.getString("profile")) ? ProfileEnum.FISICA : ProfileEnum.JURIDICA);
                user.setDocument(rs.getString("document"));
                user.setPhone(rs.getString("phone"));
                user.setCelPhone(rs.getString("celPhone"));
                user.setBlocked(rs.getBoolean("blocked"));
                user.setActivated(rs.getBoolean("activated"));
                user.setExcluded(rs.getBoolean("excluded"));
                user.setCreatedAt(rs.getTimestamp("createdAt"));
                user.setUpdatedAt(rs.getTimestamp("updatedAt"));
                return user;
            }else{
                return null;
            }
        }

        public void activateUser(Transacao tr, long id) throws Exception
        {
            Connection con = tr.obterConexao();
            String sql = "UPDATE tb_user SET activated = 1 WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1,id);
            ps.execute();
        }

        
        public void desbloquearUsuario(Transacao tr, Long id) throws Exception
        {
            Connection con = tr.obterConexao();
            String sql = "UPDATE tb_user SET blocked = 0 WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1,id);
            ps.execute();
        }

        
    public ArrayList<User> buscarBloqueados(Transacao tr) throws Exception {
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM tb_user where blocked = 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<User> UserArray = new ArrayList();
        if (rs != null){
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setProfile("FISICA".equalsIgnoreCase(rs.getString("profile")) ? ProfileEnum.FISICA : ProfileEnum.JURIDICA);
                user.setDocument(rs.getString("document"));
                user.setPhone(rs.getString("phone"));
                user.setCelPhone(rs.getString("celPhone"));            
                user.setBlocked(rs.getBoolean("blocked"));
                user.setActivated(rs.getBoolean("activated"));
                user.setExcluded(rs.getBoolean("excluded"));
                user.setCreatedAt(rs.getTimestamp("createdAt"));
                user.setUpdatedAt(rs.getTimestamp("updatedAt"));
                UserArray.add(user);
                }
            }
        
        return UserArray;
        
    }*/
        
}
