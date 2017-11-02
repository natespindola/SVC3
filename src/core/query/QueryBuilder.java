package core.query;

import java.sql.PreparedStatement;
import core.ConnectionFactory;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class QueryBuilder implements QueryBuilderInterface
{
    private Query query;
    private PreparedStatement pstm;
    private int countValues;
    private Connection connection;

    public QueryBuilder()
    {
        query = new Query();
        countValues = 0;
        connection = null;
    }

    public Connection getConnection()
    {
        return this.connection;
    }

    @Override
    public void buildSelect()
    {
        this.query.setSelect("SELECT * ");
    }

    @Override
    public void buildSelect(String[] fields)
    {
        String sqlStatement = "Select ";
        for (int i =0; i<fields.length; i++) {
            sqlStatement += fields[i];
            if(i != (fields.length - 1));
                sqlStatement += " , ";
        }
            this.query.setSelect(sqlStatement);
    }

    @Override
    public void buildInsert(String into, String[] fields)
    {
        String insertStatement = "( ";
        int commaCount = 1;
        for (String field:
             fields) {
            insertStatement += field;
            if(fields.length != commaCount) {
                insertStatement += ", ";
                commaCount++;
            }
        }
        insertStatement += " )";
        this.countValues = fields.length;
        this.query.setInsert("INSERT INTO "+into+insertStatement);
    }

    @Override
    public void buildUpdate(String table)
    {
        this.query.setUpdate("UPDATE "+ table);
    }

    @Override
    public void buildWhere(String where)
    {
        String sqlStatement = "WHERE ("+ where+ " )";
        this.query.setWhere(sqlStatement);
    }

    @Override
    public void buildJoin(JoinEnum type, String firstTableAlias, String tableToJoin, String alias, String on)
    {
        if(this.query.getJoin() == null){
            String sqlStatement = type.getJoin() +" "+ tableToJoin+ "as "+alias + " "+ on;
            this.query.setJoin(sqlStatement);
        }else {
            try {
                Query query = (Query) this.query.clone();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String sqlStatement = type.getJoin() +" "+ tableToJoin+ "as "+alias + " "+ on;
            sqlStatement = query.getJoin() + sqlStatement;
            this.query.setJoin(sqlStatement);
        }
    }

    @Override
    public void buildMaxResults(int limit, int offset)
    {
        this.query.setLimit("LIMIT " + limit + "OFFSET "+ offset);
    }

    @Override
    public void buildMaxResults(int limit)
    {
        this.query.setLimit("LIMIT " + limit);
    }

    @Override
    public void buildOrderBy(String column, String order)
    {
        this.query.setOrderBy("ORDER BY "+ column + order);
    }

    @Override
    public void buildFrom(String table, String alias)
    {
        this.query.setFrom("FROM "+ table + " as "+ alias);
    }

    @Override
    public void buildFrom(String table)
    {
        this.query.setFrom("FROM "+ table);
    }

    @Override
    public void buildHaving(String having)
    {
        this.query.setHaving("HAVING "+ having);
    }

    @Override
    public void buildSet(String[] fields)
    {
        String sqlStatement = " SET ";
        int commaCount = 1;
        for (String field:
             fields) {
            sqlStatement += field + " = ? ";
            if(commaCount != fields.length){
                sqlStatement += " ,";
                commaCount++;
            }
        }
        try {
            if(this.query.getSet() != null) {
                Query query = (Query) this.query.clone();
                this.query.setSet(query.getSet() + sqlStatement);
            }else
            {
                this.query.setSet(sqlStatement);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addSelect(String[] select)
    {
        if (this.query.getSelect() == null) {
            this.buildSelect(select);
        } else {
            String sqlStatement = "";
            try {
                Query query = (Query) this.query.clone();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i =0; i<select.length; i++) {
                sqlStatement += select[i];
                if(i != (select.length - 1));
                sqlStatement += " , ";
            }
            query.setSelect(this.query.getSelect() + " ," + select);
            this.query.setSelect(query.getSelect());
        }
    }
    @Override
    public void addOrWhere(String or) throws QueryBuilderException
    {
        if(this.query.getWhere() == null){
            this.buildWhere(or);
        }else {
            try {
                Query query = (Query) this.query.clone();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String sqlStatement = query.getWhere() + " OR (" + or +")";
            this.query.setWhere(sqlStatement);
        }

    }

    @Override
    public void addAndWhere(String and) throws QueryBuilderException
    {
        if(this.query.getWhere() == null){
            this.buildWhere(and);
        }else {
            try {
                Query query = (Query) this.query.clone();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String sqlStatement = query.getWhere() + " AND (" + and +")";
            this.query.setWhere(sqlStatement);
        }
    }

    @Override
    public void addJoin(JoinEnum type, String firstTableAlias, String tableToJoin, String alias, String on)
    {
        if(this.query.getJoin() == null){
            this.buildJoin(type,firstTableAlias,tableToJoin,alias,on);
        }else {
            try {
                Query query = (Query) this.query.clone();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String sqlStatement = " "+type.getJoin() +" "+ tableToJoin+ "as "+alias + " "+ on;
            sqlStatement = query.getJoin() + sqlStatement;
            this.query.setJoin(sqlStatement);
        }
    }

    @Override
    public void addSet(String[] fields)
    {
        if(this.query == null){
            this.buildSet(fields);
        }else {
            String sqlStatement = "";
            for (String field :
                    fields) {
                sqlStatement += "SET " + " = ?";
            }
            try {
                Query query = (Query) this.query.clone();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.query.setSet(query.getSet() + sqlStatement);
        }
    }
    private boolean isTimeStampValid(String inputString)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        try{
            format.parse(inputString);
            return true;
        }catch(ParseException e) {
            return false;
        }
    }

    @Override
    public void setParameters(String[] parameters) {
     /*   int i = 1;
        for (String parameter :
                parameters) {
            if (NumberUtils.isNumber(parameter)) {
                try {
                    pstm.setLong(i, Long.parseLong(parameter));
                    i++;
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }else if(this.isTimeStampValid(parameter)) {
                try{
                    pstm.setTimestamp(i,Timestamp.valueOf(parameter));
                    i++;
                }catch(SQLException e) {
                    e.printStackTrace();
                }
            }else{
                try{
                    pstm.setString(i,parameter);
                    i++;
                }catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

    @Override
    public void addOrderBy(String column, String order)
    {

    }

    @Override
    public void resetQueryPart(String queryPart)
    {
        if(queryPart.equals("select")){
            this.query.setSelect(null);
        }
        if(queryPart.equals("update")){
            this.query.setUpdate(null);
            this.query.setSet(null);
        }
        if(queryPart.equals("insert")){
            this.query.setInsert(null);
        }
        if(queryPart.equals("join")){
            this.query.setJoin(null);
        }
        if(queryPart.equals("where")){
            this.query.setWhere(null);
        }
        if(queryPart.equals("having")) {
        this.query.setHaving(null);
        }

    }

    @Override
    public void addValues(String[] values) throws QueryBuilderException
    {
        int commaCount = 1;
        String sqlStatement = "VALUES (";
        for (String value:
             values) {
            sqlStatement += "?";
            if(commaCount != values.length) {
                sqlStatement += ", ";
                commaCount++;
            }
        }
        sqlStatement += ")";
        this.query.setValues(sqlStatement);
        if(countValues == 0 )
        {
            countValues = values.length;
        }else{
            if(values.length != countValues) {
                throw new QueryBuilderException("Values and fields aren't equals");
            }
        }
    }
    @Override
    public PreparedStatement getResult() throws QueryBuilderException
    {
        if (connection == null){
            connection = new ConnectionFactory().getConnection();
        }
        if(this.query.getSelect() != null){
            if((this.query.getInsert() != null) || (this.query.getUpdate() != null))
            {
                throw new QueryBuilderException("Insert and Update set in a Select statement");
            }
            try {
                pstm = connection.prepareStatement(this.getSelect());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }else if(this.query.getUpdate() != null){
            if((this.query.getSelect() != null) || (this.query.getInsert() != null)){
                throw new QueryBuilderException("Select and Insert set in a Update statement");
            }
            try {
                pstm = connection.prepareStatement(this.getUpdate());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }else if(this.query.getInsert() != null){
            if((this.query.getSelect() != null) || (this.query.getUpdate() != null)){
                throw new QueryBuilderException("Select and Update set in a Insert statement");
            }
            try {
                pstm = connection.prepareStatement(this.getInsert());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }else{
            throw new QueryBuilderException("Error in the statement");
        }

        return pstm;
    }

    public String getSql() throws QueryBuilderException
    {
        String statement = null;
        if(this.query.getSelect() != null){
            if((this.query.getInsert() != null) || (this.query.getUpdate() != null))
            {
                throw new QueryBuilderException("Insert and Update set in a Select statement");
            }
            statement = this.getSelect();
        }else if(this.query.getUpdate() != null){
            if((this.query.getSelect() != null) || (this.query.getInsert() != null)){
                throw new QueryBuilderException("Select and Insert set in a Update statement");
            }
            statement = this.getUpdate();
        }else if(this.query.getInsert() != null){
            if((this.query.getSelect() != null) || (this.query.getUpdate() != null)){
                throw new QueryBuilderException("Select and Update set in a Insert statement");
            }
            statement = this.getInsert();
        }else{
            statement = null;
            throw new QueryBuilderException("Error in the statement");
        }

        return statement;
    }

    private String getUpdate() throws QueryBuilderException{
        if(this.query.getUpdate() == null || this.query.getSet() == null){
            throw new QueryBuilderException("Update or set empty in a update query");
        }
        String sqlStatement = this.query.getUpdate();
        sqlStatement += this.addQueryPart(this.query.getJoin());
        sqlStatement += this.query.getSet();
        sqlStatement += this.addQueryPart(this.query.getWhere());
        sqlStatement += this.addQueryPart(this.query.getHaving());

        return sqlStatement;

    }

    private String getInsert() throws QueryBuilderException{
        if(this.query.getInsert() == null || this.query.getValues() == null){
            throw new QueryBuilderException("Values or Insert empty in a insert query");
        }
        String sqlStatement = this.query.getInsert();
        sqlStatement += this.query.getValues();

        return sqlStatement;

    }
    private String getSelect() throws QueryBuilderException
    {
        if(this.query.getSelect() == null || this.query.getFrom() == null){
            throw new QueryBuilderException("Select or from empty in a select query");
        }
        String sqlStatement = this.query.getSelect();
        sqlStatement += this.query.getFrom();
        sqlStatement += this.addQueryPart(this.query.getJoin());
        sqlStatement += this.addQueryPart(this.query.getWhere());
        sqlStatement += this.addQueryPart(this.query.getHaving());
        sqlStatement += this.addQueryPart(this.query.getOrderBy());
        sqlStatement += this.addQueryPart(this.query.getLimit());

        return sqlStatement;
    }

    private String addQueryPart(String str)
    {
        if(shouldInclude(str)){
            return str;
        }else{
            return "";
        }
    }

    private boolean shouldInclude(String str)
    {
        if(str != null)
        {
            return true;
        }else
        {
            return false;
        }
    }
}
