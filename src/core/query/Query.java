package core.query;



import java.lang.String;
import java.util.LinkedList;
import java.util.Queue;

class Query extends QueryPrototype
{
    private String select;
    private String from;
    private String insert;
    private String update;
    private String where;
    private String orWhere;
    private String andWhere;
    private String join;
    private String limit;
    private String orderBy;
    private String having;
    private String set;
    private String values;
    private String groupBy;

    public Query()
    {
        this.select = null;
        this.from = null;
        this.insert = null;
        this.update = null;
        this.where = null;
        this.orWhere = null;
        this.andWhere = null;
        this.join = null;
        this.limit = null;
        this.orderBy = null;
        this.having = null;
        this.set = null;
        this.values = null;
        this.groupBy = null;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getSelect() {
        return select;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getHaving()
    {
        return having;
    }

    public void setHaving(String having)
    {
        this.having = having;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getInsert() {
        return insert;
    }

    public void setInsert(String insert) {
        this.insert = insert;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getOrWhere() {
        return orWhere;
    }

    public void setOrWhere(String orWhere) {
        this.orWhere = orWhere;
    }

    public String getAndWhere() {
        return andWhere;
    }

    public void setAndWhere(String andWhere) {
        this.andWhere = andWhere;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        String sql = "";
        switch (this.queryType())
        {
            case 1:
                sql += this.select;
                sql += this.from;
                break;
            case 2:
                sql += this.insert;
                break;
            case 3:
                sql += this.update;
                break;
            default :
                break;
        }
        if(this.join != null)
            sql += this.join;
        if(this.where != null)
            sql += this.where;
        if(this.andWhere != null)
            sql += this.andWhere;
        if(this.orWhere != null)
            sql += this.orWhere;
        if(this.having != null)
            sql += this.having;
        if(this.limit != null)
            sql += this.limit;
        if(this.orderBy != null)
            sql += this.orderBy;

        return sql;
    }

    public QueryPrototype clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private int queryType()
    {
        if(this.select == null){
            return 1;
        }
        if(this.insert == null){
            return 2;
        }
        if(this.update == null){
            return 3;
        }
        return 0;
    }
}
