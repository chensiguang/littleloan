package com.hexin.pettyLoan.common;

import java.util.Date;

import com.hexin.core.util.DateUtil;

public class DateHandler extends DateUtil {
	/**
	 * 服务器之间时间同步，保留方法
	 * 
	 * @return
	 */
	public static Date getNow() {
		return new Date();
	}
}
