package app.model.helper;

import java.util.Map;
import java.util.HashMap;

public enum PriceTypeEnum
{
    ALUGUEL(1), VENDA(2);

    public int id;

    PriceTypeEnum(int type)
    {
        id = type;
    }
    public int getId()
    {
        return this.id;
    }
    
    private static final Map<Integer, PriceTypeEnum> intToTypeMap = new HashMap<Integer, PriceTypeEnum>();
    static {
    for (PriceTypeEnum type : PriceTypeEnum.values()) {
        intToTypeMap.put(type.id, type);
    }
}
    
    public static PriceTypeEnum fromInt(int i) {
    PriceTypeEnum type = intToTypeMap.get(Integer.valueOf(i));
    return type;
}
    
}
