package app.model.dao;

import app.model.entity.Exemplos.ErrorLog;
import core.Transacao;
import core.query.QueryBuilder;
import core.query.QueryBuilderException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorLogDao
{
    public void save(ErrorLog entity, Transacao tr) throws SQLException {

        QueryBuilder q = new QueryBuilder();
        String[] fields = new String[3];
        String[] values = new String[3];

        fields[0] = "errorTrace";
        fields[1] = "controller";
        fields[2] = "method";
        values[0] = "?";
        values[1] = "?";
        values[2] = "?";
        q.buildInsert(entity.getTableName(), fields);
        Connection connection = tr.obterConexao();
        try {
            q.addValues(values);
            PreparedStatement pst = connection.prepareStatement(q.getSql());
            pst.setString(1, entity.getErrorTrace());
            pst.setString(2, entity.getController());
            pst.setString(3, entity.getAction());
            pst.execute();
            pst.close();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        }
    }
    
    public Vector buscar(Transacao tr)throws Exception {
        Vector v = new Vector();
        String sql = "select * from tb_error_log";
        Connection con = tr.obterConexao();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            ErrorLog el = new ErrorLog();
            el.setId(rs.getLong("id"));
            el.setController(rs.getString("controller"));
            el.setErrorTrace(rs.getString("errorTrace"));
            el.setCreatedAt(rs.getTimestamp("createdAt"));
            el.setAction(rs.getString("method"));
            v.add(el);
        }
         
        return v;    
    }
    
}
