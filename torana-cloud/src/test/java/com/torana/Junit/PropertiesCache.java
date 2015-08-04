package com.torana.Junit;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
 
public class PropertiesCache
{
   private final Properties configProp = new Properties();
    
    PropertiesCache()
   {
      //Private constructor to restrict new instances
      InputStream in = this.getClass().getClassLoader().getResourceAsStream("app.properties");
      System.out.println("Read all properties from file1");
      try {
          configProp.load(in);
          Set<Object> keys = configProp.keySet();
          for(Object k:keys){
              String key = (String)k;
              System.out.println(key+"  ::::  "+this.configProp.getProperty(key));
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
   }
 
   //Bill Pugh Solution for singleton pattern
   private static class LazyHolder
   {
      private static final PropertiesCache INSTANCE = new PropertiesCache();
   }
 
   public static PropertiesCache getInstance()
   {
      return LazyHolder.INSTANCE;
   }
    
   public String getProperty(String key){
	  // return "http://184.73.201.195:8089/torana/services/cloudElements/updateCloudElement";
      return configProp.getProperty(key);
   }
    
   public Set<String> getAllPropertyNames(){
      return configProp.stringPropertyNames();
   }
    
   public boolean containsKey(String key){
      return configProp.containsKey(key);
   }
}
