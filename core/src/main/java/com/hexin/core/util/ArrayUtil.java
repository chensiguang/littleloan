package com.hexin.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

public class ArrayUtil {
	
	public static String join(List array, String propertyName, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }
        int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return "";
        }
        StringBuilder buf = new StringBuilder(noOfItems * 16);
        for (int i = startIndex; i < endIndex; i++) {
            Object item = array.get(i);
            if (item != null) {
            	Object value = null;
            	if(item instanceof Map){
            		value = ((Map) item).get(propertyName);
            	}else{
            		try {
            			value = BeanUtils.getProperty(item, propertyName);
					} catch (Exception e) {
						value = item.toString();
					}
            	}
            	if(value != null && StringUtils.isNotEmpty(value.toString())){
            		if (i > startIndex && buf.length() > 0) {
                        buf.append(separator);
                    }
            		buf.append(value);
            	}
            }
        }
        return buf.toString();
    }
	
	public static List<String> joinToArray(Collection list, String propertyName) {
        if (list == null) {
            return null;
        }
        List<String> ls = new ArrayList<String>(list.size());
        for (Object item : list) {
        	if (item != null) {
            	String value = null;
            	if(item instanceof Map){
            		value = ((Map) item).get(propertyName).toString();
            	}else{
            		try {
            			value = BeanUtils.getProperty(item, propertyName);
					} catch (Exception e) {
						value = item.toString();
					}
            	}
            	if(value != null && StringUtils.isNotEmpty(value.toString())){
            		ls.add(value);
            	}
            }
		}
        return ls;
    }
	
	public static String joinStrForSQL(Collection list, String propertyName) {
        return join(list, propertyName, ",", "'", "'");
    }
	
	public static String joinIdForSQL(Collection list, String propertyName) {
        return join(list, propertyName, ",");
    }
	
	public static String join(Collection list, String propertyName, String separator) {
        return join(list, propertyName, separator, "", "");
    }
	
	public static String join(Collection list, String propertyName, String separator, String prefix, String suffix) {
        if (list == null) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }
        StringBuilder buf = new StringBuilder(list.size() * 16);
        for (Object item : list) {
        	if (item != null) {
            	Object value = null;
            	if(item instanceof Map){
            		value = ((Map) item).get(propertyName);
            	}else{
            		try {
            			value = BeanUtils.getProperty(item, propertyName);
					} catch (Exception e) {
						value = item.toString();
					}
            	}
            	if(value != null && StringUtils.isNotEmpty(value.toString())){
            		if (buf.length() > 0) {
                        buf.append(separator);
                    }
            		buf.append(prefix).append(value).append(suffix);
            	}
            }
		}
        return buf.toString();
    }
	
	public static <T> T getFirstfromListObject(Object object, Class<T> itemClazz){
		if(object!=null && (object instanceof List) && ((List)object).size()>0){
			Object temp = ((List)object).get(0);
			if(itemClazz.isInstance(temp)){
				return itemClazz.cast(temp);
			}
		}
		return null;
	}
}
