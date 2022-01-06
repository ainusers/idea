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
public enum ConditionEnum {

    // !,&,|运算时暂时未考虑在内，主要用于判断字符是否为操作
    NOT("!",0),
    LT("<",1),
    ELT("<=",1),
    GT(">",1),
    EGT(">=",1),
    EQ("==",2),
    NEQ("!=",2),
    BAND("&", 3),
    BOR("|", 4),
    AND("&&",5),
    OR("||",6),
    CONTAIN("contain",7),
    WEAKPASSWORD("weakPassword",7),
    REGULAR("regular",7),
    REFERENCE("reference",7),
    SENSITIVEWORD("sensitiveWord",7);


    private final String name;

    ConditionEnum(String name, Integer priority){
        this.name = name;
    }

    private static final Map<String, ConditionEnum> enums = new HashMap<>();


    public static void enumToMap(){
        Arrays.stream(ConditionEnum.values()).forEach(w -> enums.put(w.name, w));
        enums.put("eq", ConditionEnum.EQ);
    }

    public static ConditionEnum getEnumByName(String name){
        if(enums.size() < 1){
            enumToMap();
        }
        return enums.get(name);
    }

}
