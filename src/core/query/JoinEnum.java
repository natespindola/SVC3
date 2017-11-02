package core.query;

public enum JoinEnum
{
    inner("INNER JOIN"), outer("OUTER JOIN"), left("LEFT JOIN"), right("RIGHT JOIN");

    private String type;

    JoinEnum(String type)
    {
        this.type = type;
    }

    public String getJoin()
    {
        return this.type;
    }

}
