package app.model.entity;

import app.model.entity.*;
import app.model.core.AbstractDateClass;
import app.model.exception.StringSizeException;
import app.model.helper.ProfileEnum;

import java.lang.String;

public class User extends AbstractDateClass
{
    private String ID;
    private String Nome_user;
    private String Funcao;
    private String Email;
    private String Telefone;
    private String Senha;
    private String Usuario;

    /**
     *
     * @return String
     */
    @Override
    public String getTableName()
    {
        return "Usuario";
    }

    /**
     *
     * @return
     */
    public String getName()
    {
        return Nome_user;
    }

    public void setName(String Nome_user)
    {
        this.Nome_user = Nome_user;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email)
    {
        this.Email = Email;
    }

    public String getFuncao()
    {
        return Funcao;
    }

    public void setFuncao(String Funcao)
    {
        this.Funcao = Funcao;
    }

    public String getID() {
        return ID;
    }

    public String getUser()
    {
        return Nome_user;
    }

    public void setUser(String Usuario)
    {
        this.Usuario = Usuario;
    }
    
    public String getPassword() {
        return Senha;
    }

    public void setPassword(String Senha) throws StringSizeException
    {
        this.Senha = Senha;
    }

    public String getPhone() {
        return Telefone;
    }

    public void setPhone(String Telefone)
    {
        this.Telefone = Telefone; 
    }
}
