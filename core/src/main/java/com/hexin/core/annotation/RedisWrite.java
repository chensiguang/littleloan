package com.hexin.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 写redis
 * @author Administrator
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisWrite {	
	enum Remove {
		Yes, No
	}
	/**
	 * 缓存的类型，包含kv,map,list
	 * @return
	 */
	CacheType type() default CacheType.KeyValue;
	/**
	 * key的名称
	 * @return
	 */
	String keyName() default "";
	/**
	 * field的名称，map类型中使用
	 * @return
	 */
	String fieldName() default "";
	
	Remove remove() default Remove.No;
}
