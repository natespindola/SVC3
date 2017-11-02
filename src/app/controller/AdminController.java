package app.controller;

import app.model.dao.LogDao;
import app.model.dao.AdminUserDao;
import app.model.entity.Exemplos.ErrorLog;
import app.model.entity.Exemplos.Log;
import app.model.helper.LogTypeEnum;
import app.model.helper.ProfileEnum;
import core.Transacao;
import app.model.entity.Exemplos.AdminUser;
import core.Utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Vector;

public class AdminController
{
    public boolean incluir (AdminUser adminuser) throws Exception{

            // validacao das regras de negocio
            if (!isAdminUserValid(adminuser)) {
                return false;
            }

        try {
                AdminUserDao dao = new AdminUserDao();
                dao.add(adminuser);
                Log log = new Log();
                log.setMessage("Usuário administrador cadastrado com sucesso.");
                //log.setRelationshipId(getLastInsert());
                //log.setLogType(LogTypeEnum.USUARIO.name());
                LogController.incluir(log);
                return true;
            } catch(Exception e) {
                //tr.rollback();
                ErrorLog error = new ErrorLog();
                error.setAction("add");
                error.setController("AdminUser");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                error.setErrorTrace(sw.toString());
                System.out.println("erro ao incluir " + adminuser.getUser());
                ErrorLogController.incluir(error);
            }
            return false;
        } // incluir

        private boolean isAdminUserValid(AdminUser adminuser) throws Exception
        {
            boolean result = true;
            if (
                    (isEmpty(adminuser.getUser())) ||
                    (isEmpty(adminuser.getEmail()))
                    ) {
                result = false;
            }
            if(adminuser.getUser().indexOf(" ") <= 0){
                result = false;
            }
            if(!(Utils.isValidEmailAddress(adminuser.getEmail()))){
                result = false;
            }
            
            Transacao tr = new Transacao();
            AdminUserDao dao = new AdminUserDao();
            try {
                tr.beginReadOnly();
                result = !(dao.isAdminUserRegistered(adminuser, tr));
                tr.commit();
            }
            catch(Exception e){
                tr.rollback();
                e.printStackTrace();
                result =  false;
                }
            return result;
        }
        
        public boolean isAdminUserRegistered (AdminUser adminuser){
            boolean result = false;
            AdminUserDao dao = new AdminUserDao();
            Transacao tr = new Transacao();
            try{
                tr.beginReadOnly();
                result = dao.isAdminUserRegistered(adminuser,tr);
                tr.commit();
            }catch(Exception e){
                result = false;
                System.out.println("erro ao verificar se admin esta registrado");
                e.printStackTrace();
                }
            
            return result;
        }
        

/*

        public boolean atualizar(User user) throws Exception {
//            Transacao tr = new Transacao();
//            try{
//                // inserir validacoes de regras de negocio
//                tr.begin();
//                User cdata = new ContatoData();
//                cdata.atualizar(contato, tr);
//                tr.commit();
//                return true;
//            } catch (Exception e) {
//                tr.rollback();
//                System.out.println("erro ao atualizar" + contato.getNome());
//                e.printStackTrace();
//            }
            return false;
        } // atualizar

        public User buscar(int idobj) throws Exception{
//            Transacao tr = new Transacao();
//            try{
//                tr.beginReadOnly();
//                ContatoData cdata = new ContatoData();
//                ContatoDO c = cdata.buscar(idobj, tr);
//                tr.commit();
//                return c;
//            } catch (Exception e) {
//                tr.rollback();
//                System.out.println("erro ao buscar" + idobj);
//                e.printStackTrace();
//            }
            return null;
        } // buscar

        public Vector pesquisar(String nome) {
//            if ( isEmpty(nome) )
//                return null;
//
//            Transacao tr = new Transacao();
//            try {
//                tr.beginReadOnly();
//                ContatoData cdata = new ContatoData();
//                Vector v = cdata.pesquisarPorNome(nome, tr);
//                tr.commit();
//                return v;
//            } catch(Exception e) {
//                System.out.println("erro ao pesquisar " + nome);
//                e.printStackTrace();
//            }
            return null;
        } // pesquisar
*/
        private boolean isEmpty(String s) {
            if (null == s)
                return true;
            if (s.length() == 0)
                return true;
            return false;
        }
/*
        private long getLastInsert() throws Exception
        {
            Transacao tr = new Transacao();
            try{
                tr.beginReadOnly();
                UserDao dao = new UserDao();
                long u = dao.findLastInsertId(tr);
                tr.commit();
                return u;
            } catch (Exception e) {
                tr.rollback();
                System.out.println("erro ao buscar");
                e.printStackTrace();
            }
            return -1;
        }*/

//        public static void main (String args[]) throws Exception
//        {
//
//            }
}