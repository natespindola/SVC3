package app.model.dao;

import app.model.entity.Exemplos.Product;
import app.model.entity.Exemplos.ProductInfos;
import app.model.entity.Exemplos.ProductPrice;
import core.Transacao;
import app.model.entity.TipoItem;
import app.model.helper.PeriodicityTypeEnum;
import app.model.helper.PriceTypeEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoItemDao
{
        
    public void save(Transacao tr, TipoItem tipoItem) throws Exception{
        Connection con = tr.obterConexao();
        String sql = "INSERT INTO TipoItens (NomeItem, QtdAdulto, QtdInfantil, VolumeMassa) VALUE (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, tipoItem.getNomeItem());
        ps.setInt(2, tipoItem.getQtdAdulto());
        ps.setInt(3, tipoItem.getQtdInfantil());
        ps.setString(4, tipoItem.getVolumeMassa());
        int result = ps.executeUpdate();
    }
    
    public void update(Transacao tr, TipoItem tipoItem) throws Exception{
        Connection con = tr.obterConexao();
        String sql = "UPDATE TipoItens SET NomeItem = ?, QtdAdulto = ?, QtdInfantil = ?, VolumeMassa = ? WHERE ID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, tipoItem.getNomeItem());
        ps.setInt(2, tipoItem.getQtdAdulto());
        ps.setInt(3, tipoItem.getQtdInfantil());
        ps.setString(4, tipoItem.getVolumeMassa());
        ps.setInt(5, tipoItem.getTipoItemID());        
        int result = ps.executeUpdate();
    }
    
    public ArrayList<TipoItem> findByName(Transacao tr, String nomeItem) throws Exception{
        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM TipoItens WHERE NomeItem LIKE ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + nomeItem + "%");
        ResultSet rs = ps.executeQuery();
        ArrayList<TipoItem> results = new ArrayList<TipoItem>();
        while (rs.next()) {
            TipoItem tipoItem = new TipoItem();
            tipoItem.setTipoItemID(rs.getInt("ID"));
            tipoItem.setNomeItem(rs.getString("NomeItem"));
            tipoItem.setQtdAdulto(rs.getInt("QtdAdulto"));
            tipoItem.setQtdInfantil(rs.getInt("QtdInfantil"));
            tipoItem.setVolumeMassa(rs.getString("VolumeMassa"));
            results.add(tipoItem);
        }
        rs.close();
        ps.close();
        return results;
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