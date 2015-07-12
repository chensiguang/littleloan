package com.hexin.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PropertyType {
	/**
	 * Field指数据库字段<br>
	 * View指显示的额外字段，如一个表里保存userId，要显示userName，可以声明为View<br>
	 * Parameter指用户界面传递过来的参数
	 * @author huqitao
	 *
	 */
	public enum FieldType{Field,View,Parameter}
	public enum InsertNullType{Yes,No}
	FieldType[] type() default {FieldType.Field};
	InsertNullType InsertNull() default InsertNullType.Yes;//新增时是否允许为空，no=不允许,yes=允许
	String insertNullMessage() default "";//新增为空时，提示信息
}
