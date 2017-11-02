package core.query;

import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;

public abstract class QueryPrototype implements Cloneable
{
    public QueryPrototype clone() throws CloneNotSupportedException{
        return (QueryPrototype) super.clone();
    }
}
