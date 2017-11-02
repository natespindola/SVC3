package core.query;

import java.sql.PreparedStatement;

interface QueryBuilderInterface
{
    //implemented
    void buildSelect();
    void buildSelect(String[] fields);
    void buildInsert(String into, String[] fields);
    void addSelect(String[] select);
    void buildUpdate(String table);
    void buildWhere(String where);
    void buildJoin(JoinEnum type, String firstTableAlias, String tableToJoin, String alias, String on);
    void buildFrom(String table, String alias);
    void buildFrom(String table);
    void buildMaxResults(int limit, int offset);
    void buildMaxResults(int limit);
    void buildOrderBy(String column, String order);
    void buildHaving(String having);
    void buildSet(String[] fields);
    void addAndWhere(String and) throws QueryBuilderException;
    void addOrWhere(String or) throws QueryBuilderException;
    void addJoin(JoinEnum type, String firstTableAlias, String tableToJoin, String alias, String on);
    void addSet(String[] fields);
    void resetQueryPart(String queryPart);
    //implemented
    void setParameters(String[] values);
    void addOrderBy(String column, String order);
    //implemented
    void addValues(String[] values)  throws QueryBuilderException;
    public PreparedStatement getResult() throws QueryBuilderException;
}
