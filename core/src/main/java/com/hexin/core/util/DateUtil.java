package com.hexin.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil extends DateUtils
{
  private static final String[] dayNames = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
  public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  public static final String DATE_FORMAT = "yyyy-MM-dd";
  public static final String HHMMSS_FORMAT = "HH:mm:ss";
  public static final String HHMM_FORMAT = "HH:mm";

  public static Date parseDate(String dateTime)
  {
    return parse(dateTime, "yyyy-MM-dd");
  }

  public static Boolean isDate(String dateTime){
	  try{
		  parse(dateTime,"yyyy-MM-dd");
	  }
	  catch(Exception ex){
		  try{
			  parse(dateTime,"yyyy/MM/dd");
		  }
		  catch(Exception ex1){
			  try{
				  parse(dateTime,"yyyy年MM月dd日");
			  }
			  catch(Exception ex2){
				  return false;
			  }
		  }
	  }
	  return true;
  }
  public static Date parseDateTime(String dateTime)
  {
    return parse(dateTime, "yyyy-MM-dd HH:mm:ss");
  }

  public static Date parse(String dateTime, String format)
  {
    if (StringUtils.isBlank(dateTime)) return null;

    DateFormat dateFormat = new SimpleDateFormat(format);
    try {
      return dateFormat.parse(dateTime);
    }
    catch (ParseException e) {
      throw new RuntimeException("format date error!", e);
    }
  }

  public static String format(Date date)
  {
    return formatDateTime(date, "yyyy-MM-dd HH:mm:ss");
  }

  public static String formatYYYYMMDD(Date date)
  {
    return formatDateTime(date, "yyyy-MM-dd");
  }

  public static String formatDateTime(Date date, String format)
  {
    if (date == null) return null;

    DateFormat dateFormat = new SimpleDateFormat(format);

    return dateFormat.format(date);
  }

  public static String formatWeekInMonth(Date date)
  {
    if (date == null) return null;

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    return dayNames[(cal.get(7) - 1)];
  }

  public static int getDiffeSeconds(Date old)
  {
    if (old == null) return 0;

    return getDiffeMinute(null, old);
  }

  public static int getDiffeSeconds(Date now, Date old)
  {
    if (old == null) return 0;
    if (now == null) now = new Date();

    return (int)((now.getTime() - old.getTime()) / 1000L);
  }

  public static int getDiffeMinute(Date old)
  {
    if (old == null) return 0;

    return getDiffeMinute(null, old);
  }

  public static int getDiffeMinute(Date now, Date old)
  {
    if (old == null) return 0;
    if (now == null) now = new Date();

    return (int)((now.getTime() - old.getTime()) / 60000L);
  }

  public static int getDiffeHour(Date old)
  {
    if (old == null) return 0;

    return getDiffeMinute(null, old);
  }

  public static int getDiffeHour(Date now, Date old)
  {
    if (old == null) return 0;
    if (now == null) now = new Date();

    return (int)((now.getTime() - old.getTime()) / 3600000L);
  }

  public static int getDiffeDay(Date old)
  {
    if (old == null) return 0;

    return getDiffeMinute(null, old);
  }

  public static int getDiffeDay(Date now, Date old)
  {
    if (old == null) return 0;
    if (now == null) now = new Date();

    return (int)((now.getTime() - old.getTime()) / 86400000L);
  }

  public static Date firstDayOfMonth()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(5, 1);
    cal.set(11, 0);
    cal.set(12, 0);
    cal.set(13, 0);
    cal.set(14, 0);

    return cal.getTime();
  }
  
}