package com.hexin.pettyLoan.common.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 判断工作是否需要审批<br>
 * workflowName = 工作流名称(配置在SimpleWorkflowConstatns中)<br>
 * description = 说明，可以用<0:name>,<1>等方式来描述参数，其中0表示第0个参数，name表示参数中的属性名称，如果直接使用参数用<1>表示<br>
 * @author Administrator
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedApprove {
	String workflowName() default "";
	String description() default "";
}
