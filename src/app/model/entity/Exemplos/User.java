package app.model.entity.Exemplos;

import app.model.core.AbstractDateClass;
import app.model.exception.StringSizeException;
import app.model.helper.ProfileEnum;

import java.lang.String;

public class User extends AbstractDateClass
{
    private String name;
    private String email;
    private String document;
    private String profile;
    private String password;
    private String celPhone;
    private String phone;
    private boolean blocked;
    private boolean activated;
    private boolean excluded;

    /**
     *
     * @return String
     */
    @Override
    public String getTableName()
    {
        return "tb_user";
    }

    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getDocument()
    {
        return document;
    }

    public void setDocument(String document)
    {
        this.document = document;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(ProfileEnum profile)
    {
        this.profile = profile.name();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws StringSizeException
    {
        this.password = password;
    }

    public String getCelPhone() {
        return celPhone;
    }

    public void setCelPhone(String celPhone) throws StringSizeException
    {
        this.celPhone = celPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public boolean isBlocked()
    {
        return blocked;
    }

    public void setBlocked(boolean blocked)
    {
        this.blocked = blocked;
    }

    public boolean isActivated()
    {
        return activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    public boolean isExcluded()
    {
        return excluded;
    }

    public void setExcluded(boolean excluded)
    {
        this.excluded = excluded;
    }
}
