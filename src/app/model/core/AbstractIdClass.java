package app.model.core;

import java.math.BigInteger;
import java.lang.String;

public abstract class AbstractIdClass
{
    private long id;

    private String tableName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public abstract String getTableName();
}
