/*
 * 创建日期 2010-7-16
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package open.stakeout.tcp;

import java.util.HashMap;
import java.util.Map;
import open.system.monitor.Monitor;
import open.system.monitor.MonitorInfo;

/**
 * 将监控信息保存到日志文件
 * @author pengminghua
 *
 */
public class AliveSave extends AbstractSave{

	public AliveSave(String name) {
		super(name);
	}
	
	@Override
	public void init() {
		new Thread(checkDB).start();
	}
	
	class CheckDB implements Runnable{
		private boolean flag = true;
		/**
		 * run
		 */
		public void run() {
			while(flag){
				try {
					long t = System.currentTimeMillis();
//					new PersistenceDelegate(null)
//						.executeSQL(PersistenceDelegate.SELECT, "select 1");
					dbDelay = System.currentTimeMillis() - t;
					dbFlag = 0;
					info = Monitor.getMonitorInfo();
				} catch (Exception e) {
					dbFlag = 1;
					dbDelay = 0;
					info = null;
					e.printStackTrace();
				}
				timeDelay();
			}
		}
		/**
		 * stop
		 */
		public void stop(){
			flag = false;
			timeDelay();
		}
	}
	
	private int dbFlag = 0;
	private long dbDelay = 0;
	private long call = 0;
	private MonitorInfo info = null;
	private CheckDB checkDB = new CheckDB();
	/**
	 * 统计这段时间的交易总数
	 */
	public long getCallCount() {
		
		long call = 0;
		long rtn = call - this.call;
		this.call = call;
		return rtn;
	}
	
	public Map<String,Object> getData() throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("call", getCallCount());
		data.put("db", dbFlag);
		data.put("dbDly", dbDelay);
		if(info!=null){
			data.put("tMem", info.getTotalMemory());
			data.put("uMem", info.getTotalMemory()-info.getFreeMemory());
			data.put("mMem", info.getMaxMemory());
			data.put("cpu", info.getCpuRatio());
			data.put("trd", info.getActiveThread());
		}
		return data;
	}
}
