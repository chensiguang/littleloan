package com.hexin.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberFormatUtil {
	
	/**
	 * 
	 * 格式化数字类型String，指定保留位数小数，是否不足补0
	 * format("2.2287", 6, true) = "2.228700"
	 * format("2.2287", 6, false) = "2.2287"
	 * format("2.2287", 2, true) = "2.23"
	 * format("2.2287", 2, false) = "2.23"
	 * 
	 * @author Flouny.Caesar
	 * @date 2011-05-01
	 * 
	 * @param num 输入参数
	 *            
	 * @param scale 指定小数保留位数
	 *            
	 * @param isFix 是否不足补0
	 *            
	 * @return
	 */
	public static BigDecimal parse(String num, int scale, boolean isFix) {
		StringBuffer flag = new StringBuffer();
		flag.append("#");
		
		for (int i = 0; i < scale; i++) {
			if (i == 0) {
				flag.append(isFix ? ".0" : ".#");
			} else {
				flag.append(isFix ? "0" : "#");
			}
		}
		
		return new BigDecimal(num);
	}
	
	/**
	 * 
	 * 格式化数字类型String，指定保留位数小数，是否不足补0
	 * format("2.2287", 6, true) = "2.228700"
	 * format("2.2287", 6, false) = "2.2287"
	 * format("2.2287", 2, true) = "2.23"
	 * format("2.2287", 2, false) = "2.23"
	 * 
	 * @author Flouny.Caesar
	 * @date 2011-05-01
	 * 
	 * @param num 输入参数
	 *            
	 * @param scale 指定小数保留位数
	 *            
	 * @param isFix 是否不足补0
	 *            
	 * @return
	 */
	public static String format(String num, int scale, boolean isFix) {
		
		NumberFormat nf = new DecimalFormat(parse(num, scale, isFix).toString());
		
		return nf.format(new BigDecimal(num));
	}
}