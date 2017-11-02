package app.model.entity.Exemplos;

import app.model.core.AbstractIdClass;

import java.math.BigInteger;
import java.sql.Timestamp;

public class Log extends AbstractIdClass
{
    private String logType;
    private long relationshipId;
    private String message;
    private Timestamp createdAt;

    public String getLogType()
    {
        return logType;
    }

    public void setLogType(String logType)
    {
        this.logType = logType;
    }

    public long getRelationshipId()
    {
        return relationshipId;
    }

    public void setRelationshipId(long relationshipId)
    {
        this.relationshipId = relationshipId;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
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
        return "tb_log";
    }
}
