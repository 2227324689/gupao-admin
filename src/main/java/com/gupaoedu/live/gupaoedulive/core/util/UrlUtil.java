package com.gupaoedu.live.gupaoedulive.core.util;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * url 相关工具类
 * @author link 1119132339@qq.com
 */
public class UrlUtil {

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     * @param strUrlParam  去除路径后的纯参数部分
     * @return  url请求参数部分
     */
    public static Map<String, String> urlSplit(String strUrlParam){
        Map<String, String> mapRequest = new HashMap<>();
        String[] arrSplit=null;
        if(strUrlParam==null){
            return mapRequest;
        }
        arrSplit=strUrlParam.trim().split("[&]");
        for(String strSplit:arrSplit){
            String[] arrSplitEqual=null;
            arrSplitEqual= strSplit.split("[=]");
            //解析出键值
            if(arrSplitEqual.length>1){
                //正确解析
                mapRequest.put(arrSplitEqual[0].trim(),arrSplitEqual[1]);
            }else{
                if(arrSplitEqual[0]!=""){
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0].trim(),"");
                }
            }
        }
        return mapRequest;
    }



    /**
     * 判断是否是微信访问
     * @param request
     * @return
     */
    public static boolean isWeChat(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        return userAgent == null || userAgent.indexOf("micromessenger") == -1 ? false : true;
    }



}
