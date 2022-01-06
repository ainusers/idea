package com.qax.idea.check;

import java.util.*;

/**
  * @author: tianyong
  * @time: 2021/9/8 11:30
  * @description: 前缀树算法/DFA算法: http://blog.csdn.net/chenssy/article/details/26961957 实现的敏感词检测
  * @Version: v1.0
  * @company: QiXin Group.Situation xx事业部
  */
public class SensitiveWordCheck {

    public static final int MINMATCHTYPE = 1;      //最小匹配规则
    public static final int MAXMATCHTYPE = 2;      //最大匹配规则


    /**
      * @author: tianyong
      * @time: 2021/9/8 11:31
      * @description: 检查文字中是否包含敏感字符
      * @Version: v1.0
      * @company: QiXin Group.Situation xx事业部
      */
    @SuppressWarnings({ "rawtypes"})
    public static int checkBadWord(Map<String,String> wordMap,String txt,int beginIndex,int matchType){
        boolean  flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
        int matchFlag = 0;     //匹配标识数默认为0
        char word = 0;
        Map nowMap = wordMap;
        for(int i = beginIndex; i < txt.length() ; i++){
            word = txt.charAt(i);
            nowMap = (Map) nowMap.get(word);     //获取指定key
            if(nowMap != null){     //存在，则判断是否为最后一个
                matchFlag++;     //找到相应key，匹配标识+1
                if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
                    flag = true;       //结束标志位为true
                    if(matchType == MINMATCHTYPE){    //最小规则，直接返回,最大规则还需继续查找
                        break;
                    }
                }
            }
            else{
                break;
            }
        }
        if(!flag){
            matchFlag = 0;
        }
        return matchFlag;
    }


    public static boolean isContaintBadWord(Map<String,String> wordMap,String txt){
        return isContaintBadWord(wordMap,txt,MINMATCHTYPE);
    }


    /**
      * @author: tianyong
      * @time: 2021/9/8 11:32
      * @description: 判断文字是否包含敏感字符 (匹配规则 1：最小匹配规则，2：最大匹配规则)
      * @Version: v1.0
      * @company: QiXin Group.Situation xx事业部
      */
    public static boolean isContaintBadWord(Map<String,String> wordMap,String txt,int matchType){
        boolean flag = false;
        for(int i = 0 ; i < txt.length() ; i++){
            int matchFlag = checkBadWord(wordMap, txt, i, matchType); //判断是否包含敏感字符
            if(matchFlag > 0){    //大于0存在，返回true
                flag = true;
            }
        }
        return flag;
    }


    /**
      * @author: tianyong
      * @time: 2021/9/8 11:32
      * @description: 替换敏感字字符 （替换字符，默认*）
      * @Version: v1.0
      * @company: QiXin Group.Situation xx事业部
      */
    public static String replaceBadWord(Map<String,String> wordMap, String txt,int matchType,String replaceChar){
        String resultTxt = txt;
        Set<String> set = getBadWord(wordMap, txt, matchType);     //获取所有的敏感词
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }
        return resultTxt;
    }


    /**
      * @author: tianyong
      * @time: 2021/9/8 11:32
      * @description: 获取文字中的敏感词 (1：最小匹配规则，2：最大匹配规则)
      * @Version: v1.0
      * @company: QiXin Group.Situation xx事业部
      */
    public static Set<String> getBadWord(Map<String,String> wordMap,String txt){
        return getBadWord(wordMap,txt,MINMATCHTYPE);
    }

    public static Set<String> getBadWord(Map<String,String> wordMap,String txt , int matchType){
        Set<String> sensitiveWordList = new HashSet<String>();
        for(int i = 0 ; i < txt.length() ; i++){
            int length = checkBadWord(wordMap, txt, i, matchType);    //判断是否包含敏感字符
            if(length > 0){    //存在,加入list中
                sensitiveWordList.add(txt.substring(i, i+length));
                i = i + length - 1;    // 减1的原因，是因为for会自增
            }
        }
        return sensitiveWordList;
    }


    /**
      * @author: tianyong
      * @time: 2021/9/8 11:33
      * @description: 获取替换字符串
      * @Version: v1.0
      * @company: QiXin Group.Situation xx事业部
      */
    private static String getReplaceChars(String replaceChar,int length){
        String resultReplace = replaceChar;
        for(int i = 1 ; i < length ; i++){
            resultReplace += replaceChar;
        }
        return resultReplace;
    }


    /**
      * @author: tianyong
      * @time: 2021/9/8 11:33
      * @description: 将我们的敏感词库构建成了一个类似与一颗一颗的树，这样我们判断一个词是否为敏感词时就大大减少了检索的匹配范围
      * @Version: v1.0
      * @company: QiXin Group.Situation xx事业部
      */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map<String,String> makeWordToHashMapTree(Set<String> keyWordSet) {
        Map<String,String> wordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while(iterator.hasNext()){
            key = iterator.next();    //关键字
            nowMap = wordMap;
            for(int i = 0 ; i < key.length() ; i++){
                char keyChar = key.charAt(i);       //转换成char型
                Object map = nowMap.get(keyChar);       //获取
                if(map != null){        //如果存在该key，直接赋值
                    nowMap = (Map) map;
                }else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String,String>();
                    newWorMap.put("isEnd", "0");     //不是最后一个
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }
                if(i == key.length() - 1){
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
        return wordMap;
    }


    public static void main(String[] args) {
        String string = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
        String uri = "/wdinfox.aphp";
        System.out.println("待检测语句字数：" + string.length());

        // 将敏感词构成HashMapTree
        List<String> wordList = List.of("shell.asp", "shell.aspx", "shell.jsp", "shell.php", "x.php", "x.asp", "x.jsp","shell.asp", "shell.aspx", "shell.jsp", "shell.php", "x.php", "x.asp", "x.jsp");
        Map wordMap = makeWordToHashMapTree(new HashSet(wordList));
        System.out.println(wordMap);

        // 获取文字中的敏感词
        Set<String> badWordset = getBadWord(wordMap, uri);
        System.out.println(badWordset);

        // 判断是否包含
        // System.out.println(isContaintBadWord(wordMap,uri));
    }
}
