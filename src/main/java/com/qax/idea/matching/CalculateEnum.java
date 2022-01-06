package com.qax.idea.matching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称: idea
 * 文件名称: com.qax.idea.matching
 * 描述: [功能描述]
 * 创建时间: 2022/1/6.
 * 公司信息: QiXin Group.Situation xx事业部
 *
 * @author tianyong@qixin.com
 * @version v2.0
 */
public enum CalculateEnum {

    NUM("num"),

    STR("str"),

    DATE("date");


    public final String typeName;

    CalculateEnum(String typeName){
        this.typeName = typeName;
    }

    private static final Map<String, CalculateEnum> enums = new HashMap<>();

    /**
     * 构造value， key
     */
    public static void enumToMap(){
        Arrays.stream(CalculateEnum.values()).forEach(w -> enums.put(w.typeName, w));
    }

    /**
     * 获取对应的 枚举 name
     * @param value 需要获取的枚举name，对应的value
     * @return CalculateEnum
     */
    public static CalculateEnum getEnumByName(String value){
        if(enums.size() < 1){
            enumToMap();
        }
        return enums.get(value);
    }

}
