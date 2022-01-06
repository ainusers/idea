package com.qax.idea.matching;

import java.util.regex.Pattern;

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
public class CalculateUtils {

    public static boolean numCalculate(String  o1, String o2, ConditionEnum operate){
        long operand1 = Long.parseLong(o1);
        long operand2 = Long.parseLong(o2);
        switch (operate){
            case LT:
                return operand1 < operand2;
            case ELT:
                return operand1 <= operand2;
            case GT:
                return operand1 > operand2;
            case EGT:
                return operand1 >= operand2;
            case EQ:
                return operand1 == operand2;
            case NEQ:
                return operand1 != operand2;
            default:
                return true;
        }
    }

    public static boolean strCalculate(String data, String filter, ConditionEnum operate){
        switch (operate){
            case EQ:
                return data.equals(filter);
            case NEQ:
                return !data.equals(filter);
            case AND:
                return "true".equals(data) && "true".equals(filter);
            case OR:
                return "true".equals(filter) || "true".equals(data);
            case CONTAIN:
                return data.contains(filter);
            case REGULAR:
                return Pattern.matches(filter, data);
            case WEAKPASSWORD:
                return FileUtil.isContain(filter,data);
            default:
                return true;
        }
    }

    public static boolean dateCalculate(String operand1, String operand2, ConditionEnum operate){
        switch (operate){
            case LT:
                return compareDate(operand1, operand2) == -1 ? true : false;
            case ELT:
                return compareDate(operand1, operand2) <= 0 ? true : false;
            case GT:
                return compareDate(operand1, operand2) == 1 ? true : false;
            case EGT:
                return compareDate(operand1, operand2) >= 0 ? true : false;
            case EQ:
                return compareDate(operand1, operand2) == 0 ? true : false;
            case NEQ:
                return compareDate(operand1, operand2) != 0 ? true : false;
            default:
                return true;
        }
    }

    /**
     * 比较日期大小
     */
    public static int compareDate(String date1, String date2){
        String d1 = date1.trim().replaceAll("-|:|/| ","");
        String d2 = date2.trim().replaceAll("-|:|/| ", "");
        StringBuilder sb1 = new StringBuilder(d1);
        StringBuilder sb2 = new StringBuilder(d2);
        while(sb1.length() < 14){
            sb1.append("0");
        }
        while(sb2.length() < 14){
            sb2.append("0");
        }
        long num1 = Long.parseLong(sb1.toString());
        long num2 = Long.parseLong(sb2.toString());
        return num1 == num2 ? 0 : num1 > num2 ? 1 : -1;
    }

}
