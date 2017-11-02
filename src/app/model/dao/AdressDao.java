package app.model.dao;

import app.model.core.AbstractIdClass;
import app.model.core.DaoInterface;
import app.model.entity.Exemplos.Adress;
import core.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

public class AdressDao {
    
    public void add(Adress entity, Transacao tr) throws SQLException
    {
        Connection con = tr.obterConexao();
        String sql = "INSERT INTO `tb_adress` (`zipCode`, `adress`, `number`, `complement`, `reference`, `userId`) VALUES (?,?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement(sql);
        //ps.setString(1, entity.getAdressType());
        ps.setString(1, entity.getZipCode());
        ps.setString(2, entity.getAdress());
        ps.setString(3, entity.getNumber());
        ps.setString(4, entity.getComplement());
        ps.setString(5, entity.getReference());
        ps.setString(6, entity.getuserId());
        ps.execute();
    }
    
    public long findLastInsertId(Transacao tr) throws SQLException
    {
        Connection con = tr.obterConexao();
        String sql = "SELECT id FROM tb_adress ORDER BY id desc LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getLong("id");
    }
    
    public void update(Adress entity,String id, Transacao tr) throws Exception
    {
        Connection con = tr.obterConexao();
        String sql = "UPDATE `db_sebo_virtual`.`tb_adress` SET `typeAdressId`=?, `zipCode`=?, `adress`=?, `number`=?, `complement`=?, `reference`=? WHERE `userId` = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, entity.getAdressType());
        ps.setString(2, entity.getZipCode());
        ps.setString(3, entity.getAdress());
        ps.setString(4, entity.getNumber());
        ps.setString(5, entity.getComplement());
        ps.setString(6, entity.getReference());
        //ps.setString(7, entity.getuserId());
        ps.setString(7, id);
        ps.execute();
    }
    
    public Adress findByUser (String id, Transacao tr) throws Exception
    {
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM tb_adress WHERE `userId` = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Adress adress = new Adress();
        try{
        adress.setAdressType(rs.getString("typeAdressId"));
        adress.setZipCode(rs.getString("zipCode"));
        adress.setAdress(rs.getString("adress"));
        adress.setNumber(rs.getString("number"));
        adress.setComplement(rs.getString("complement"));
        adress.setReference(rs.getString("reference"));
        adress.setUserId(rs.getString("userId"));
        } catch(Exception e){
            adress=null;
        }
        return adress;
    }
    
    public Adress[] findAll (Transacao tr) throws Exception
    {
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM tb_adress";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        int count = 0;
        Adress[] AdressArray;
        AdressArray = new Adress[100000];
        if (rs != null){
            while (rs.next()){
                Adress adress = new Adress();
                adress.setAdressType(rs.getString("typeAdressId"));
                adress.setZipCode(rs.getString("zipCode"));
                adress.setAdress(rs.getString("adress"));
                adress.setNumber(rs.getString("number"));
                adress.setComplement(rs.getString("complement"));
                adress.setReference(rs.getString("reference"));
                adress.setUserId(rs.getString("userId"));
                AdressArray[count] = adress;
                count+=1;
                }
            }
        return AdressArray;
    } 
    
    public ArrayList<Adress> findAllByUser (String userId, Transacao tr) throws Exception
    {
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM tb_adress WHERE `userId` = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, userId);
        ResultSet rs = ps.executeQuery();
        ArrayList<Adress> results = new ArrayList<Adress>();
        while (rs.next()) {
            Adress adress = new Adress();
            adress.setAdressType(rs.getString("typeAdressId"));
            adress.setZipCode(rs.getString("zipCode"));
            adress.setAdress(rs.getString("adress"));
            adress.setNumber(rs.getString("number"));
            adress.setComplement(rs.getString("complement"));
            adress.setReference(rs.getString("reference"));
            adress.setUserId(rs.getString("userId"));
            results.add(adress);
        }
        rs.close();
//        Adress adress = new Adress();
//        adress.setAdressType(rs.getString("typeAdressId"));
//        adress.setZipCode(rs.getString("zipCode"));
//        adress.setAdress(rs.getString("adress"));
//        adress.setNumber(rs.getString("number"));
//        adress.setComplement(rs.getString("complement"));
//        adress.setReference(rs.getString("reference"));
//        adress.setUserId(rs.getString("userId"));
        
        
        return results;
    }
    
}