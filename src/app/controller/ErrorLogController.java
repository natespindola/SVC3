package app.controller;

import app.model.dao.ErrorLogDao;
import app.model.entity.Exemplos.ErrorLog;
import core.Transacao;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorLogController
{
    public static boolean remover (ErrorLog log) throws Exception
    {
        Transacao tr = new Transacao();

        try {
            tr.begin();
            ErrorLogDao dao = new ErrorLogDao();
            dao.save(log,tr);
            tr.commit();
            return true;
        }catch(Exception e)
        {
            tr.rollback();
            e.printStackTrace();
            System.out.println("Erro fatal não tem mais o que fazer");
        }
        return false;
    }
    
    public static boolean incluir (ErrorLog log) throws Exception
    {
        Transacao tr = new Transacao();

        try {
            tr.begin();
            ErrorLogDao dao = new ErrorLogDao();
            dao.save(log,tr);
            tr.commit();
            return true;
        }catch(Exception e)
        {
            tr.rollback();
            e.printStackTrace();
            System.out.println("Erro fatal não tem mais o que fazer");
        }
        return false;
    }
    
    public Vector buscar(){
        Vector v = new Vector();
        Transacao tr = new Transacao();
        ErrorLogDao data = new ErrorLogDao();
        try {
            tr.beginReadOnly();
            v = data.buscar(tr);
            for(int i=0;i<v.size();i++){
                ErrorLog er = (ErrorLog) v.get(i);
                System.out.println("ID:" +er.getId());               
            }
        } catch (Exception ex) {
            Logger.getLogger(ErrorLogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return v;
    }
}
