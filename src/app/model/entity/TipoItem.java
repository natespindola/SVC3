package app.model.entity;

import app.model.core.AbstractIdClass;

public class TipoItem extends AbstractIdClass
{
  private int TipoItemID;
  private String NomeItem;
  private int QtdAdulto;
  private int QtdInfantil;
  private String VolumeMassa;

    @Override
    public String getTableName(){
        return "TipoItens";
    }

    public int getTipoItemID(){
        return TipoItemID;
    }

    public void setTipoItemID(int TipoItemID){
        this.TipoItemID = TipoItemID;
    }
    
    public String getNomeItem(){
        return NomeItem;
    }

    public void setNomeItem(String NomeItem){
        this.NomeItem = NomeItem;
    }
    
    public int getQtdAdulto(){
        return QtdAdulto;
    }

    public void setQtdAdulto(int QtdAdulto){
        this.QtdAdulto = QtdAdulto;
    }

    public int getQtdInfantil(){
        return QtdInfantil;
    }

    public void setQtdInfantil(int QtdInfantil){
        this.QtdInfantil = QtdInfantil;
    }
    
    public String getVolumeMassa(){
        return VolumeMassa;
    }

    public void setVolumeMassa(String VolumeMassa){
        this.VolumeMassa = VolumeMassa;
    }
}
