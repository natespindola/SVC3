package app.model.helper;

import java.util.HashMap;
import java.util.Map;


public enum PeriodicityTypeEnum
{
    UNICO(1), DIARIO(2),SEMANAL(3),MENSAL(4),SEMESTRAL(5),ANUAL(6);

    private int id;

    PeriodicityTypeEnum(int type)
    {
        id = type;
    }
    public int getId()
    {
        return this.id;
    }
    
        private static final Map<Integer, PeriodicityTypeEnum> intToTypeMap = new HashMap<Integer, PeriodicityTypeEnum>();
    static {
    for (PeriodicityTypeEnum type : PeriodicityTypeEnum.values()) {
        intToTypeMap.put(type.id, type);
    }
}
    
    public static PeriodicityTypeEnum fromInt(int i) {
    PeriodicityTypeEnum type = intToTypeMap.get(Integer.valueOf(i));
    return type;
}
    
}
