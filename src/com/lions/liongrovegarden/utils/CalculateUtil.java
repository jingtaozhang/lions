package com.lions.liongrovegarden.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 表达式计算
 * @author zjt 2016-11-28
 *
 */
public class CalculateUtil {
	
	private static final Logger logger = Logger.getLogger(CalculateUtil.class);
	
	/**
	 * avg 求平均值
	 * @param list
	 * @return
	 */
	public static Double avg(List<Double> list){
		Double value =0.0;
		if(null==list||list.size()==0){
			return value;
		}
		logger.debug("接收到的list长度:"+list.size());
		Double total=0.0;
		/*for(Double i :list){
			total +=i;
		}*/
		for(int i = 0;i<list.size();i++){
			total += list.get(i);
		}
		try {
			value = total/(list.size());
		} catch (Exception e) {
			return value;
		}
		return value;
	}
	/**
	 * avg 求和
	 * @param list
	 * @return
	 */
	public static Double sum(List<Double> list){
		if(null==list||list.size()==0){
			return 0.0;
		}
		logger.debug("接收到的list长度:"+list.size());
		Double total=0.0;
		for(int i = 0;i<list.size();i++){
			total += list.get(i);
		}
		return total;
	}
	/**
	 * max 求最大值
	 * @param list
	 * @return
	 */
	public synchronized static Double max(List<Double> list){
		Double value =0.0;
		if(null==list||list.size()==0){
			return 0.0;
		}
		logger.debug("max方法接收到的list长度:"+list.size());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size() ; i++){    //最多做n-1趟排序
			sb.append(list.get(i)+"---");
			if(value<list.get(i)){
				value = list.get(i);
			}
           /*for(int j = 0 ;j < list.size() - i - 1; j++){    //对当前无序区间list[0......length-i-1]进行排序(j的范围很关键，这个范围是在逐步缩小的)
               if(list.get(j) <list.get(j+1)){    //把小的值交换到后面
                   Double temp = list.get(j);
                   list.set(j, list.get(j + 1));
                   list.set(j + 1,temp);
                }
           }*/
        }
//		value = list.get(0);
		logger.debug("求最大值方法："+sb+";最大值为："+value);
	    return value;
	}
	
	/**
	 * max 求最小值
	 * @param list
	 * @return
	 */
	public synchronized static Double min(List<Double> list){
		
		Double value =0.0;
		if(null==list||list.size()==0){
			return value;
		}
		logger.debug("min接收到的list长度:"+list.size());
		for (int i = 0; i < list.size(); i++){    //最多做n-1趟排序
			if(value>list.get(i)){
				value = list.get(i);
			}
			
	         /*  for(int j = 0 ;j < list.size() - i - 1; j++){    //对当前无序区间list[0......length-i-1]进行排序(j的范围很关键，这个范围是在逐步缩小的)
	               if(list.get(j) <list.get(j+1)){    //把小的值交换到后面
	                   Double temp = list.get(j);
	                   list.set(j, list.get(j + 1));
	                   list.set(j + 1,temp);
	                }
	           }*/
	        }
//		value = list.get(list.size()-1);
		return value;
	}
	/**
	 * default 默认
	 * @param list
	 * @return
	 */
	public static Double defaultValue(List<Double> list){
		Double value =0.0;
		if(null==list||list.size()==0){
			return value;
		}
		logger.debug("接收到的list长度:"+list.size());
		value = list.get(list.size()-1);
		return value;
	}
	
	public static void main(String[] args) {
		List<Double> list = new ArrayList<Double>();
		list.add(0.0);
		list.add(10.0);
		list.add(110.0);
		list.add(120.0);
		list.add(110.0);
		list.add(10.0);
		list.add(130.0);
		list.add(10.0);
		list.add(20.0);
		list.add(1230.0);
		System.out.println(CalculateUtil.avg(list));
		System.out.println(CalculateUtil.max(list));;
		System.out.println(CalculateUtil.min(list));;
	}
}
