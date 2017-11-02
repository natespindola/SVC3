package app.model.core;

import java.sql.Connection;
import java.util.List;

public interface DaoInterface
{
    List<AbstractIdClass> findAll(String[] select);
    List<AbstractIdClass> findAll(String orderBy);
    List<AbstractIdClass> findAll(String orderBy, String order);
    List<AbstractIdClass> findAll(String[] select, String orderBy);
    List<AbstractIdClass> findBy(String[] select, String[] predicates);
}
