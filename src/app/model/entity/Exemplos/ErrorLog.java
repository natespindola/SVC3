package app.model.entity.Exemplos;

import app.model.core.AbstractIdClass;

import java.math.BigInteger;
import java.sql.Timestamp;

public class ErrorLog extends AbstractIdClass
{
    private String errorTrace;
    private String controller;
    private String action;
    private Timestamp createdAt;

    public String getErrorTrace()
    {
        return errorTrace;
    }

    public void setErrorTrace(String errorTrace)
    {
        this.errorTrace = errorTrace;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller)
    {
        this.controller = controller;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public Timestamp getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt)
    {
        this.createdAt = createdAt;
    }

    @Override
    public String getTableName()
    {
        return "tb_error_log";
    }
}
