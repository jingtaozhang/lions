package com.lions.liongrovegarden.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 接收日志文件工具类
 * @author zjt 2016-11-4
 *
 */
public class ReceiveLogFileUtil {
	
	private static final Logger logger = Logger.getLogger(DataParseUtil.class);
	
	//日志文件内容map key为服务器或服务名加上日志名称，value为实时接收的日志内容
	public static final Map<String,String> logFileMap = new HashMap<String,String>();
    
	//静态日志文件内容map key为服务器或服务名加上日志名称，value为实时接收的日志内容
	public static final Map<String,String> staticLogFileMap = new HashMap<String,String>();
	
	//日志文件列表map key为用户名_服务器或服务名，value为列表string
	public static final Map<String,String> logFileListMap = new HashMap<String,String>();
    /**
     * 解析日志 适用于日志文件
     * @param log 
     */
    public static void parseLogForLogFile(String log){
		try {
			logger.info("解析日志文件："+log);
			if(null==log||log.length()==0){
				return;
			}
			//获取服务器名
			String serverName = log.substring(0, log.indexOf(":"));
			String logContent = log.substring(log.indexOf(":")+1,log.length());
			if(logContent.trim().length()==0){
				return;
			}
	        logFileMap.put(serverName, logContent);
		} catch (Exception e) {
			logger.error("解析日志文件出现异常，日志内容为："+log,e);
		}
    }
   
    /**
     * 解析日志 适用于静态日志文件
     * @param log 
     */
    public static void parseLogForStaticLogFile(String log){
		try {
			logger.info("解析静态日志文件："+log);
			if(null==log||log.length()==0){
				return;
			}
			//获取服务器名
			String serverName = log.substring(0, log.indexOf("::"));
			String logContent = log.substring(log.indexOf("::")+2,log.length());
			if(logContent.trim().length()==0){
				return;
			}
			staticLogFileMap.put(serverName, logContent);
		} catch (Exception e) {
			logger.error("解析静态日志文件出现异常，日志内容为："+log,e);
		}
    }
    
    /**
     * 解析日志 适用于解析日志文件列表
     * @param log 
     */
    public static void parseLogForLogFileList(String log){
		try {
			logger.info("解析日志列表文件："+log);
			if(null==log||log.length()==0){
				return;
			}
			//获取服务器名
			String serverName = log.substring(0, log.indexOf(":"));
			String logContent = log.substring(log.indexOf(":")+1,log.length());
			if(logContent.trim().length()==0){
				return;
			}
			logFileListMap.put(serverName, logContent);
		} catch (Exception e) {
			logger.error("解析日志文件列表出现异常，日志内容为："+log,e);
		}
    }
}
