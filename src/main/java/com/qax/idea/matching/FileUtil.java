package com.qax.idea.matching;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
@Slf4j
public class FileUtil {

    public static final String FILE_PATH="dict/";

    private static Map<String,Set<String>> weakPassword = null;

    /**
      * @time: 2022/1/6 14:11
      * @Param: 
      * @return: 
      * @Description: 初始化弱密码到内存
      */
    public static void init(String filename){
        if(weakPassword==null){
            weakPassword=new HashMap<>();
        }
        log.info("加载配置文件："+FILE_PATH+filename);
        weakPassword.put(filename, FileUtil.getTextContentAsSet(FILE_PATH+filename));
    }

    /**
      * @time: 2022/1/6 14:14
      * @Param: 
      * @return: 
      * @Description: 判断是否为弱密码入口
      */
    public static Boolean isContain(String filename,String target){
        if(weakPassword==null||weakPassword.get(filename)==null){
            init(filename);
        }
        return weakPassword.get(filename).contains(target);
    }

    /**
      * @time: 2022/1/6 14:15
      * @Param: 
      * @return: 
      * @Description: 获取文本存入set集合
      */
    public static Set<String> getTextContentAsSet(String fileName){
        Set<String> set=new HashSet();
        try {
            InputStream resourceAsStream = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
            String buf="";
            while ((buf=bufferedReader.readLine())!=null){
                set.add(buf.trim());
            }
            resourceAsStream.close();
            return set;
        } catch (IOException e) {
            log.error(e.toString());
            return null;
        }
    }

    /**
      * @time: 2022/1/6 14:18
      * @Param:
      * @return:
      * @Description: 读取json存入对象
      */
    public static JSONObject readJsonFile(String fileName) {
        JSONObject jsonStr ;
        try {
            InputStream resourceAsStream = CalculateRule.class.getClassLoader().getResourceAsStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
            StringBuilder stringBuffer = new StringBuilder();
            String buf="";
            while ((buf=bufferedReader.readLine())!=null){
                stringBuffer.append(buf);
            }
            resourceAsStream.close();
            jsonStr = (JSONObject) JSON.parse(stringBuffer.toString());
            return jsonStr;
        } catch (IOException e) {
            log.error(e.toString());
            return null;
        }
    }

}
