package com.qax.idea.matching;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
public class CalculateRule {
    
    /**
      * @time: 2022/1/6 14:17
      * @Param: 
      * @return: 
      * @Description: 测试类
      */
    public static void main (String args[]) {
        JSONObject jsonObject = FileUtil.readJsonFile("rule/web.json");
        JSONObject rules = (JSONObject) jsonObject.get("rule");

        JSONObject data = new JSONObject();
        data.put("status", "200");
        data.put("data", "name=13917072920&pass=600123");
        data.put("username", "13917072920");
        data.put("password", "600123");

        // 核心比较
        boolean judge = parseRule(rules, data);
        System.out.println(judge);
    }

    /**
      * @time: 2022/1/6 10:47
      * @Param:
      * @return:
      * @Description: 解析规则入口
      */
    public static boolean parseRule(JSONObject rule, JSONObject data){
        String operator = rule.get("operator").toString();
        JSONArray rules=(JSONArray) rule.get("children");
        switch (operator) {
            case "and":
                return andOperation(rules,data);
            case "or":
                return orOperation(rules,data);
            case "not":
                return  notOperation(rules,data);
            default:
                return defaultOperation(rule,data);
        }
    }

    public static Boolean andOperation(JSONArray rules, JSONObject data) {
        if (rules.size() > 0) {
            for (Object obj : rules) {
                JSONObject jsonObject1 = (JSONObject) obj;
                if(!parseRule(jsonObject1, data)){
                    return false;
                }
            }
        }
        return true;
    }

    public static Boolean orOperation(JSONArray rules, JSONObject data) {
        if (rules.size() > 0) {
            for (Object obj : rules) {
                JSONObject jsonObject1 = (JSONObject) obj;
                if(parseRule(jsonObject1, data)){
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean notOperation(JSONArray rules, JSONObject data) {
        if (!rules.isEmpty()) {
            for (Object obj : rules) {
                JSONObject jsonObject1 = (JSONObject) obj;
                if(!parseRule(jsonObject1, data)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
      * @time: 2022/1/6 10:49
      * @Param: 
      * @return: 
      * @Description: 核心处理逻辑 (叶子节点)
      */
    public static Boolean defaultOperation(JSONObject rule, JSONObject data) {
        String field_type = rule.get("field_type").toString();
        String op = rule.get("operator").toString();
        String rule_value = rule.get("value").toString();
        String key = rule.get("key").toString();
        String opType=rule.getString("type");
        if (data.containsKey(key)) {
            if(opType!=null&&opType.equals(ConditionEnum.REFERENCE.name())){
                rule_value = data.get(rule_value).toString();
            }
            String data_value = data.get(key).toString();
            return judgeType(CalculateEnum.getEnumByName(field_type), rule_value, data_value, op);
        }
        return false;
    }

    /**
     * 判断基本条件系节点
     * @param type 条件节点参与判断的字段类型： str， num， date
     * @param rule_value 规则数据
     * @param data_value 流量数据
     * @param op 判断符号； > != <
     * @return 判断结果，true， false
     */
    public static boolean judgeType(CalculateEnum type, String rule_value, String data_value, String op ){
        switch (type){
            case STR:
                return CalculateUtils.strCalculate(data_value, rule_value, ConditionEnum.getEnumByName(op));

            case NUM:
                return CalculateUtils.numCalculate(data_value, rule_value, ConditionEnum.getEnumByName(op));

            case DATE:
                return CalculateUtils.dateCalculate(data_value, rule_value, ConditionEnum.getEnumByName(op));

            default:
                return true;
        }
    }

}
