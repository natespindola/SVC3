package app.model.dao;

import app.model.entity.Exemplos.Log;
import app.model.entity.Exemplos.User;
import app.model.helper.ProfileEnum;
import core.Transacao;
import core.query.QueryBuilder;
import core.query.QueryBuilderException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class LogDao {
    public void save(Log entity, Transacao tr) throws SQLException {

        QueryBuilder q = new QueryBuilder();
        String[] fields = new String[3];
        String[] values = new String[3];

        fields[0] = "logType";
        fields[1] = "message";
        fields[2] = "relationshipId";
        values[0] = "?";
        values[1] = "?";
        values[2] = "?";
        q.buildInsert(entity.getTableName(), fields);
        Connection connection = tr.obterConexao();
        try {
            q.addValues(values);
            PreparedStatement pst = connection.prepareStatement(q.getSql());
            pst.setString(1, entity.getLogType());
            pst.setString(2, entity.getMessage());
            pst.setLong(3, entity.getRelationshipId());
            pst.execute();
            pst.close();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        }
    }
    
    /*
    public Vector buscar_log () throws Exception
    {
     Transacao tr = new Transacao();
     tr.beginReadOnly();
     Connection con = tr.obterConexao();
     String sql = "select * from tb_log";
     PreparedStatement ps = con.prepareStatement(sql);
     ResultSet rs = ps.executeQuery();
     System.out.println("query executada");
     Vector log = new Vector();
     while (rs.next()) {
        Log c = new Log();
        c.setId (rs.getLong("id"));
        c.setMessage (rs.getString("message"));
        //completar com outras colunas
        System.out.println(" got " + c.getNome());
        log.add(c);
     }
     return log;
    }
    */
    


    public Log[] buscarLog_tudo (Transacao tr) throws Exception {

        Connection con = tr.obterConexao();
        String sql = "SELECT * FROM tb_log";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        Log[] LogArray;
        LogArray = new Log[100000];
        if (rs != null){
            while (rs.next()){
                Log log = new Log();
                log.setLogType(rs.getString("logType"));
                log.setRelationshipId(rs.getLong("relationshipId"));
                log.setMessage(rs.getString("message"));
                log.setCreatedAt(rs.getTimestamp("CreatedAt"));


                LogArray[count] = log;
                count+=1;
                }
            }

        ps.close(); 
        rs.close();

        return LogArray;
        
    }
    
    
    
    
    
    
}
