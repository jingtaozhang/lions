package com.test;

import org.apache.log4j.Logger;
import org.junit.Test;

public class JTest {
	private static final Logger logger = Logger.getLogger(JTest.class);
    
	
	/**
	 * 测试解析error log日志
	 */
	//@Test
	public void parseErrorLog() {
		String log = "[start]state=error,uuid=8fe399201b6f42bd8fc691bd963ab74f,target=com.baiwang.baiwangcloud.admin.facade.impl.PortalYgFacadeImpl@533bf759,operation=getAllYhInfo[com.baiwang.baiwangcloud.common.model.RequestBean@77404f23, {\"yhQd\":1,\"yhQds\":[1],\"qdZhbms\":[],\"start\":0,\"size\":10}],message=[end]";
	//	DataParseUtil.parseLog(log);
	}
	
	/**
	 * 测试解析start log日志
	 */
	//@Test
	public void parseStartLog() {
		String log = "[start]state=start,uuid=8fe399201b6f42bd8fc691bd963ab74f,target=com.baiwang.baiwangcloud.admin.facade.impl.PortalYgFacadeImpl@533bf759,operation=getAllYhInfo[com.baiwang.baiwangcloud.common.model.RequestBean@77404f23, {\"yhQd\":1,\"yhQds\":[1],\"qdZhbms\":[],\"start\":0,\"size\":10}],message=[end]";
		//DataParseUtil.parseLog(log);
	}
	
	/**
	 * 测试解析end log日志
	 */
	@Test
	public void parseEndLog() {
		logger.info("begin");
		try {
			String log = "[start]state=end,uuid=8fe399201b6f42bd8fc691bd963ab74f,target=com.baiwang.baiwangcloud.admin.facade.impl.PortalYgFacadeImpl@533bf759,operation=getAllYhInfo[com.baiwang.baiwangcloud.common.model.RequestBean@77404f23, {\"yhQd\":1,\"yhQds\":[1],\"qdZhbms\":[],\"start\":0,\"size\":10}],message=[end]";
			//DataParseUtil.parseLog(log);
		} catch (Exception e) {
			logger.error("测试end日志出现异常：",e);
		}
		logger.info("end");
	}
}
