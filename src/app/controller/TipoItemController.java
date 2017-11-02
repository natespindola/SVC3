package app.controller;

import app.model.dao.TipoItemDao;
import core.Transacao;
import app.model.entity.TipoItem;
import java.util.ArrayList;

public class TipoItemController 
{    
    public void inserir(TipoItem tipoItem){
        Transacao tr = new Transacao();
        try{
            TipoItemDao tipoItemDao = new TipoItemDao();
            tr.begin();
            tipoItemDao.save(tr,tipoItem);
            tr.commit();
        } catch(Exception e){
            System.out.println("erro ao adicionar produto");
        }
    }
    
    public ArrayList<TipoItem> consultar(String nomeItem) {
        Transacao tr = new Transacao();
        try {
            tr.beginReadOnly();
            TipoItemDao dao = new TipoItemDao();
            ArrayList<TipoItem> results = dao.findByName(tr, nomeItem);
            tr.commit();
            return results;
        } catch(Exception e) {
            System.out.println("erro ao pesquisar");
            e.printStackTrace();
            return null;            
        }
    }
    
    public void atualizar(TipoItem tipoItem){
        Transacao tr = new Transacao();
        try{
            TipoItemDao tipoItemDao = new TipoItemDao();
            tr.begin();
            tipoItemDao.update(tr,tipoItem);
            tr.commit();
        } catch(Exception e){
            System.out.println("erro ao adicionar produto");
        }
    }
}
