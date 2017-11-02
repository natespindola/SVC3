package app.controller;

import app.model.dao.LogDao;
import app.model.entity.Exemplos.Log;
import app.model.helper.LogTypeEnum;
import core.Transacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LogController
{
    public static boolean remover (Log log) throws Exception
    {
        Transacao tr = new Transacao();

        try {
            tr.begin();
            LogDao dao = new LogDao();
            dao.save(log,tr);
            tr.commit();
            return true;
        }catch(Exception e)
        {
            tr.rollback();
        }
        return false;
    }
    
    public static boolean incluir (Log log) throws Exception
    {
        Transacao tr = new Transacao();

        try {
            tr.begin();
            LogDao dao = new LogDao();
            dao.save(log,tr);
            tr.commit();
            return true;
        }catch(Exception e)
        {
            tr.rollback();
        }
        return false;
    }
    
    
    public Log[] buscarLog () throws Exception{
        Transacao tr = new Transacao();
        
        try{
            tr.beginReadOnly();
            LogDao logdao = new LogDao();
            Log[] l;
            l = logdao.buscarLog_tudo(tr);
            tr.commit();
            return l;
        }catch(Exception e){
        tr.rollback();
        }
        return null;
        }
    

    
    
    
    
    
    
    
    
}
