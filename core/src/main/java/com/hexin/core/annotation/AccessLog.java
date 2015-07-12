package com.hexin.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLog {
	public enum OperateType{Insert, Update, Delete, Query}
	public enum Type{Error, Info}
	public enum Add2DB{Yes, No}
	
	OperateType operateType() default OperateType.Query;
	Type type() default Type.Info;
	String description() default "";
	Add2DB add2DB() default Add2DB.No;
}
