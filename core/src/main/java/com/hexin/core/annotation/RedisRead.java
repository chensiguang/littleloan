package com.hexin.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.poi.ss.formula.functions.T;

/**
 * 启用redis缓存，比如是带参的方法，不带参方法使用ehcache<br >
 * RedisRead的key需要有对应的方法来刷新，如果没有刷新方法，不适合用RedisRead，推荐用ehcache或手动刷新缓存<br>
 * keyName和fieldName可以使用表达式设置对应的值，如<1>表示使用第一个参数替换<1>，<0:name>表示使用第0个参数的name属性替换<0:name>的值，可以加入自定义的一些内容<br>
 * &lt;result&gt;表示用返回结果替换对应的值
 * @author Administrator
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisRead {
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
	
	/**
	 * 返回结果的类型，字符串全称格式，如com.hexin.core.util.StringUtil
	 * @return
	 */
	String valueType() default "";
}
