package app.model.entity.Exemplos;

import app.model.core.AbstractDateClass;
import app.model.exception.StringSizeException;

import java.lang.String;

public class AdminUser extends AbstractDateClass
{
    private String user;
    private String email;
    private String password;

    @Override
    public String getTableName()
    {
        return "tb_admin";
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
