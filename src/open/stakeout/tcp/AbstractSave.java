/*
 * 创建日期 2010-7-16
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package open.stakeout.tcp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;

import open.tools.convert.DataFormat;
import open.unreal.service.ServiceException;
import org.apache.log4j.Logger;

public abstract class AbstractSave implements Runnable{
	
	private boolean stop = false;
	private int time = 1000;
	public static final String START = "[start]";
	public static final String END = "[end]";
	private String name = null;
	/**
	 * 获取数据
	 */
	public abstract Map<String,Object> getData() throws Exception;
	
	/**
	 * 初始化方法
	 */
	public abstract void init();
	
	/**
	 * 设置保存延迟的间隔时间 
	 * @param time
	 */
	public void setTimeDelay(int time){
		this.time = time;
	}
	
	/**
	 * 结束
	 */
	public void stop(){
		stop = true;
		timeDelay();
	}
	
	public AbstractSave(String name) {
		if(name==null){
			try {
				this.name = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
			}
		}else{
			this.name = name;
		}
	}
	
	/**
	 * 延迟
	 */
	protected void timeDelay(){
		try {
			long t = time - System.currentTimeMillis()%time;
			if(t>0){
				Thread.sleep(t);
			}
		} catch (InterruptedException e) {
		}
	}
	
	/**
	 * 发送任务
	 * @param map
	 * @throws ServiceException 
	 * @throws MessageException
	 */
	public void save(Map<String, Object> map) {
		if(map!=null && !map.isEmpty()){
			Iterator<String> keys =  map.keySet().iterator();
			String txt = "SERVERNAME=" + name + ",date=" + DataFormat.format(new Timestamp(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss");
			while(keys.hasNext()){
				String key = keys.next();
				txt = txt + "," + key + "=" + map.get(key).toString();
			}
 			System.out.println(START + txt + END);
			
			Logger.getLogger(this.getClass()).info(START + txt + END);
		}
	}
	
	/**
	 * @see Runnable#run()
	 */
	public void run() {
		init();
		try {
			Logger.getLogger(this.getClass()).debug("initializing " + this.getClass().getSimpleName() + "  initialized success");
			while(!stop){
				try {
					save(getData());
				} catch (Exception e) {
					Logger.getLogger(this.getClass()).error(e);
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
				timeDelay();
			}
		} catch (Exception e1) {
			Logger.getLogger(this.getClass()).error(e1);
		}
	}
	
	private  static Thread thread = null;

	public synchronized static void start(String name) {
		if(thread==null){
			AliveSave save = new AliveSave(name);
			thread = new Thread(save);
			thread.start();
		}else{
			if(!thread.isAlive()){
				thread.start();
			}
		}
	}
}
