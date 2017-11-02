package app.controller;

import app.model.dao.LogDao;
import app.model.dao.AdressDao;
import app.model.entity.Exemplos.ErrorLog;
import app.model.entity.Exemplos.Log;
import app.model.helper.LogTypeEnum;
import app.model.helper.ProfileEnum;
import core.Transacao;
import app.model.entity.Exemplos.Adress;
import core.Utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Vector;
import java.util.ArrayList;

public class AdressController {
    
        public boolean AddAdress (Adress adress) throws Exception{
        Transacao tr = new Transacao();

        try {
                tr.begin();
                AdressDao dao = new AdressDao();
                dao.add(adress,tr);
                tr.commit();
                Log log = new Log();
                log.setMessage("Endereco cadastrado com sucesso.");
                log.setRelationshipId(getLastInsert());
                log.setLogType(LogTypeEnum.ADRESS.name());
                LogController.incluir(log);
                return true;
            } catch(Exception e) {
                tr.rollback();
                ErrorLog error = new ErrorLog();
                error.setAction("incluir");
                error.setController("Adress");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                error.setErrorTrace(sw.toString());
                System.out.println("erro ao incluir " + adress.getAdress());
                ErrorLogController.incluir(error);
            }
            return false;
        } // incluir
    
        public boolean UpdateAdress (Adress adress, String idAdress) throws Exception{
        Transacao tr = new Transacao();

        try {
                tr.begin();
                AdressDao dao = new AdressDao();
                dao.update(adress,idAdress,tr);
                tr.commit();
                Log log = new Log();
                log.setMessage("Endereco modificado com sucesso.");
                log.setRelationshipId(getLastInsert());
                log.setLogType(LogTypeEnum.ADRESS.name());
                LogController.incluir(log);
                return true;
            } catch(Exception e) {
                tr.rollback();
                ErrorLog error = new ErrorLog();
                error.setAction("modificar");
                error.setController("Adress");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                error.setErrorTrace(sw.toString());
                System.out.println("erro ao modificar " + adress.getAdress());
                ErrorLogController.incluir(error);
            }
            return false;
        } // incluir
         
         
        private boolean isEmpty(String s) {
            if (null == s)
                return true;
            if (s.length() == 0)
                return true;
            return false;
        }

        public long getLastInsert() throws Exception
        {
            Transacao tr = new Transacao();
            try{
                tr.beginReadOnly();
                AdressDao dao = new AdressDao();
                long u = dao.findLastInsertId(tr);
                tr.commit();
                return u;
            } catch (Exception e) {
                tr.rollback();
                System.out.println("erro ao buscar");
                e.printStackTrace();
            }
            return -1;
        }
        
        public Adress SearchAdressByUser(String idUser) throws Exception {
            Transacao tr = new Transacao();
            try{
                AdressDao adressDao = new AdressDao(); 
                Adress adress;
                tr.beginReadOnly();
                adress = adressDao.findByUser(idUser,tr);
                tr.commit();
                return adress;
            }catch(Exception e) {
                System.out.println("erro ao gerar lista");
                e.printStackTrace();
                return null;
            }
            
        }
        
        public Adress[] SearchAllAdress() throws Exception {
            Transacao tr = new Transacao();
            try{
                AdressDao adressdao = new AdressDao(); 
                Adress[] UserArray;
                tr.beginReadOnly();
                UserArray = adressdao.findAll(tr);
                tr.commit();
                return UserArray;
            }catch(Exception e) {
                System.out.println("erro ao gerar lista");
                e.printStackTrace();
                return null;
            }
            
        }
        public ArrayList<Adress> SearchAllAdressByUser(String id) throws Exception {
            Transacao tr = new Transacao();
            try{
                AdressDao adressDao = new AdressDao(); 
                ArrayList<Adress> results = new ArrayList<Adress>();
                tr.beginReadOnly();
                results = adressDao.findAllByUser(id,tr);
                tr.commit();
                return results;
            }catch(Exception e) {
                System.out.println("erro ao gerar lista");
                e.printStackTrace();
                return null;
            }
            
        }
}
