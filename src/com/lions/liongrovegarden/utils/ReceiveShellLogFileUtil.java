package com.lions.liongrovegarden.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 接收shell日志文件工具类
 * @author zjt 2016-12-23
 *
 */
public class ReceiveShellLogFileUtil {
	
	private static final Logger logger = Logger.getLogger(DataParseUtil.class);
	
	//日志文件内容map key为服务器或服务名加上日志名称，value为实时接收的日志内容
	public static final Map<String,String> grepMap = new HashMap<String,String>();
    
    /**
     * 解析grep命令返回的数据
     * @param log 
     */
    public static void parseLogForGrep(String log){
		try {
			logger.info("解析日志文件："+log);
			if(null==log||log.length()==0){
				return;
			}
			//获取服务器名
			String key = log.substring(0, log.indexOf("::"));
			String logContent = log.substring(log.indexOf("::")+2,log.length());
			if(logContent.trim().length()==0){
				return;
			}
			grepMap.put(key, logContent);
		} catch (Exception e) {
			logger.error("解析日志文件出现异常，日志内容为："+log,e);
		}
    }
   
  
}
