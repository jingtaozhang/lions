package com.lions.liongrovegarden.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;


public class ConfigProperty
{
   private static ResourceBundle resource = null;
   public ConfigProperty()
   {
      // TODO : implement
   }
   /** *描述  读取具体的配置值
    *  @param sname 配置名称
    *  @param BundlePath 配置文件路径
    *  @Exception Exception
    *  @return  字符串的配置值
    * @see  java.util.ResourceBundle */
   public  static String get (String sname,String BundlePath){
      String rv =null;
      try{
         resource = ResourceBundle.getBundle(BundlePath);
         rv = resource.getString(sname);
      }catch(Exception e){
         System.out.println(ConfigProperty.class.getName()+" Exp:"+e);
      }
      return rv;
   }
   /** *描述  写入具体的配置值
    *  @param sname:配置值名,svalue:配置的值
    *  @Exception Exception
    *  @return  如果成功则true，否则是异常
    * @see  java.util.ResourceBundle */
   public void set (String sname,String svalue) throws Exception
   {
      // TODO : implement
   }


   /**
    * 获取指定路径下配置文件值
    * @param sname  Key值
    * @param FilePath  如，配置文件在与项目平级的目录中则：application.properties
    */
   public static String getForAbs(String sname,String FilePath){
      Properties pro = new Properties();
      String rtn="";
      try {
    	 String path = ConfigProperty.class.getClassLoader().getResource("/").getPath();
         pro.load(new FileInputStream((path+"/"+FilePath)));
         rtn = pro.get(sname).toString();
      } catch (FileNotFoundException e) {
         System.out.println("ConfigFileNotFoundException");
         e.printStackTrace();
      } catch (IOException e) {
         System.out.println("IOException");
         e.printStackTrace();
      }
      return rtn;
   }
}
