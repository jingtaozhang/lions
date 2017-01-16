package com.lions.liongrovegarden.utils;
import com.alibaba.fastjson.JSONObject;


/**
 * zjt 2016-11-10
 */
public class ResultBuilder {

    /**
     *  构造返回json结果
     * @param Status
     * @param Message
     * @return
     */
    public static String getResult(String status , String Message ){
        return "{\"status\":\"" + status + "\"" +  ",\"message\":\"" + Message + "\"}";
    }

    /**
     * 构建返回json对象
     * @param state
     * @param data
     * @param message
     * @return JSONObject
     */
    public static JSONObject getJsonResult(String status ,Object data,String message){
    	JSONObject json = new JSONObject();
    	json.put("status", status);
    	json.put("message", message);
    	json.put("data", data);
        return json;
    }
}
