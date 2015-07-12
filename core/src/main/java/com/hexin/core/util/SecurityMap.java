package com.hexin.core.util;

/**
 * 线程安全的hashMap
 * 
 */
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SecurityMap
{
  private Map<Object, Object> concurrentMap = new ConcurrentHashMap();

  private static ThreadLocal<SecurityMap> local = new ThreadLocal() {
    @Override
	protected SecurityMap initialValue() {
      super.initialValue();
      return new SecurityMap();
    }
  };

  public static void set(Object k, Object v)
  {
    geInstance().concurrentMap.put(k, v);
  }

  public static Object get(Object k)
  {
    return geInstance().concurrentMap.get(k);
  }

  public static void clear() {
    geInstance().concurrentMap.clear();
  }

  private static SecurityMap geInstance() {
    return local.get();
  }
}