package com.hexin.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings("unchecked")
public class MapUtil {
	public static final String REGIX = "_";
	/**
	 * 把list映射成attrValue＝List的形式
	 * @param list
	 * @param attrName
	 * @return
	 */
	public static synchronized Map map(List list,String attrName){
		if(list==null||list.size()==0||attrName==null) return null;
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			for(int i=0;list!=null&&i<list.size();i++){
				Object object = list.get(i);
				String attrValue = BeanUtils.getProperty(object, attrName);
				List<Object> object0 = (List<Object>)result.get(attrValue);
				if(object0==null){
					List<Object> list0 = new ArrayList<Object>();
					list0.add(object);
					result.put(attrValue, list0);
				}else{
					object0.add(object);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
	/**
	 * 把list映射成attrValue＝Object的形式, 若有多个, 只取第一个
	 * @param list
	 * @param attrName
	 * @return
	 */
	public static synchronized <T> Map<String, T> mapFirst(List<T> list,String attrName){
		if(list==null||list.size()==0||attrName==null) return null;
		Map<String, T> result = new HashMap<String, T>();
		try {
			for(int i=0;list!=null&&i<list.size();i++){
				T object = list.get(i);
				String attrValue = BeanUtils.getProperty(object, attrName);
				T object0 = result.get(attrValue);
				if(object0==null){
					result.put(attrValue, object);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
	/**
	 * 把list映射成attrValue1_attrValue2_attrValue3＝List的形式
	 * @param list
	 * @param attrNames
	 * @return
	 */
	public static synchronized Map map(List list,String[] attrNames){
		if(list==null||list.size()==0||attrNames==null||attrNames.length==0) return null;
		Map<String, Object> result = new HashMap<String, Object>();
		for(int i=0;list!=null&&i<list.size();i++){
			Object object = list.get(i);
			String attrValues = MapUtil.mergeAttr(object, attrNames);
			List<Object> object0 = (List<Object>)result.get(attrValues);
			if(object0==null){
				List<Object> list0 = new ArrayList<Object>();
				list0.add(object);
				result.put(attrValues, list0);
			}else{
				object0.add(object);
			}
		}
		
		return result;
	}
	
	/**
	 * 连接字段
	 * @param obj
	 * @param col
	 * @return
	 */
	public static synchronized String mergeAttr(Object obj,String[] attrNames){
		String attrValues = "";
		try {
			attrValues = BeanUtils.getProperty(obj, attrNames[0]);
			for(int i=1;i<attrNames.length;i++){
				attrValues += REGIX + BeanUtils.getProperty(obj, attrNames[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attrValues;
	}
}
