package app.model.dao;

import app.model.core.AbstractDaoFactory;
import app.model.core.AbstractIdClass;
import app.model.entity.Exemplos.AdminUser;

import javax.swing.text.html.parser.Entity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import core.Transacao;
import java.sql.SQLException;
import java.sql.Connection;

public class AdminUserDao extends AbstractDaoFactory
{
    public AdminUserDao()
    {
        super(new AdminUser());
    }

    public void add(AdminUser entity)
    {
        String[] fields = new String[3];
        String[] values = new String[3];

        fields[0] = "user";
        fields[1] = "email";
        fields[2] = "password";
        values[0] = "?";
        values[1] = "?";
        values[2] = "?";
        this.queryBuilder.buildInsert(this.entity.getTableName(),fields);
        try {
            this.queryBuilder.addValues(values);
            PreparedStatement pst = this.queryBuilder.getResult();
            pst.setString(1,entity.getUser());
            pst.setString(2,entity.getEmail());
            pst.setString(3,entity.getPassword());
            pst.execute();
            pst.close();
            System.out.println("Inserido");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(AdminUser entity)
    {
        String[] fields = new String[3];

        fields[0] = "user";
        fields[1] = "email";
        fields[2] = "password";

        this.queryBuilder.buildUpdate(this.entity.getTableName());
        this.queryBuilder.buildSet(fields);
        this.queryBuilder.buildWhere("id = ?");
        try {
            PreparedStatement pst = this.queryBuilder.getResult();
            pst.setString(1,entity.getUser());
            pst.setString(2,entity.getEmail());
            pst.setString(3,entity.getPassword());
            pst.setLong(4,entity.getId());
            pst.execute();
            pst.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<AdminUser> findAll() {
        this.queryBuilder.buildSelect();
        this.queryBuilder.buildFrom(this.entity.getTableName());
        List<AdminUser> admins = null;
        try {
            PreparedStatement pst = this.queryBuilder.getResult();
            ResultSet rs = pst.executeQuery();
            admins = new ArrayList<AdminUser>();
            while (rs.next()) {
                AdminUser admin = new AdminUser();
                admin.setId(rs.getLong("id"));
                admin.setUser(rs.getString("user"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setCreatedAt(rs.getTimestamp("createdAt"));
                admin.setUpdatedAt(rs.getTimestamp("updatedAt"));
                admins.add(admin);
            }
            rs.close();
            pst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins;
    }
    public void remove(int id,Transacao tr) {
        try {
            Connection con = tr.obterConexao();
            String sql = ("DELETE FROM tb_admin WHERE id = ?");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1,id);
            pst.execute();
            pst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AbstractIdClass> findAll(String[] select) {
        return null;
    }

    @Override
    public List<AbstractIdClass> findAll(String orderBy) {
        return null;
    }

    @Override
    public List<AbstractIdClass> findAll(String orderBy, String order) {
        return null;
    }

    @Override
    public List<AbstractIdClass> findAll(String[] select, String orderBy) {
        return null;
    }

    @Override
    public List<AbstractIdClass> findBy(String[] select, String[] predicates) {
        return null;
    }


    public AdminUser findById(int id)
    {
        return null;
    }
    
    public boolean isAdminUserRegistered(AdminUser adminuser, Transacao tr) throws SQLException
    {
        Connection con = tr.obterConexao();
        String sql = "SELECT id FROM tb_admin WHERE user = ? AND password = ? ORDER BY id desc LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,adminuser.getUser());
        ps.setString(2,adminuser.getPassword());       
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst() ) {
            return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
//        AdminUserDao dao = new AdminUserDao();
//        AdminUser entity = new AdminUser();
//        Transacao tr = new Transacao();
//            try{
//            tr.begin();
//            entity.setId(5);
//            entity.setPassword("lala");
//            entity.setUser("Sebastianu");
//            entity.setEmail("seb.kape@gmail.com");
//            dao.add(entity);
//            dao.update(entity);
//            dao.remove((int) entity.getId(),tr);
//            if(dao.isAdminUserRegistered(entity, tr)){
//            System.out.println("usuario registrado");
//            } else{
//                    System.out.println("usuario nao registrado");
//                    }
//            
//            
//            tr.commit();
//        
//        } catch(Exception e) {
//                System.out.println("erro");
//                e.printStackTrace();}       
    }
}
