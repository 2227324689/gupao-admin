package com.gupaoedu.live.gupaoedulive.core.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MsgUtils {

    private static Pattern MSG = Pattern.compile("(\\{([a-zA-Z]+)\\})");

    /**
     * 根据模板及参数获得短信内容
     * @return
     */
    public static String getContent(String tempalte, Map<String,String> parameters){

        Matcher m = MSG.matcher(tempalte);
        StringBuffer stringBuffer = new StringBuffer();
        while (m.find()){
            String key = m.group(2);
            String value = null;
            if(null != parameters && parameters.size() > 0){
                if (parameters.containsKey(key)){
                    value = parameters.get(key);
                }
                value = (value == null) ? "" : value;
            }else{
                value = "";
            }
            m.appendReplacement(stringBuffer,value);

        }
        m.appendTail(stringBuffer);
        return stringBuffer.toString();
    }


}
