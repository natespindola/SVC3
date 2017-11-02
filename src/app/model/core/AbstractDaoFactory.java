package app.model.core;

import core.ConnectionFactory;
import core.query.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class AbstractDaoFactory implements DaoInterface
{
    protected AbstractIdClass entity;
    protected QueryBuilder queryBuilder;
    protected Connection connection;

    public AbstractDaoFactory(AbstractIdClass entity)
    {
        this.entity       = entity;
        this.queryBuilder = new QueryBuilder();
        this.connection   = this.queryBuilder.getConnection();
    }

    public abstract List<AbstractIdClass> findAll(String[] select);
    public abstract List<AbstractIdClass> findAll(String orderBy);
    public abstract List<AbstractIdClass> findAll(String orderBy, String order);
    public abstract List<AbstractIdClass> findAll(String[] select, String orderBy);
    public abstract List<AbstractIdClass> findBy(String[] select, String[] predicates);
}
